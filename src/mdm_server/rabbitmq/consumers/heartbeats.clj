(ns mdm-server.rabbitmq.consumers.heartbeats
  (:require [mdm-server.rabbitmq.connections :refer [rabbitmq-connection rabbitmq-channel]]
            [mdm-server.db.entities.devices :refer [update-device-last-seen]]
            [langohr.queue :as langohr-queue]
            [langohr.consumers :as langohr-consumers]
            [cheshire.core :refer [parse-string]]))

(defn heartbeats-handler
  [channel metadata ^bytes payload]
  (let [parsed-payload (parse-string (String. payload "UTF-8") true)]
      (update-device-last-seen (:unique_id parsed-payload)) true))

(defn consume
  []
  (let [queue-name "events"]
    (langohr-consumers/subscribe rabbitmq-channel queue-name heartbeats-handler {:auto-ack true})))
