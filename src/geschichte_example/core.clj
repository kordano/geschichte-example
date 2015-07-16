(ns geschichte-example.core
  (:gen-class :main true)
  (:require [hasch.core :refer [uuid]]
            [gorilla-repl.core :as g]
            [aprint.core :refer [aprint]]
            [taoensso.timbre :as timbre]))

(timbre/refer-timbre)

(defn init-log []
  (timbre/set-config! [:appenders :standard-out :min-level] :warn))


(def eval-map
  {'(fn init [_ name]
      {:store name
       :data []})
   (fn [_ name]
     {:store name
      :data []})
   '(fn transact-entry [old entry]
      (update-in old [:data] concat entry))
   (fn [old entry]
     (update-in old [:data] concat entry))})


(defn mapped-eval [code]
  (if (eval-map code)
    (eval-map code)
    (do (debug "eval-map didn't match:" code)
        (fn [old _] old))))


(defn find-fn [name]
  (first (filter (fn [[_ fn-name]]
                   (= name fn-name))
                 (keys eval-map))))


(defn -main [& args]
  (g/run-gorilla-server {:port 44444
                         :ip "127.0.0.1"
                         :nrepl-port 47382
                         :version "0.3.4"
                         :project "geschichte example"}))
