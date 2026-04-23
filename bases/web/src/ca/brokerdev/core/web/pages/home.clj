(ns ca.brokerdev.core.web.pages.home
  (:require [hiccup2.core :as h]))

(defn page []
  (str (h/html
         [:html
          [:head
           [:meta {:charset "UTF-8"}]
           [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
           [:title "BrokerDev"]
           [:link {:rel "stylesheet" :href "/style.css"}]]
          [:body
           [:h1 "Home Page"]
           [:p "Welcome to BrokerDev"]]])))
