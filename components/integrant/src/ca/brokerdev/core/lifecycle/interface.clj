(ns ca.brokerdev.core.lifecycle.interface
  (:refer-clojure :exclude [ref])
  (:require [integrant.core :as ig]))

(defn start! [config]
  (ig/init config))

(defn stop! [system]
  (ig/halt! system))

(def init-key ig/init-key)
(def halt-key ig/halt-key!)
(def ref ig/ref)
