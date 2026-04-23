(ns ca.brokerdev.core.log.interface
 (:require [taoensso.timbre :as timbre]))

(defn set-min-level! [opts]
  (timbre/set-min-level! opts))

(defmacro info
  [& args]
  `(timbre/info ~@args))

(defmacro warn
  [& args]
  `(timbre/warn ~@args))

(defmacro error
  [& args]
  `(timbre/error ~@args))

(defmacro debug
  [& args]
  `(timbre/debug ~@args))

(defmacro spy
  [& args]
  `(timbre/spy ~@args)) 
