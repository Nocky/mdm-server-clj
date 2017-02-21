(ns mdm-server.web.handler
  (:require  [compojure.api.sweet :refer :all]
             [ring.util.http-response :refer :all]
             [schema.core :as s]
             [mdm-server.db.entities.devices :as devices]
             [ring.swagger.ui :as swagger-ui]
             [clj-time.coerce :as c]))



(defn register-device
  [device]
  (if (seq (devices/find :unique_id (:unique_id device)))
    (internal-server-error {:error "device already registered!!"})
    (ok {:access-token   (let [r-device (devices/register-device! device)]
                           (str (:access_token r-device)))})))


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
                  :return {:access-token s/Str}
                  :body-params [device :- devices/schema]
                  :summary "Register a new device"
                  (register-device device)))))
