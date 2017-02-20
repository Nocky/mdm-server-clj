(ns mdm-server.db.entities.devices
  (:require [korma.core :as korma :refer [defentity select ]]
            [clojure.spec :as s]
            [mdm-server.db.entities.common-specs :refer :all]))

(defentity devices)
