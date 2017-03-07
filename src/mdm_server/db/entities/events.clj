(ns mdm-server.db.entities.events
  (:require [korma.core :as korma :refer [defentity belongs-to]]
            [mdm-server.db.entities.devices :refer [devices]]
            [schema.core :as s]))

(defentity events
  (belongs-to devices {:fk :device_id}))

(s/def schema
  {(s/required-key :name) s/Str})

((defn create-event!
  [event]
  (korma/insert events (korma/values event))))
