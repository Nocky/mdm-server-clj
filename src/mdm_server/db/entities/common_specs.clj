(ns mdm-server.db.entities.common-specs
  (:require  [clojure.spec :as s])
  (:import [java.util.Date]))

(s/def ::date #(instance? java.util.Date %))
