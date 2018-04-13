package com.smartystreets.api

case class AddressAnalysis(dpv_match_code: String,
                           dpv_footnotes: String,
                           dpv_cmra: String,
                           dpv_vacant: String,
                           active: String,
                           footnotes: String)