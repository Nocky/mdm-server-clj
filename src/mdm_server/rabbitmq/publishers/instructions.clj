(ns mdm-server.rabbitmq.publishers.instructions
  (:require [mdm-server.rabbitmq.connections :refer [rabbitmq-connection rabbitmq-channel]]
            [mdm-server.db.entities.instructions :as instructions]
            [langohr.basic :as langohr-basic]
            [langohr.queue :as langohr-queue]
            [langohr.confirm :as langohr-confirm]))

(defn device-queue-name
  [client-id]
  (str "mqtt-subscription-" client-id "qos1"))

(defn publish-instruction
  [instruction]
  (instructions/update-instruction-status (:id instruction) "processing")
  (langohr-basic/publish rabbitmq-channel "" (device-queue-name (:access_token instruction)) (:instruction instruction) {:mandatory true :message-id (str (:id instruction))}))

(defn return-handler
  [reply-code reply-text exchange routing-key properties body]
  (let [instruction (first (instructions/find :id (Integer. (.getMessageId properties))))
        queue-name (device-queue-name (:access_token instruction))]
    (langohr-queue/declare rabbitmq-channel queue-name {:durable true})
    (publish-instruction instruction)))

(defn process
  []
  (let [unprocessed-instructions (instructions/find :status "unprocessed")]
    (langohr-basic/add-return-listener rabbitmq-channel return-handler)
    (for [unprocessed-instruction unprocessed-instructions] (publish-instruction unprocessed-instruction))))
