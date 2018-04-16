package com.smartystreets.api

import scala.concurrent.Future
import io.youi.net._
import io.circe.generic.extras.auto._
import SmartyStreets._
import scala.concurrent.ExecutionContext.Implicits.global

class USStreetAddress(instance: SmartyStreets) {
  private lazy val baseURL = url"https://us-street.api.smartystreets.com/street-address"

  import instance._

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
    val q = USStreetQuery(
      street = street,
      street2 = street2,
      secondary = secondary,
      city = city,
      state = state,
      zip = zip,
      lastLine = lastLine,
      addressee = addressee,
      urbanization = urbanization,
      candidates = candidates,
      matchStrategy = matchStrategy,
      inputId = inputId)
    client.call[List[StreetAddress]](url(baseURL, q.params))
  }

  def apply(addresses: USStreetQuery*): Future[List[StreetAddress]] = {
    val list = addresses.toList
    client.restful[List[USStreetQuery], List[StreetAddress]](url(baseURL), list.take(100)).flatMap { results =>
      if (list.size > 100) {
        apply(list.drop(100): _*).map(results ::: _)
      } else {
        Future.successful(results)
      }
    }
  }
}