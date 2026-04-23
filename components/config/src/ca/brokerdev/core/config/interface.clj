(ns ca.brokerdev.core.config.interface
  (:require [aero.core :as aero]
            [clojure.java.io :as io]
            [ca.brokerdev.core.config.core :as c]
            [ca.brokerdev.core.lifecycle.interface :as lifecycle]))
               
(defmethod aero/reader 'lifecycle/ref [_ _ value]
  (lifecycle/ref value))

(defmethod aero/reader 'lifecycle/random [_ _ value]
  (case value
        :uuid (random-uuid)
        :port (c/get-free-port)))

(defn read-config
  "Loads a configuration file from the base resource file. 

  Arguments:
  - resource-path: The path to the EDN resource (string) (e.g. \"api/config.edn\" .
  - opts (e.g., {:profile :local})."
  [resource-path opts]
  (if-let [res (io/resource resource-path)]
    (aero/read-config res opts)
    (throw (ex-info "Configuration resource not found" {:path resource-path}))))  

(defn parse-opts [args cli-options]
  (c/parse-opts args cli-options))
