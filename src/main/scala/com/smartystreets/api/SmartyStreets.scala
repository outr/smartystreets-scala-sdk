package com.smartystreets.api

import io.circe.generic.extras.Configuration
import io.youi.client.{HttpClient, HttpClientConfig}
import io.youi.net._
import profig.Profig

import scala.concurrent.duration._

class SmartyStreets(authId: String = SmartyStreets.authId,
                    authToken: String = SmartyStreets.authToken,
                    http2: Boolean = false,
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

  def authId: String = Profig("smartystreets.authId")
    .opt[String]
    .getOrElse(throw new RuntimeException(s"No configuration defined for smartystreets.authId"))
  def authToken: String = Profig("smartystreets.authToken")
    .opt[String]
    .getOrElse(throw new RuntimeException(s"No configuration defined for smartystreets.authToken"))
}