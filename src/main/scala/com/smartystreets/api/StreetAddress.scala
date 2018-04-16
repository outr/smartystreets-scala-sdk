package com.smartystreets.api

case class StreetAddress(inputId: Option[String],
                         inputIndex: Int,
                         candidateIndex: Int,
                         addressee: Option[String],
                         deliveryLine_1: String,
                         deliveryLine_2: Option[String],
                         lastLine: String,
                         deliveryPointBarcode: String,
                         components: AddressComponents,
                         metadata: AddressMetadata,
                         analysis: AddressAnalysis)