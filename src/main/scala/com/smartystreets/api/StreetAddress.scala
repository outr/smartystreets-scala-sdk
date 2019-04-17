package com.smartystreets.api

case class StreetAddress(input_id: Option[String],
                         input_index: Int,
                         candidate_index: Int,
                         addressee: Option[String],
                         delivery_line_1: String,
                         delivery_line_2: Option[String],
                         last_line: String,
                         delivery_point_barcode: String,
                         components: AddressComponents,
                         metadata: AddressMetadata,
                         analysis: AddressAnalysis)