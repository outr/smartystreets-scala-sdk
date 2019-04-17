package com.smartystreets.api

case class AddressAnalysis(dpv_match_code: Option[String],
                           dpv_footnotes: Option[String],
                           dpv_cmra: Option[String],
                           dpv_vacant: Option[String],
                           active: Option[String],
                           ews_match: Option[String],
                           footnotes: Option[String],
                           lacslink_code: Option[String],
                           lacslink_indicator: Option[String],
                           suitelink_match: Option[String])