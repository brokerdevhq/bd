(ns ca.brokerdev.core.http-server.handler
  (:require [muuntaja.core :as m]
            [reitit.coercion.malli :as malli]
            [reitit.ring.coercion :as coercion]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [reitit.ring.middleware.parameters :as parameters]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.middleware.resource :refer [wrap-resource]]
            [reitit.ring :as rr]
            ))

(defn- health [_req]
  {:status  200
   :body    {:status "OK"}})

(defn- default-not-found [_req]
  {:status  404
   :body    {:error "Not found"}})

(defn- wrap-system []
  {:name ::system
   :compile (fn [{:keys [system]} _]
              (fn [handler]
                (fn [request]
                  (handler (assoc request :system system)))))})

(defn- origin-patterns [origins]
  (mapv #(re-pattern (str "^" (java.util.regex.Pattern/quote %) "$")) 
        origins))

(defn build [{:keys [routes resources not-found cors-origins] :as system}]
  (let [route-table (conj [["/health" {:name ::health  :get  {:handler health}}]]
                          routes)]
    (cond-> (rr/ring-handler
              (rr/router route-table
                         {:data {:system system
                                 :coercion malli/coercion
                                 :muuntaja m/instance
                                 :middleware [parameters/parameters-middleware
                                              muuntaja/format-middleware
                                              coercion/coerce-request-middleware
                                              coercion/coerce-response-middleware
                                              (wrap-system)
                                              [wrap-cors
                                               :access-control-allow-origin (origin-patterns cors-origins)
                                               :access-control-allow-methods [:post]
                                               :access-control-allow-headers ["Content-Type" "Authorization"]
                                               :access-control-allow-credentials "true"]]}})
              (rr/create-default-handler {:not-found (or not-found default-not-found)}))
      resources (wrap-resource resources))))
