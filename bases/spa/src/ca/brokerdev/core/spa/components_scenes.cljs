(ns ca.brokerdev.core.spa.components-scenes
  (:require [portfolio.replicant :refer-macros [defscene]]))

(defscene hello-world
  [:h1 "Hello Spa!"])

(defscene button-example
  [:button {:on {:click #(js/alert "Clicked!")}}
   "Click me"])
