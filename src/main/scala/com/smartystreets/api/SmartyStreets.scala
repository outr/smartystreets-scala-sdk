package com.smartystreets.api

import io.circe.generic.extras.Configuration
import io.youi.client.HttpClient
import io.youi.net._
import profig.Profig

class SmartyStreets(authId: String = SmartyStreets.authId,
                    authToken: String = SmartyStreets.authToken) {
  private[api] lazy val client = new HttpClient()
  private[api] def url(baseURL: URL, params: Map[String, String] = Map.empty): URL = {
    baseURL.withParam("auth-id", authId).withParam("auth-token", authToken).withParams(params)
  }

  object streets {
    lazy val us: USStreetAddress = new USStreetAddress(SmartyStreets.this)
  }
  object zip {
    lazy val us: USZip = new USZip(SmartyStreets.this)
  }

  def dispose(): Unit = client.dispose()
}

object SmartyStreets {
  private[api] implicit val customConfig: Configuration = Configuration
    .default
    .withDefaults
    .withSnakeCaseMemberNames
    .withSnakeCaseConstructorNames

  def authId: String = Profig("smartystreets.authId")
    .as[Option[String]]
    .getOrElse(throw new RuntimeException(s"No configuration defined for smartystreets.authId"))
  def authToken: String = Profig("smartystreets.authToken")
    .as[Option[String]]
    .getOrElse(throw new RuntimeException(s"No configuration defined for smartystreets.authToken"))
}