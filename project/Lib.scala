import sbt._

object Lib {
  def compile   (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "compile")
  def provided  (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "provided")
  def test      (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")

  val repos = Seq(
    "Central Maven Repository" at "https://repo1.maven.org/maven2",
    "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository",
    "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"
  )

  val logback       = "ch.qos.logback"              %   "logback-classic"         % Version.LOGBACK
  val mysqlconn     = "mysql"                       %   "mysql-connector-java"    % Version.MYSQLCONN
  val cucumberJUnit = "info.cukes"                  %   "cucumber-junit"          % Version.CUCUMBER
  val cucumberGuice = "info.cukes"                  %   "cucumber-guice"          % Version.CUCUMBER
  val dbunit        = "org.dbunit"                  %   "dbunit"                  % Version.DBUNIT
  val junit         = "junit"                       %   "junit"                   % Version.JUNIT
  val scalaCompiler = "org.scala-lang"              %   "scala-compiler"          % Version.SCALA
  val scalaReflect  = "org.scala-lang"              %   "scala-reflect"           % Version.SCALA
  val jacksonBind   = "com.fasterxml.jackson.core"  %   "jackson-databind"        % Version.JACKSON
  val jacksonScala  = "com.fasterxml.jackson.module"% "jackson-module-scala_2.11" % Version.JACKSONMODULE
  val guice         = "com.google.inject"           %   "guice"                   % Version.GUICE
  val jaxrs         = "org.jboss.resteasy"          %   "resteasy-jaxrs"          % Version.JAXRS
  val jaxrsClient   = "org.jboss.resteasy"          %   "resteasy-client"         % Version.JAXRS
}