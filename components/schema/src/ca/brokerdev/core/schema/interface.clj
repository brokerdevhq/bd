(ns ca.brokerdev.core.schema.interface
  "Schema registry for Datomic/DataScript schema definitions.

   Components register their schema by implementing the `register` multimethod.
   The `all` function collects and returns all registered schemas as a single
   vector, suitable for passing to `d/transact` or `d/create-conn`.

   Example usage:

     ;; In another component's namespace:
     (ns my.component
       (:require [ca.brokerdev.core.schema.interface :as schema]))

     (defmethod schema/register :user/schema [_]
       [{:db/ident       :user/id
         :db/valueType   :db.type/uuid
         :db/cardinality :db.cardinality/one
         :db/unique      :db.unique/identity}
        {:db/ident       :user/email
         :db/valueType   :db.type/string
         :db/cardinality :db.cardinality/one}])

     ;; To get all registered schema:
     (schema/all)
     ;=> [{:db/ident :user/id ...} {:db/ident :user/email ...} ...]"
  (:require [ca.brokerdev.core.schema.core :as core]))

(def register
  "Multimethod for registering schema definitions.

   Dispatch is on the key itself (identity). Each implementation should return
   a vector of Datomic/DataScript schema attribute maps.

   Extend via:
     (defmethod schema/register :your-component/schema [_]
       [{:db/ident       :your/attr
         :db/valueType   :db.type/string
         :db/cardinality :db.cardinality/one}])"
  core/register)

(defn all
  "Returns all registered schema definitions as a single vector.

   Iterates over all registered implementations of `register` and concatenates
   their results into one collection suitable for transacting to a database."
  []
  (core/all))
