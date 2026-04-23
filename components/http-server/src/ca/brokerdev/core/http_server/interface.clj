(ns ca.brokerdev.core.http-server.interface
  (:require [ca.brokerdev.core.http-server.server :as server]
            [ca.brokerdev.core.http-server.handler :as handler]))

(defn start!
  "Starts an HTTP server on the specified port.

  Required:
    :handler - Ring handler to serve requests
    :port    - Port number to listen on

  Returns a map with :port and :stop-fn for shutdown."
  [{:keys [_handler _port] :as opts}]
  (server/start! opts))

(defn stop!
  "Stops a running HTTP server.

  Required:
    :stop-fn - stop fn to turn off the HTTP server

  Takes the system map returned by start! and invokes its stop function."
  [{:keys [_stop-fn] :as system}]
  (server/stop! system))

(defn build-handler
  "Builds a Ring handler with routing, middleware, and CORS support.

  Options:
    :cors-origins - Collection of allowed origin URLs for CORS"
  [{:keys [_cors-origins] :as system}]
  (handler/build system))
