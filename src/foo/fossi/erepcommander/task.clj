(ns foo.fossi.erepcommander.task
  (:use [foo.fossi.erepcommander model parse_citizen persistence]
	[compojure.html])
  (:import [com.google.appengine.api.labs.taskqueue Queue QueueFactory TaskOptions TaskOptions$Builder]))


(defn update-citizen
  [{{id :id} :params}]
  (html (store-snapshot (parse-citizen id))))

(defn task-handler
  [{{call :*} :params :as params}]
  (condp = call
    "update" (html (update-citizen params))
    (html "denied.")))

(defn enqueue-citizen-update
  [id]
  (.add (QueueFactory/getDefaultQueue) (-> (TaskOptions$Builder/url "/task/update")
					   (.param "id" #^String (str id)))))