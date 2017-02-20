(ns mdm-server.db.entities
  (:require [korma.db :as korma-db]
            [korma.core :as korma :refer [select insert values]]
            [mdm-server.config :refer [env]]
            [mount.core :refer [defstate start]]
            [migratus.core :as migratus]))



(declare *conn*)

(defn start-default-connection-pool
  []
  (let [conn (korma-db/create-db (korma-db/postgres (env :db)))]
    (korma-db/default-connection conn)
    conn))

(defn close-connection-pool
  []
  (.close (:datasource @(:pool *conn*))))

(defstate ^:dynamic *conn*
  :start (start-default-connection-pool)
  :stop (close-connection-pool))
