package com.smartystreets.api

case class AddressAnalysis(dpvMatchCode: Option[String],
                           dpvFootnotes: Option[String],
                           dpvCmra: Option[String],
                           dpvVacant: Option[String],
                           active: Option[String],
                           ewsMatch: Option[String],
                           footnotes: Option[String],
                           lacslinkCode: Option[String],
                           lacslinkIndicator: Option[String],
                           suitelinkMatch: Option[String])