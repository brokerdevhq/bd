(ns ca.brokerdev.core.api.core-test
  (:require [clojure.test :as test :refer [deftest is testing use-fixtures]]
            [ca.brokerdev.core.http-client.interface :as http]
            [ca.brokerdev.core.api.core :as core]))

(defonce state (atom nil))

(defn setup! []
  (let [system (core/start! :test)
        base-url (str "http://localhost:" (get-in system [:api/http-server :port]))]
    (swap! state assoc 
           :system system
           :base-url base-url)))

(defn teardown! []
  ((:system @state) core/stop!)
  (reset! state nil))

(comment
  state
  (setup!)
  (teardown!))

(defn run-test [f]
  (setup!)
  (f)
  (teardown!))

(use-fixtures :each run-test)

(defn do-health-check []
  (let [res (http/get (str (:base-url @state) "/health")
                      {:as :clojure
                       :accept :edn})]
    (is (= 200 (:status res)) "/health expected to be 200")))

(deftest health-check-test
  (testing "health-check returns 200"
    (do-health-check)))
