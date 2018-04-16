package com.smartystreets.api

import io.circe.Json
import SmartyStreets._
import io.circe.generic.extras.auto._
import io.circe.syntax._

case class ZipQuery(city: Option[String] = None,
                    state: Option[String] = None,
                    zipcode: Option[String] = None,
                    inputId: Option[String] = None) {
  private def ot(name: String, o: Option[String]): Option[(String, String)] = {
    o.map(name -> _)
  }

  lazy val params: Map[String, String] = List(
    ot("city", city),
    ot("state", state),
    ot("zipcode", zipcode),
    ot("input_id", inputId)
  ).flatten.toMap

  lazy val json: Json = this.asJson
}