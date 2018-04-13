package com.smartystreets.api

case class AddressComponents(primaryNumber: String,
                             streetName: String,
                             streetSuffix: String,
                             cityName: String,
                             stateAbbreviation: String,
                             zipcode: String,
                             plus4Code: String,
                             deliveryPoint: String,
                             deliveryPointCheckDigit: String)