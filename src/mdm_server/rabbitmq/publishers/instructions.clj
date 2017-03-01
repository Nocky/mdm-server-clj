(ns mdm-server.rabbitmq.publishers.instructions
  (:require [mdm-server.rabbitmq.connections :refer [rabbitmq-connection rabbitmq-channel]]
            [mdm-server.db.entities.instructions :as instructions]
            [langohr.basic :as langohr-basic]
            [langohr.confirm :as langohr-confirm]))

(defn return-handler
  [reply-code reply-text exchange routing-key properties body]
  (instructions/update-instruction-status (Integer. (.getMessageId properties)) "unprocessed"))

(defn publish-instruction
  [instruction]
  (instructions/update-instruction-status (:id instruction) "processing")
  (langohr-basic/publish rabbitmq-channel "amq.topic" (:access_token instruction) (:instruction instruction) {:mandatory true :message-id (str (:id instruction))}))

(defn process
  []
  (let [unprocessed-instructions (instructions/find :status "unprocessed")]
    (langohr-basic/add-return-listener rabbitmq-channel return-handler)
    (for [unprocessed-instruction unprocessed-instructions] (publish-instruction unprocessed-instruction))))
