(ns mdm-server.web.server
  (:require [ring.adapter.jetty :as jetty]
            [mount.core :as mount]
            [mdm-server.web.handler :as handler :refer [app]]
            [mdm-server.config :as conf]))



(mount/defstate server
  :start (jetty/run-jetty app {:port (get conf/env :http-port 3000)})
  :stop (.stop server))
