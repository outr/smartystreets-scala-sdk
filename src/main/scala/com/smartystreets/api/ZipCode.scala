package com.smartystreets.api

case class ZipCode(zipcode: String,
                   zipcodeType: String,
                   defaultCity: String,
                   countyFips: String,
                   countyName: String,
                   stateAbbreviation: String,
                   state: String,
                   latitude: Double,
                   longitude: Double,
                   precision: String,
                   alternateCounties: List[County] = Nil)