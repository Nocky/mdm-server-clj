(ns mdm-server.core
  (:gen-class)
  (:require [mount.core :as mount]
            [mdm-server.db.entities]
            [mdm-server.rabbitmq.connections ]
            [mdm-server.rabbitmq.consumers.heartbeats :as heartbeats]
            [mdm-server.rabbitmq.publishers.instructions]
            [mdm-server.config :as config]
            [mdm-server.web.server :as server]
            [mdm-server.db.migrations :as migrations]))

(defn -main
  [& args]
  (println "starting..")
  (mount/start)
  (migrations/init)
  (migrations/migrate))
