import Dependencies._

ThisBuild / organization := "dev.zio"
ThisBuild / version := "$project_version$"
ThisBuild / scalaVersion := "$scala_version$"
ThisBuild / homepage := Some(url("$project_url$"))
ThisBuild / description := "$project_description$"
ThisBuild / licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
ThisBuild / developers := List(
  Developer(
    "johnDoe",
    "John Doe",
    "@johndoe",
    url("https://github.com/johndoe")
  ),
  Developer(
    "janedoe",
    "Jane Doe",
    "@janedoe",
    url("https://github.com/janedoe")
  )
)

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("check", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")

lazy val root = (project in file("."))
  .settings(
    name := "$name$",
    libraryDependencies ++= Seq(
      `dev.zio`.zio.zio,
      `dev.zio`.zio.test,
      `dev.zio`.zio.`test-sbt`,
      `dev.zio`.zio.`config_typesafe`
    ),
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )

lazy val docs = project
  .in(file("$name$-docs"))
  .settings(
    publish / skip := true,
    moduleName := "$name$-docs",
    scalacOptions -= "-Yno-imports",
    scalacOptions -= "-Xfatal-warnings",
    libraryDependencies ++= Seq(
      `dev.zio`.zio.zio
    ),
    ScalaUnidoc / unidoc / unidocProjectFilter := inProjects(root),
    ScalaUnidoc / unidoc / target := (LocalRootProject / baseDirectory).value / "website" / "static" / "api",
    cleanFiles += (ScalaUnidoc / unidoc / target).value,
    docusaurusCreateSite := docusaurusCreateSite.dependsOn(Compile / unidoc).value,
    docusaurusPublishGhpages := docusaurusPublishGhpages.dependsOn(Compile / unidoc).value
  )
  .dependsOn(root)
  .enablePlugins(MdocPlugin, DocusaurusPlugin, ScalaUnidocPlugin)
