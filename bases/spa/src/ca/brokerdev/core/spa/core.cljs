(ns ca.brokerdev.core.spa.core
  (:require [replicant.dom :as d]))

(defn ^:dev/after-load init []
  (d/render (js/document.getElementById "app")
            [:h1 "Hello Spa!"]))
