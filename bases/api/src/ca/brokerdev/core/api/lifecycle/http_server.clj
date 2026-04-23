(ns ca.brokerdev.core.api.lifecycle.http-server
  (:require 
    [ca.brokerdev.core.lifecycle.interface :as lifecycle]
    [ca.brokerdev.core.http-server.interface :as http-server]))

(defmethod lifecycle/init-key :api/http-server [_ config]
  (http-server/start! config))

(defmethod lifecycle/halt-key :api/http-server [_ system]
  (http-server/stop! system))

(defmethod lifecycle/init-key :api/handler [_ system]
   (http-server/build-handler system))
