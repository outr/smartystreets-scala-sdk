name := "smartystreets-scala-sdk"
organization := "com.outr"
version := "1.0.0"
scalaVersion := "2.12.5"
crossScalaVersions := List("2.12.5", "2.11.12")
resolvers += Resolver.sonatypeRepo("releases")
resolvers += Resolver.sonatypeRepo("snapshots")

publishTo in ThisBuild := sonatypePublishTo.value
sonatypeProfileName in ThisBuild := "com.outr"
publishMavenStyle in ThisBuild := true
licenses in ThisBuild := Seq("MIT" -> url("https://github.com/outr/smartystreets-scala-sdk/blob/master/LICENSE"))
sonatypeProjectHosting in ThisBuild := Some(xerial.sbt.Sonatype.GitHubHosting("outr", "smartystreets-scala-sdk", "matt@outr.com"))
homepage in ThisBuild := Some(url("https://github.com/outr/smartystreets-scala-sdk"))
scmInfo in ThisBuild := Some(
  ScmInfo(
    url("https://github.com/outr/smartystreets-scala-sdk"),
    "scm:git@github.com:outr/smartystreets-scala-sdk.git"
  )
)
developers in ThisBuild := List(
  Developer(id="darkfrog", name="Matt Hicks", email="matt@matthicks.com", url=url("http://matthicks.com"))
)


val youiVersion = "0.9.0-M8"

libraryDependencies ++= Seq(
  "io.youi" %% "youi-client" % youiVersion
)