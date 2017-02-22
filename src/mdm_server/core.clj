(ns mdm-server.core
  (:gen-class)
  (:require [mount.core :as mount]
            [mdm-server.db.entities :as entities]
            [mdm-server.rabbitmq.connections]
            [mdm-server.rabbitmq.consumers.heartbeats]
            [mdm-server.config :as config]
            [mdm-server.web.server :as server]))

(defn -main
  [& args]
  (println "Starting..")
  (mount/start))
