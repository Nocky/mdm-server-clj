(ns mdm-server.db.migrations
  (:gen-class)
  (:require [migratus.core :as migratus]
            [korma.db :as korma-db]
            [mdm-server.config :as conf]
            [mount.core :as mount]))



(mount/defstate config
  :start {:store         :database
          :migration-dir "migrations/"
          :init-script   "init.sql" })


(defn migrate []
  (migratus/migrate (merge  config {:db (korma-db/postgres (conf/env :db))})))

(defn init
  []
  (let [config (merge config {:db (korma-db/postgres (dissoc (conf/env :db) :db))})]
    (migratus/init config)))

(defn rollback []
  (migratus/rollback (merge  config {:db (korma-db/postgres (conf/env :db))})))


(defn -main [& args]
  (mount/start)
  (let [param (first args)]
    (case param
      "migrate" (migrate)
      "rollback" (rollback))))
