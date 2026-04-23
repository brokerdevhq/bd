(ns ca.brokerdev.core.http-client.interface
  (:refer-clojure :exclude [get])
  (:require [clj-http.client :as http]
            [clj-http.cookies :as cookies]))

(defn get
  [url & [req]]
  (http/get url req))

(defn post
  [url & [req]]
  (http/post url req))

(defn cookie-store []
  (cookies/cookie-store))
