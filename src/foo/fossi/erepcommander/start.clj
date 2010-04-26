; starting the application in a jetty.
; the original application is decorated by a function that sets up the
; app engine services.

(ns foo.fossi.erepcommander.start
  (:use [foo.fossi.erepcommander.erepcommander]
	[clojure.contrib.def])
  (:require [com.freiheit.clojure.appengine.appengine-local :as gae-local]))

(defvar- decorated-app
  (gae-local/environment-decorator
   erep-commander)
  "a version of the website application that can run locally")

(defn start-server
  "starts the app."
  []
  (gae-local/new-server local-server decorated-app)
  (gae-local/start-gae-server local-server)
  "Server started")
