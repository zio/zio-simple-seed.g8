import sbt._

object Dependencies {

  case object `dev.zio` {
    case object zio {
      val zio = "dev.zio" %% "zio" % "$zio_version$"
      val test = "dev.zio" %% "zio-test" % "$zio_version$" % "test"
      val `test-sbt` = "dev.zio" %% "zio-test-sbt" % "$zio_version$" % "test"
      val `config_typesafe` =
        "dev.zio" %% "zio-config-typesafe" % "$zio_config_version$"
    }
  }
}
