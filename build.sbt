import sbt.Keys._
import sbt._

lazy val _organization = "com.zeptolab"
lazy val _version = "0.1.0"
scalacOptions ++= Seq("-deprecation", "-language:_", "-unchecked")

lazy val _resolvers = Seq(
  "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
  "sonatype releases" at "http://oss.sonatype.org/content/repositories/releases"
)

lazy val _scalacOptions = Seq("-unchecked")

lazy val _pomExtra = {
  <url>https://github.com/zeptolab/stackable-controller/tree/play26</url>
    <licenses>
      <license>
        <name>Apache License, Version 2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:zeptolab/stackable-controller</url>
      <connection>scm:git:git@github.com:zeptolab/stackable-controller</connection>
    </scm>
    <developers>
      <developer>
        <id>gakuzzzz</id>
        <name>gakuzzzz</name>
        <url>https://github.com/gakuzzzz</url>
      </developer>
      <developer>
        <id>ezhulkov</id>
        <name>ezhulkov</name>
        <url>https://github.com/ezhulkov</url>
      </developer>
    </developers>
}

val Scala212 = "2.12.2"

lazy val core = Project(
  id = "core",
  base = file("core")
).settings(
  organization := _organization,
  name := "stackable-controller",
  version := _version,
  scalaVersion := Scala212,
  crossScalaVersions := Scala212 :: Nil,
  publishTo := Some(if (isSnapshot.value) Opts.resolver.sonatypeSnapshots else Opts.resolver.sonatypeStaging),
  publishMavenStyle := true,
  resolvers ++= _resolvers,
  libraryDependencies ++= Seq(
    "com.typesafe.play" %% "play" % play.core.PlayVersion.current % "provided"
  ),
  sbtPlugin := false,
  scalacOptions ++= _scalacOptions,
  publishMavenStyle := true,
  publishArtifact in Test := false,
  pomIncludeRepository := { x => false },
  pomExtra := _pomExtra
)

lazy val root = Project(id = "root", base = file(".")).settings(
  scalaVersion := Scala212
).aggregate(core)

