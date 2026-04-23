(ns ca.brokerdev.core.config.core
  (:import [java.net ServerSocket]))

(defn get-free-port []
  (with-open [socket (ServerSocket. 0)]
    (.getLocalPort socket)))
