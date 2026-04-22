(ns ca.brokerdev.core.schema.core)

(defmulti register identity)

(defn all []
  (->> (keys (methods register))
       (mapcat #(register %))
       (vec)))
