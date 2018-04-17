package com.smartystreets.api

case class AddressMetadata(recordType: String,
                           zipType: String,
                           countyFips: String,
                           countyName: String,
                           carrierRoute: String,
                           congressionalDistrict: String,
                           buildingDefaultIndicator: Option[String],
                           rdi: String,
                           elotSequence: String,
                           elotSort: String,
                           latitude: Double,
                           longitude: Double,
                           precision: String,
                           timeZone: String,
                           utcOffset: Int,
                           dst: Option[Boolean])