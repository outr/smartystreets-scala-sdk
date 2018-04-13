package com.smartystreets.api

case class AddressComponents(primary_number: String,
                             street_name: String,
                             street_suffix: String,
                             city_name: String,
                             state_abbreviation: String,
                             zipcode: String,
                             plus4_code: String,
                             delivery_point: String,
                             delivery_point_check_digit: String)