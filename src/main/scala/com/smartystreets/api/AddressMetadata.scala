package com.smartystreets.api

case class AddressMetadata(record_type: Option[String],
                           zip_type: Option[String],
                           county_fips: Option[String],
                           county_name: Option[String],
                           carrier_route: Option[String],
                           congressional_district: Option[String],
                           building_default_indicator: Option[String],
                           rdi: Option[String],
                           elot_sequence: Option[String],
                           elot_sort: Option[String],
                           latitude: Option[Double],
                           longitude: Option[Double],
                           precision: Option[String],
                           time_zone: Option[String],
                           utc_offset: Option[Int],
                           dst: Option[Boolean])