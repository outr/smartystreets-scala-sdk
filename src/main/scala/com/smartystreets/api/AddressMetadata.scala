package com.smartystreets.api

case class AddressMetadata(record_type: String,
                           zip_type: String,
                           county_fips: String,
                           county_name: String,
                           carrier_route: String,
                           congressional_district: String,
                           rdi: String,
                           elot_sequence: String,
                           elot_sort: String,
                           latitude: Double,
                           longitude: Double,
                           precision: String,
                           time_zone: String,
                           utc_offset: Int,
                           dst: Boolean)