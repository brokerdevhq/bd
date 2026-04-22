(ns ca.brokerdev.core.schema.interface-test
  (:require [clojure.test :refer [deftest is testing]]
            [ca.brokerdev.core.schema.interface :as schema]))

(defmethod schema/register :test/user [_]
  [{:db/ident       :user/id
    :db/valueType   :db.type/uuid
    :db/cardinality :db.cardinality/one}
   {:db/ident       :user/email
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one}])

(deftest schema-registration
  (testing "registered schema is returned by all"
    (let [result (schema/all)]
      (is (some #(= :user/id (:db/ident %)) result))
      (is (some #(= :user/email (:db/ident %)) result)))))



