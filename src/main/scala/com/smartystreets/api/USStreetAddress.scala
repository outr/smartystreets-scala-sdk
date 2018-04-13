package com.smartystreets.api

import scala.concurrent.Future
import io.youi.net._
import io.circe.generic.auto._

class USStreetAddress(instance: SmartyStreets) {
  private lazy val baseURL = url"https://us-street.api.smartystreets.com/street-address"

  def apply(street: Option[String] = None,
            street2: Option[String] = None,
            secondary: Option[String] = None,
            city: Option[String] = None,
            state: Option[String] = None,
            zip: Option[String] = None,
            lastLine: Option[String] = None,
            addressee: Option[String] = None,
            urbanization: Option[String] = None,
            candidates: Int = 1,
            matchStrategy: MatchOutputStrategy = MatchOutputStrategy.Strict,
            inputId: Option[String] = None): Future[List[StreetAddress]] = {
    def ot(name: String, o: Option[String]): Option[(String, String)] = {
      o.map(name -> _)
    }
    val params = List(
      ot("street", street),
      ot("street2", street2),
      ot("secondary", secondary),
      ot("city", city),
      ot("state", state),
      ot("zipcode", zip),
      ot("lastline", lastLine),
      ot("addressee", addressee),
      ot("urbanization", urbanization),
      ot("candidates", if (candidates != 1) Some(candidates.toString) else None),
      ot("match", if (matchStrategy != MatchOutputStrategy.Strict) Some(matchStrategy.value) else None),
      ot("input_id", inputId)
    ).flatten.toMap
    val url = instance.url(baseURL, params)
    instance.client.call[List[StreetAddress]](url)
  }
}