package com.smartystreets.api

case class AddressMetadata(recordType: Option[String],
                           zipType: Option[String],
                           countyFips: Option[String],
                           countyName: Option[String],
                           carrierRoute: Option[String],
                           congressionalDistrict: Option[String],
                           buildingDefaultIndicator: Option[String],
                           rdi: Option[String],
                           elotSequence: Option[String],
                           elotSort: Option[String],
                           latitude: Option[Double],
                           longitude: Option[Double],
                           precision: Option[String],
                           timeZone: Option[String],
                           utcOffset: Option[Int],
                           dst: Option[Boolean])