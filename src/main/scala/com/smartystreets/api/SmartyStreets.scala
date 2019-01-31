package com.smartystreets.api

import io.circe.generic.extras.Configuration
import io.youi.client.{HttpClient, HttpClientConfig}
import io.youi.net._
import profig.Profig

import scala.concurrent.duration._

class SmartyStreets private(authId: String,
                            authToken: String,
                            val groupSize: Int = 100) {
  private[api] lazy val client = HttpClient(HttpClientConfig(
    retries = 100,
    retryDelay = 10.seconds
  ))

  private[api] def url(baseURL: URL, params: Map[String, String] = Map.empty): URL = {
    baseURL.withParam("auth-id", authId).withParam("auth-token", authToken).withParams(params)
  }

  object streets {
    lazy val us: USStreetAddress = new USStreetAddress(SmartyStreets.this)
  }

  object zip {
    lazy val us: USZip = new USZip(SmartyStreets.this)
  }

}

object SmartyStreets {
  private[api] implicit val customConfig: Configuration = Configuration
    .default
    .withDefaults
    .withSnakeCaseMemberNames
    .withSnakeCaseConstructorNames

  def authId: Option[String] = Profig("smartystreets.authId")
    .opt[String]

  def authToken: Option[String] = Profig("smartystreets.authToken")
    .opt[String]

  def groupSize: Int = Profig("smartystreets.groupSize").as[Int](100)

  def get(authId: Option[String] = authId,
          authToken: Option[String] = authToken,
          groupSize: Int = groupSize): Option[SmartyStreets] = authId.flatMap(aid => authToken.map(at => aid -> at)) match {
    case Some((aid, at)) => Some(new SmartyStreets(aid, at, groupSize))
    case None => None
  }

  def apply(authId: Option[String] = authId,
            authToken: Option[String] = authToken,
            groupSize: Int = groupSize): SmartyStreets = get(authId, authToken, groupSize)
    .getOrElse(throw new RuntimeException("No configuration defined for smartystreets.authId or authToken"))
}