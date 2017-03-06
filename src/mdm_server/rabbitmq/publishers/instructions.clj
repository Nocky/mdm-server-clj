(ns mdm-server.rabbitmq.publishers.instructions
  (:require [mdm-server.rabbitmq.connections :refer [rabbitmq-connection rabbitmq-channel]]
            [mdm-server.db.entities.instructions :as instructions]
            [langohr.basic :as langohr-basic]
            [mount.core :refer [defstate]]))

(declare message-return-listener)

(def tag "[publishers/instructions]")

(defn device-queue-name
  [client-id]
  (str "mqtt-subscription-" client-id "qos1"))

(defn publish-instruction
  [instruction]
  (println tag "Processing instruction " (:id instruction))
  (instructions/update-instruction-status (:id instruction) "processing")
  (langohr-basic/publish rabbitmq-channel "" (device-queue-name (:access_token instruction)) (:instruction instruction) {:mandatory true :message-id (str (:id instruction))}))

(defn process
  []
  (while true (let [unprocessed-instructions (instructions/find :status "unprocessed")]
    (doseq [unprocessed-instruction unprocessed-instructions] (publish-instruction unprocessed-instruction)))
    (Thread/sleep 1000)))

(defn start-instruction-publisher
  []
  (println tag "Starting ...")
  (.start (Thread. process)))


(defstate instruction-publisher
  :start (start-instruction-publisher))
