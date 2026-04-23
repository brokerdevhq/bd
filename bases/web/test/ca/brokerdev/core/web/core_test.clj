(ns ca.brokerdev.core.web.core-test
  (:require [clojure.test :as test :refer [deftest is testing use-fixtures]]
            [ca.brokerdev.core.web.core :as core]
            [ca.brokerdev.core.http-client.interface :as http]))

(defonce state (atom nil))

(defn setup! []
  (let [system (core/start! :test)
        base-url (str "http://localhost:" (get-in system [:web/http-server :port]))]
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

(defn do-home-check []
  (let [res (http/get (str (:base-url @state) "/")
                      {:as :clojure
                       :accept :edn})]
    (is (= 200 (:status res)) "/ expected to be 200")))

(deftest health-checks
  (testing "health-check returns 200"
    (do-health-check))
  
  (testing "home returns 200"
    (do-home-check)))


