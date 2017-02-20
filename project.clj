(defproject mdm-server "0.1.0-SNAPSHOT"
  :description "oneMDM Backend"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [korma "0.4.0"]
                 [postgresql/postgresql "9.3-1102.jdbc41"]
                 [migratus "0.8.13"]
                 [korma "0.4.3"]
                 [cprop "0.1.10"]
                 [mount "0.1.11"]
                 [clojure-future-spec "1.9.0-alpha14"]
                 [clj-time "0.13.0"]]

  :plugins [[migratus-lein "0.4.2"]]


  :source-paths ["src"]
  :resource-paths ["resources"]

  :main ^:skip-aot mdm-server.core
  :target-path "target/%s"
  :profiles

  {:uberjar       {:omit-source true
                   :aot :all
                   :uberjar-name "mdm-server.jar"
                   :source-paths ["env/prod/clj"]
                   :resource-paths ["env/prod/resources"]}

   :dev           [:project/dev :profiles/dev]
   :test          [:project/dev :project/test :profiles/test]

   :project/dev   {
                   :dependencies   [[org.clojure/tools.namespace "0.2.11"]]
                   :source-paths   ["env/dev/clj" "test/clj"]
                   :resource-paths ["env/dev/resources"]
                   :repl-options   {:init-ns mdm-server.db.entities}
                   }
   :project/test  {:resource-paths ["env/test/resources"]}
   :profiles/dev  {}
   :profiles/test {}})
