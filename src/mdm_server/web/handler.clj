(ns mdm-server.web.handler
  (:require  [compojure.api.sweet :refer :all]
             [ring.util.http-response :refer :all]
             [schema.core :as s]
             [mdm-server.db.entities.devices :as devices]
             [ring.swagger.ui :as swagger-ui]
             [clj-time.coerce :as c]))



(defn register-device
  [params]
  (ok
   {:access_token
    (str (:access_token
          (if-let [device (first  (devices/find :unique_id (:unique_id params)))]
            (devices/re-register-device! device params)
            (devices/register-device! params))))}))


(def app
  (api
   {:swagger
    {:ui "/api-docs"
     :spec "/swagger.json"
     :data {:info {:title  "MDM Server"
                   :description "MDM Server APIS"}
            :tags [{:name "api", :description "Device APIs"}]}}}

   (context "/api" []
            :tags ["api"]
            (POST "/register" []
                  :return {:access_token s/Str}
                  :body-params [device :- devices/schema]
                  :summary "Register a new device"
                  (register-device device)))))
