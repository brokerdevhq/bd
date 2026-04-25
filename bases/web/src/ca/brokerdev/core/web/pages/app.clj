(ns ca.brokerdev.core.web.pages.app
  (:require [hiccup2.core :as h]))

(defn page []
  (str (h/html
         [:html
          [:head
           [:meta {:charset "UTF-8"}]
           [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
           [:title "BrokerDev App"]
           [:link {:rel "stylesheet" :href "/style.css"}]]
          [:body
           [:div#app]
           [:script {:src "/app/js/main.js"}]]])))
