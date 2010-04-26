(ns foo.fossi.erepcommander.persistence
    (:use foo.fossi.erepcommander.model
	  [com.freiheit.gae.datastore.datastore-query-dsl :only [select where get-by-key get-by-keys]])
    (:require  [com.freiheit.gae.datastore.datastore-access-dsl :as da]
	       [com.freiheit.gae.datastore.datastore-types :as types]
	       [appengine-clj.datastore :as ds])
    (:import [com.google.apphosting.api ApiProxy ApiProxy$Environment]
	     [java.io File]
	     [java.util HashMap]
	     [com.google.appengine.api.datastore Query]))

(da/defentity p-citizen
	      [:key]
	      [:id]
	      [:name]
	      [:birth])

(da/defentity p-citizen-snapshot
	      [:key]
	      [:id]
	      [:lvl]
	      [:xp]
	      [:str]
	      [:dmg]
	      [:wellness]
	      [:country]
	      [:citizen])

(da/defentity p-group
	      [:id]
	      [:name]
	      [:count]
	      [:uids])

(defn store-citizen
  [{id :id
    name :name
    birth :birth}]
  (if (nil? id) nil
      (da/store-entities! [(make-p-citizen :id id :name name :birth birth)])))

(defn get-citizen-by-name
  [#^String name]
  (first (select (where p-citizen [[= :name name]]))))

(defn get-citizen-by-id
  [#^Integer id]
  (first (select (where p-citizen [[= :id id]]))))

(defn find-all-citizens
  []
  (select (where p-citizen [])))


(defn store-snapshot
  [{id :id
    lvl :lvl
    xp :xp
    str :str
    dmg :dmg
    wellness :wellness
    country :country
    citizen :citizen}]
  (da/store-entities! [(make-p-citizen-snapshot 
			:id id 
			:lvl lvl 
			:xp xp
			:str str
			:dmg dmg
			:wellness wellness
			:country country
			:citizen citizen)]))

(defn find-all-snapshots
  []
  (select (where p-citizen-snapshot [])))

(defn get-latest-snapshot-by-id
  [id]
  (last (select (where p-citizen-snapshot [[= :id id]]))))

(defn delete-by-key
  [key]
  (ds/delete key))
