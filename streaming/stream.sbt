name := "streaming"

version := "0.0.1"

scalaVersion := "2.10.4"

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

// additional libraries
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.4.1.2.3.2.0-2950" % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.4.1.2.3.2.0-2950",
  "org.apache.spark" %% "spark-streaming-kafka" % "1.4.1.2.3.2.0-2950"
)

resolvers ++= Seq(
  "Hortonworks Repository" at "http://repo.hortonworks.com/content/repositories/releases/",
  Resolver.sonatypeRepo("public")
)

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case m if m.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
    case m if m.startsWith("META-INF") => MergeStrategy.discard
    case PathList("javax", "servlet", xs @ _*) => MergeStrategy.first
    case PathList("org", "apache", xs @ _*) => MergeStrategy.first
    case PathList("org", "jboss", xs @ _*) => MergeStrategy.first
    case "about.html"  => MergeStrategy.rename
    case "reference.conf" => MergeStrategy.concat
    case _ => MergeStrategy.first
  }
}
