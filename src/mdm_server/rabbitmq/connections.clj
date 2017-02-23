(ns mdm-server.rabbitmq.connections
  (:require [langohr.core]
            [langohr.channel :as langohr-channel]
            [mdm-server.config :refer [env]]
            [mount.core :refer [defstate start]]))

(declare rabbitmq-connection)
(declare rabbitmq-channel)

(defn start-rabbitmq-connection
  []
  (let [rabbitmq-connection (do
                              (println "sleeping for 5 secs.... --- ******************************")
                              (Thread/sleep 5000)
                              (langohr.core/connect (env :rabbitmq)))]
    rabbitmq-connection))

(defn establish-rabbitmq-channel
  []
  (let [rabbitmq-channel (langohr-channel/open rabbitmq-connection)]
    rabbitmq-channel))

(defn close-rabbitmq-connection
  []
  (langohr.core/close rabbitmq-connection))

(defstate rabbitmq-connection
  :start (start-rabbitmq-connection)
  :stop (close-rabbitmq-connection))

(defstate rabbitmq-channel
  :start (establish-rabbitmq-channel))
