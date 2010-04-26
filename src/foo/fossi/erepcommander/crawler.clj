(ns foo.fossi.erepcommander.crawler
  (:use foo.fossi.erepcommander.model)
  (:require [clojure.contrib.str-utils2 :only lower-case]
	    [clojure.http.client :as http]
	    [clojure.xml :as xml]
	    [clojure.zip :as zip]
	    ))

;--- Alt ---------------------------------------------------------------------------

;; (defn download-citizen-doc
;;   [name]
;;   (let [response (http/request (get-citizen-name-url name) )]
;;     (if (not (= (:code response) 200))
;;       nil
;;       (:body-seq response))))

;; (defn open-file-citizen-doc
;;   [name]
;;   (slurp (get-citizen-file name)))

;--- Download ----------------------------------------------------------------------

(def url-encode #(. java.net.URLEncoder (encode %)))

(defn get-citizen-name-url
  [name]
  (str *api-url* (format *api-url-citizen* name)))

(defn get-citizen-id-url
  [id]
  (str *api-url* (format *api-url-citizen* id)))

;--- File --------------------------------------------------------------------------

(defn get-citizen-file
  [name]
  (str *local-dir* name ".xml"))

;--- Parsing -----------------------------------------------------------------------

(defn get-citizen-doc-uri
  [name]
  (let [clean-name (url-encode name)]
       (if *offline-mode*
	 (get-citizen-file clean-name)
	 (get-citizen-name-url clean-name))))

(defn load-citizen-doc-by-name
  [name]
  (try
   (xml/parse (get-citizen-doc-uri name))
   (catch Exception e nil)))

;--- API ---------------------------------------------------------------------------

(defn crawl-citizen-by-name
  [name]
  (load-citizen-doc-by-name name))

(defn crawl-citizen
  [id]
  ())