package com.smartystreets.api

import io.circe.generic.extras.Configuration
import io.youi.client.HttpClient
import io.youi.net._
import profig.Profig

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class SmartyStreets(authId: String = SmartyStreets.authId,
                    authToken: String = SmartyStreets.authToken) {
  private[api] lazy val client = new HttpClient()
  private[api] def url(baseURL: URL, params: Map[String, String] = Map.empty): URL = {
    baseURL.withParam("auth-id", authId).withParam("auth-token", authToken).withParams(params)
  }

  object streets {
    lazy val us: USStreetAddress = new USStreetAddress(SmartyStreets.this)
  }

  def dispose(): Unit = client.dispose()
}

object SmartyStreets {
  private[api] implicit val customConfig: Configuration = Configuration.default.withDefaults.withSnakeCaseMemberNames.withSnakeCaseConstructorNames

  def authId: String = Profig("smartystreets.authId")
    .as[Option[String]]
    .getOrElse(throw new RuntimeException(s"No configuration defined for smartystreets.authId"))
  def authToken: String = Profig("smartystreets.authToken")
    .as[Option[String]]
    .getOrElse(throw new RuntimeException(s"No configuration defined for smartystreets.authToken"))

  def main(args: Array[String]): Unit = {
    Profig.loadDefaults()
    Profig.merge(args)
    val ss = new SmartyStreets()
    try {
//      val list = Await.result(ss.streets.us(street = Some("345 Spear Street San Francisco, CA")), Duration.Inf)
      val list = Await.result(ss.streets.us(
        USStreetQuery(street = Some("345 Spear Street San Francisco, CA"), inputId = Some("ca")),
        USStreetQuery(street = Some("224 ness drive norman, ok"), inputId = Some("ok"))
      ), Duration.Inf)
      println(s"Results: $list")
    } finally {
      ss.dispose()
    }
  }
}