(ns mdm-server.config
  (:require [cprop.core :refer [load-config]]
            [cprop.source :as source]
            [mount.core :refer [defstate args]]))

(defstate env :start (load-config))
                                        ;                      :merge
                                        ;                      :merge
                                        ;                      [(args)
                                        ;                       (source/from-system-props)
                                        ;                       (source/from-env)]))
