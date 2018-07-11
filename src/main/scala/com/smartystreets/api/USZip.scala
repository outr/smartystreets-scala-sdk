package com.smartystreets.api

import scala.concurrent.Future
import io.youi.net._
import io.circe.generic.extras.auto._
import SmartyStreets._
import io.circe.Decoder.Result
import io.circe.{Decoder, HCursor}

import scala.concurrent.ExecutionContext.Implicits.global

class USZip(instance: SmartyStreets) {
  private lazy val baseURL = url"https://us-zipcode.api.smartystreets.com/lookup"

  import instance._

  private implicit val statusDecoder: Decoder[ZipStatus] = new Decoder[ZipStatus] {
    override def apply(c: HCursor): Result[ZipStatus] = c.as[String] match {
      case Left(df) => throw df
      case Right(s) => Right(s match {
        case "blank" | "" => ZipStatus.Blank
        case "invalid_state" => ZipStatus.InvalidState
        case "invalid_city" => ZipStatus.InvalidCity
        case "invalid_zipcode" => ZipStatus.InvalidZipcode
        case "conflict" => ZipStatus.Conflict
      })
    }
  }

  def apply(city: Option[String] = None,
            state: Option[String] = None,
            zipcode: Option[String] = None,
            inputId: Option[String] = None): Future[Zip] = {
    val q = ZipQuery(city, state, zipcode, inputId)
    client.call[List[Zip]](url(baseURL, q.params)).map(_.head)
  }

  def apply(zips: ZipQuery*): Future[List[Zip]] = {
    val list = zips.toList
    client.restful[List[ZipQuery], List[Zip]](url(baseURL), list.take(groupSize)).flatMap { results =>
      if (list.size > groupSize) {
        apply(list.drop(groupSize): _*).map(results ::: _)
      } else {
        Future.successful(results)
      }
    }
  }
}