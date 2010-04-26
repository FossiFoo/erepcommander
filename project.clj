(defproject foo.fossi/erepcommander "0.0.1-SNAPSHOT"
  :description "A tool for organising groups in eRepublic"
  :dependencies [[org.clojure/clojure "1.1.0"]
                 [org.clojure/clojure-contrib "1.1.0"]
		 [compojure/compojure "0.3.2"]
		 [clojure-http-client "1.0.0-SNAPSHOT"]
		 [com.google.appengine/appengine-api "1.3.1"]
		 [appengine-clj/appengine-clj "0.0.1"]
		 [joda-time "1.6"]
		 [fdc-clojure-libs-bin "0.10.0"]
		 [clj-serializer "0.1.0"]
		 [clj-gae-datastore-bin "0.1"]]
  
  :dev-dependencies [[lein-clojars "0.5.0-SNAPSHOT"]]
  :namespaces [foo.fossi.erepcommander.erepcommander]
  :compile-path "dist/WEB-INF/classes/")