(ns mdm-server.db.entities.instructions
  (:require [korma.core :as korma :refer [defentity belongs-to]]
            [mdm-server.db.entities.devices :refer [devices]]
            [schema.core :as s]))

(defentity instructions
  (belongs-to devices {:fk :device_id}))

(s/def schema
  {(s/required-key :status) s/Str
   (s/optional-key :instruction) s/Str})

(defn find [key val]
  (korma/select instructions (korma/with devices) (korma/where (= key val))))

(defn create-instruction!
  [instruction]
  (korma/insert instructions (korma/values instruction)))

(defn update-instruction-status
  [id status]
  (korma/update instructions
    (korma/set-fields {:status status})
    (korma/where {:id id})))
