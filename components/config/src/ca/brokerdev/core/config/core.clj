(ns ca.brokerdev.core.config.core
  (:require [clojure.tools.cli :as cli])
  (:import [java.net ServerSocket]))

(defn get-free-port []
  (with-open [socket (ServerSocket. 0)]
    (.getLocalPort socket)))

(defn parse-opts [args cli-options]
  (cli/parse-opts args cli-options))
