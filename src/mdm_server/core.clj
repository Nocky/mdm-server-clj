(ns mdm-server.core
  (:gen-class)
  (:require [mount.core :as mount]
            [mdm-server.db.entities :as entities]
            [mdm-server.config :as config]
            [mdm-server.web.server :as server]))

(defn -main
  [& args]
  (println "starting..")
  (mount/start))
