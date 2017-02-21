(ns mdm-server.db.entities.devices
  (:require [korma.core :as korma :refer [defentity select ]]
            [schema.core :as s]
            [clj-time.core :as time]
            [mdm-server.db.ext]
            [mdm-server.db.entities.common-specs :refer :all]
            [schema.spec.core :as spec]
            [clj-time.coerce :as c])
  (:import java.util.UUID))



(defentity devices)

(s/def schema
  {(s/optional-key  :model)      s/Str
   (s/required-key :unique_id )  s/Str
   (s/required-key :imei_number) s/Str
   (s/optional-key  :os_version) s/Str
   (s/optional-key :last_seen)   org.joda.time.DateTime})


(defn create-device!
  [device]
  (korma/insert devices (korma/values device)))

(defn register-device!
  [params]
  (let [uuid (java.util.UUID/randomUUID)
        device (create-device! (merge params {:access_token uuid}))]
    device))


(defn find [key val]
  (korma/select devices (korma/where (= key val))))
