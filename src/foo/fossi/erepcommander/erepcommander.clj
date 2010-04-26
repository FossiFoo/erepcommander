(ns foo.fossi.erepcommander.erepcommander
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:require [foo.fossi.erepcommander.stats-page :as stats]
	    [foo.fossi.erepcommander.admin :as admin]
	    [foo.fossi.erepcommander.task :as task])
  (:use compojure.http.helpers compojure.http.middleware compojure.http.routes compojure.http.servlet compojure.html compojure.control))

(defroutes erep-commander
  (ANY "/admin/*" admin/admin-handler)
  (ANY "/task/*" task/task-handler)
  (ANY "*" stats/stats-page-handler))

(defservice erep-commander)