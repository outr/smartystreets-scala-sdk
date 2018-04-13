package com.smartystreets.api

sealed abstract class MatchOutputStrategy(val value: String)

object MatchOutputStrategy {
  case object Strict extends MatchOutputStrategy("strict")
  case object Range extends MatchOutputStrategy("range")
  case object Invalid extends MatchOutputStrategy("invalid")
}