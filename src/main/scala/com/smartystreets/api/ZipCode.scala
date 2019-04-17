package com.smartystreets.api

case class ZipCode(zipcode: String,
                   zipcode_type: String,
                   default_city: String,
                   county_fips: String,
                   county_name: String,
                   state_abbreviation: String,
                   state: String,
                   latitude: Double,
                   longitude: Double,
                   precision: String,
                   alternate_counties: List[County] = Nil)