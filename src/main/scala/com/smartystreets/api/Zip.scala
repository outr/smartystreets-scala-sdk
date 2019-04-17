package com.smartystreets.api

case class Zip(input_index: Int,
               city_states: List[CityState] = Nil,
               zipcodes: List[ZipCode] = Nil,
               status: ZipStatus = ZipStatus.Success,
               reason: Option[String])