package com.smartystreets.api

case class Zip(inputId: Option[String],
               inputIndex: Int,
               cityStates: List[CityState] = Nil,
               zipcodes: List[ZipCode] = Nil,
               status: ZipStatus = ZipStatus.Success,
               reason: Option[String])