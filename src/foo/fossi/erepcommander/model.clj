(ns foo.fossi.erepcommander.model)

(def *offline-mode* false)

(def *api-url* "http://api.erepublik.com/v1/feeds/")
(def *api-url-citizen* "citizens/%s?by_username=true")

(def *local-dir* "/home/fossi/projects/erep/psde/data/")

(def *erep-url-citizen* "http://www.erepublik.com/en/citizen/profile/")

(defstruct citizen :id :name :birth)
(defstruct citizen-snapshot :id :lvl :xp :str :dmg :country :citizen :wellness)
(defstruct group :id :name :count :uids)


(defn make-citizen 
  [id name birth]
  (struct citizen id name birth))

(defn make-citizen-snapshot
  [id lvl xp str dmg wellness country citizenship]
  (struct citizen-snapshot id lvl xp str dmg country citizenship wellness))

(defn make-group
  [id name count uids]
  (struct group id name count uids))
