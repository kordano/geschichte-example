(defproject geschichte-example "0.1.0-SNAPSHOT"

  :description "Simple geschichte process"

  :url "https://github.com/kordano/geschichte-example"

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/core.async "0.1.303.0-886421-alpha"]

                 [es.topiq/full.async "0.2.8-beta1"]
                 
                 [gorilla-repl "0.3.4" :exclusions [http-kit]]
                 [geschichte-gorilla "0.1.0-SNAPSHOT":exclusions [org.clojure/clojure]]

                 [com.taoensso/nippy "2.8.0"]
                 [com.taoensso/timbre "3.4.0" :exclusions [com.taoensso/encore]]

                 [aprint "0.1.3"]

                 [net.polyc0l0r/konserve "0.2.3"]
                 [es.topiq/replikativ "0.1.0-SNAPSHOT"]]

  :main geschichte-example.core

  :min-lein-version "2.0.0"
  )
