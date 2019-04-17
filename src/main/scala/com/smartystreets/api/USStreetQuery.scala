package com.smartystreets.api

import io.circe.Json
import SmartyStreets._
import io.circe.generic.extras.auto._
import io.circe.syntax._

case class USStreetQuery(street: Option[String] = None,
                         street2: Option[String] = None,
                         secondary: Option[String] = None,
                         city: Option[String] = None,
                         state: Option[String] = None,
                         zip: Option[String] = None,
                         lastline: Option[String] = None,
                         addressee: Option[String] = None,
                         urbanization: Option[String] = None,
                         candidates: Int = 1,
                         matchStrategy: MatchOutputStrategy = MatchOutputStrategy.Strict,
                         input_id: Option[String] = None) {
  private def ot(name: String, o: Option[String]): Option[(String, String)] = {
    o.map(name -> _)
  }

  lazy val params: Map[String, String] = List(
    ot("street", street),
    ot("street2", street2),
    ot("secondary", secondary),
    ot("city", city),
    ot("state", state),
    ot("zipcode", zip),
    ot("lastline", lastline),
    ot("addressee", addressee),
    ot("urbanization", urbanization),
    ot("candidates", if (candidates != 1) Some(candidates.toString) else None),
    ot("match", if (matchStrategy != MatchOutputStrategy.Strict) Some(matchStrategy.value) else None),
    ot("input_id", input_id)
  ).flatten.toMap

  lazy val json: Json = this.asJson
}
