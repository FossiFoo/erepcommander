(ns foo.fossi.erepcommander.parse_citizen
  (use [foo.fossi.erepcommander.crawler :only [crawl-citizen crawl-citizen-by-name]]
       [foo.fossi.erepcommander.model])
  (require [clojure.zip :as zip]
	   [clojure.contrib.zip-filter.xml :as zf]))

(defn parse-id
  [zip]
  (Integer/parseInt (zf/xml1-> zip :id zf/text)))

(defn parse-name
  [zip]
  (zf/xml1-> zip :name zf/text))

(defn parse-birth
  [zip]
  (zf/xml1-> zip :date-of-birth zf/text))

;; (defmacro def-ptag
;;   [tag]
;;   `(defn ~(str "parse-" tag) [zip#] (zf/xml-> zip# ~(keyword tag) zf/text)))
;;
;;(def-ptag lvl)

(defn parse-lvl
  [zip]
  (Integer/parseInt (zf/xml1-> zip :level zf/text)))

(defn parse-xp
  [zip]
  (Integer/parseInt (zf/xml1-> zip :experience-points zf/text)))

(defn parse-str
  [zip]
  (Float/parseFloat (zf/xml1-> zip :strength zf/text)))

(defn parse-dmg
  [zip]
  (Integer/parseInt (zf/xml1-> zip :damage zf/text)))

(defn parse-wellness
  [zip]
  (Float/parseFloat (zf/xml1-> zip :wellness zf/text)))

(defn parse-country
  [zip]
  (zf/xml1-> zip :country zf/text))

(defn parse-citizenship
  [zip]
  (zf/xml1-> zip :citizenship :country zf/text))

;--- API ---------------------------------------------------------------------------

(defn init-citizen
  [name]
  (let [citizen-doc (crawl-citizen-by-name name)
	data (zip/xml-zip citizen-doc)]
    (if (nil? citizen-doc) nil 
	(make-citizen 
	 (parse-id data)
	 (parse-name data)
	 (parse-birth data)))))

(defn parse-citizen
  [name]
  (let [citizen-doc (crawl-citizen-by-name name)
	data (zip/xml-zip citizen-doc)]
    (if (nil? citizen-doc) nil
	(make-citizen-snapshot 
	 (parse-id data)
	 (parse-lvl data)
	 (parse-xp data)
	 (parse-str data)
	 (parse-dmg data)
	 (parse-wellness data)
	 (parse-country data)
	 (parse-citizenship data)))))