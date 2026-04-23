(ns ca.brokerdev.core.spa.portfolio
  (:require [portfolio.ui :as ui]
            [ca.brokerdev.core.spa.components-scenes]))

(defn ^:dev/after-load init []
  (ui/start!))
