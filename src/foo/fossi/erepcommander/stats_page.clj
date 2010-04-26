(ns foo.fossi.erepcommander.stats-page
  (:use [foo.fossi.erepcommander model persistence]	
	[compojure.html])
  (:require [com.freiheit.clojure.util.date :as date]))

(defn get-overview-data
  []
  (let [data (for 
		 [citizen (find-all-citizens)]
	       (merge citizen (get-latest-snapshot-by-id (:id citizen))))]
    (reverse (sort-by :xp data))))

(defn render-body
  [title content]
  [:html
   [:head
    [:title "eRepStrategy"]
    [:link {:rel "stylesheet" :type "text/css" :href "/css/erepcommander.css"}]]
   [:body
    [:div {:style "text-align:center;"}
     [:div {:class "center"}
      [:h2 title]
      [:div {:class "content"} content]]]]])

(defn render-players
  [data]
  [:table
   [:tr 
    [:th "Platz"]
    [:th "Level"]
    [:th "XP"]
    [:th "Name"]
    [:th "Stärke"]
    [:th "Damage"]
    [:th "Wellness"]
    [:th "Alter"]
    [:th "Land"]]
   (for [iplayer (map vector (iterate inc 1) data)]
     (let [idx (first iplayer)
	   player (second iplayer)]
      [:tr 
       [:td idx]
       [:td (:lvl player)]
       [:td (:xp player)]
       [:td [:a {:href (str *erep-url-citizen* (:id player))} (:name player)]]
       [:td (format "%.2f" (:str player))]
       [:td (:dmg player)]
       [:td (format "%.2f" (:wellness player))]
       [:td (.substring (:birth player) 0 10)]
       [:td (:country player)]]))])

(defn render-stats-page
  [data]
  (html (render-body
	 "Spieler" (render-players data))))

(defn stats-page-handler
  [param]
  (render-stats-page (get-overview-data)))