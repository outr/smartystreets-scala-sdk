package com.smartystreets.api

sealed trait ZipStatus

object ZipStatus {
  case object Success extends ZipStatus
  case object Blank extends ZipStatus
  case object InvalidState extends ZipStatus
  case object InvalidCity extends ZipStatus
  case object InvalidZipcode extends ZipStatus
  case object Conflict extends ZipStatus
}