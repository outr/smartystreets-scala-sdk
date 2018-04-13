package com.smartystreets.api

case class StreetAddress(input_index: Int,
                           candidate_index: Int,
                           delivery_line_1: String,
                           last_line: String,
                           delivery_point_barcode: String,
                           components: AddressComponents,
                           metadata: AddressMetadata,
                           analysis: AddressAnalysis)