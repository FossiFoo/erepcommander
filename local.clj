(ns local
  (import 
   [com.google.appengine.tools.development ApiProxyLocalFactory ApiProxyLocalImpl LocalServiceContext LocalServerEnvironment]
   [com.google.appengine.api.labs.taskqueue.dev LocalTaskQueue]
   [com.google.apphosting.api ApiProxy$Environment]
   [java.util HashMap]
   [java.io File]))

(defn init-app-engine
  "Initialize the app engine services. Call it once from the REPL"
  ([]
     (init-app-engine "/tmp"))
  ([dir]
     (let [default-port 9090
	   env-proxy (proxy [ApiProxy$Environment] []
		       (isLoggedIn [] false)
		       (getRequestNamespace [] "")
		       (getDefaultNamespace [] "")
		       (getAttributes [] (let [attributes (HashMap.)
					       local-server-url (str "http://localhost:" default-port)]
					   (.put attributes "com.google.appengine.server_url_key" local-server-url)
					   attributes))
		       (getAppId [] "local"))
	   local-env (proxy [LocalServerEnvironment] []
		       (getAppDir [] (File. dir))
		       (getAddress [] "localhost")
		       (getPort [] default-port)
		       (waitForServerToStart [] nil))
	   api-proxy (.create (ApiProxyLocalFactory.) local-env)]
	  (do
	    (com.google.apphosting.api.ApiProxy/setEnvironmentForCurrentThread env-proxy)
	    (com.google.apphosting.api.ApiProxy/setDelegate api-proxy)))))