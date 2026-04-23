(ns ca.brokerdev.core.api.core
  (:require 
    [ca.brokerdev.core.config.interface :as config]
    [ca.brokerdev.core.lifecycle.interface :as lifecycle]
    [ca.brokerdev.core.log.interface :as log]
    [ca.brokerdev.core.api.lifecycle.load]
    [clojure.string :as str])
  (:gen-class))

(def allowed-env #{:local})

(def cli-options
  [["-e" "--environment PROFILE" (str "Valid Environment include: " (str/join ", " (map name allowed-env)))
    :parse-fn keyword
    :missing (str "Missing Environment Valid profiles: " (str/join ", " (map name allowed-env)))
    :validate [allowed-env
               (str "Valid Environment " (str/join ", " (map name allowed-env)))]]
   ["-h" "--help"]])

(defn start! [environment]
  (log/info "Environment" environment)
  (let [config (dissoc
                 (config/read-config "api/config.edn" {:profile environment}))]
    (lifecycle/start! config)))

(defn stop! [system]
  (lifecycle/stop! system))

(defn -main [& args]
  (let [{:keys [options errors help]} (config/parse-opts args cli-options)]
    (cond
      help (do (println "Usage: api [options]") (System/exit 0))
      errors (do (log/error errors)
                 (System/exit 1))
      :else (start! (:profile options)))))

(comment

  ;; This is what we read by when starting
  (config/read-config "api/config.edn" {:profile :test})

  ;; fully start the system including exit 1 on error
  ;; this is as if you started from the cli
  (def system (-main "-p" "local")) 

  ;; helpful repl tooling
  ;; if you're dev'ing you probably want to do this from a development
  ;; file so you can pass the system around after starting
  (def system (start! :local))
  (stop! system)
  )

