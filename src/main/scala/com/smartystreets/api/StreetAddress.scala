package com.smartystreets.api

case class StreetAddress(inputIndex: Int,
                         candidateIndex: Int,
                         deliveryLine_1: String,
                         lastLine: String,
                         deliveryPointBarcode: String,
                         components: AddressComponents,
                         metadata: AddressMetadata,
                         analysis: AddressAnalysis)