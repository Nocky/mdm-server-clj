(ns mdm-server.web.handler
  (:require  [compojure.api.sweet :refer :all]
             [ring.util.http-response :refer :all]
             [schema.core :as s]
             [mdm-server.db.entities.devices :as devices]
             [ring.swagger.ui :as swagger-ui]
             [clj-time.coerce :as c]))



(def app
  (api
   {:swagger
    {:ui "/api-docs"
     :spec "/swagger.json"
     :data {:info {:title       "MDM Server"
                   :description "MDM Server APIS"}
            :tags [{:name "api", :description "Device APIs"}]}}}

   (context "/api" []
            :tags ["api"]
            (POST "/register" []
                  :return {:access-token s/Str}
                  :body-params [device :- devices/schema]
                  :summary "Register a new device"
                  (ok {:access-token (do (println (str "Registering device"))
                                         (let [r-device (devices/create-device! device)]
                                           (str  (:id r-device))))})))))
