package com.smartystreets.api

case class Zip(inputId: Option[String],
               inputIndex: Int,
               cityStates: List[CityState],
               zipcodes: List[ZipCode],
               status: Option[String],
               reason: Option[String])
