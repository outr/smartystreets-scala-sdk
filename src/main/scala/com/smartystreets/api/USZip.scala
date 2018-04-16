package com.smartystreets.api

import scala.concurrent.Future
import io.youi.net._
import io.circe.generic.extras.auto._
import SmartyStreets._
import scala.concurrent.ExecutionContext.Implicits.global

class USZip(instance: SmartyStreets) {
  private lazy val baseURL = url"https://us-zipcode.api.smartystreets.com/lookup"

  import instance._

  def apply(city: Option[String] = None,
            state: Option[String] = None,
            zipcode: Option[String] = None,
            inputId: Option[String] = None): Future[List[Zip]] = {
    val q = ZipQuery(city, state, zipcode, inputId)
    client.call[List[Zip]](url(baseURL, q.params))
  }

  def apply(zips: ZipQuery*): Future[List[Zip]] = {
    val list = zips.toList
    client.restful[List[ZipQuery], List[Zip]](url(baseURL), list.take(100)).flatMap { results =>
      if (list.size > 100) {
        apply(list.drop(100): _*).map(results ::: _)
      } else {
        Future.successful(results)
      }
    }
  }
}