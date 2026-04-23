(ns ca.brokerdev.core.web.lifecycle.http-server
  (:require 
    [ca.brokerdev.core.lifecycle.interface :as lifecycle]
    [ca.brokerdev.core.http-server.interface :as http-server]
    
    [ca.brokerdev.core.web.pages.home :as home]
    ))

(defmethod lifecycle/init-key :web/http-server [_ config]
  (http-server/start! config))

(defmethod lifecycle/halt-key :web/http-server [_ system]
  (http-server/stop! system))

(defn- home-hanlder [_req]
  {:status  200
   :body    (home/page)})

(defn- not-found [_req]
  {:status  404
   :body    {:error "Custom not found"}})

(def routes 
  [["/"      {:name ::home  :get  {:handler home-hanlder}}]])

(defmethod lifecycle/init-key :web/handler [_ system]
  (http-server/build-handler (assoc system
                                    :routes routes
                                    :not-found not-found
                                    :resources ["web/public"]
                                    )))
