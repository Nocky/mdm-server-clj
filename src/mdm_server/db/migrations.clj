(ns mdm-server.db.migrations
  (:gen-class)
  (:require [migratus.core :as migratus]
            [korma.db :as korma-db]
            [mdm-server.config :as conf]
            [mount.core :as mount]))



(mount/defstate config
  :start {:store         :database
          :migration-dir "migrations/"
          :db            (korma-db/postgres (conf/env :db))})


(defn migrate []
  (migratus/migrate config))


(defn rollback []
  (migratus/rollback config))


(defn -main [& args]
  (mount/start)
  (let [param (first args)]
    (case param
      "migrate" (migrate)
      "rollback" (rollback))))
