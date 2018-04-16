package com.smartystreets.api

case class AddressAnalysis(dpvMatchCode: String,
                           dpvFootnotes: String,
                           dpvCmra: String,
                           dpvVacant: String,
                           active: String,
                           ewsMatch: Option[String],
                           footnotes: String,
                           lacslinkCode: Option[String],
                           lacslinkIndicator: Option[String],
                           suitelinkMatch: Option[String])