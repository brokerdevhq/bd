(ns ca.brokerdev.core.http-server.server
  (:require [org.httpkit.server :as hk]
            [ca.brokerdev.core.log.interface :as log]))

(defn start! [{:keys [handler port] :as opts}]
  (log/info "Starting webserver on port: " port opts)
  {:port port
   :stop-fn (hk/run-server handler 
                           (select-keys opts [:port]))})

(defn stop! [system]
  (log/warn "Stopping webserver")
  ((:stop-fn system)))
