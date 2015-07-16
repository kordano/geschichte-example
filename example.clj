;; gorilla-repl.fileformat = 1

;; **
;;; # Replikativ Example Pipeline
;; **

;; @@
(ns harmonious-creek
  (:require [geschichte-example.core :refer :all]
            [geschichte-gorilla.core :as g]
            [gorilla-repl.vega :as v]
            [hasch.core :refer [uuid]]
            [konserve.store :refer [new-mem-store]]
            [konserve.protocols :refer [-get-in -assoc-in -update-in -bassoc]]
            [full.async :refer [<??]]
            [replikativ.core :refer [server-peer client-peer]]
            [replikativ.stage :as s]
            [replikativ.p2p.fetch :refer [fetch]]
            [replikativ.p2p.hash :refer [ensure-hash]]
            [replikativ.p2p.block-detector :refer [block-detector]]
            [replikativ.platform :refer [create-http-kit-handler! start]]
            [replikativ.crdt.repo.realize :refer [branch-value commit-history]]
            [replikativ.crdt.repo.stage :as rs]
            [clojure.core.async :refer [>!!]]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Define *user* and *socket* address.
;; **

;; @@
(def user "kordano@topiq.es")
(def socket "ws://127.0.0.1:37050")
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;harmonious-creek/socket</span>","value":"#'harmonious-creek/socket"}
;; <=

;; **
;;; Create new in-memory konserve *store*.
;; **

;; @@
(def store (<?? (new-mem-store)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;harmonious-creek/store</span>","value":"#'harmonious-creek/store"}
;; <=

;; **
;;; Fire up *peer*.
;; **

;; @@
(def peer (server-peer (create-http-kit-handler! socket)
                                 store
                                 (comp (partial block-detector :peer-core)
                                       (partial fetch store)
                                       ensure-hash
                                       (partial block-detector :p2p-surface))))
(start peer)
;; @@
;; ->
;;; starting ws://127.0.0.1:37050
;;; 
;; <-
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:volatile</span>","value":":volatile"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:new-conns</span>","value":":new-conns"},{"type":"html","content":"<span class='clj-unkown'>#object[clojure.core.async.impl.channels.ManyToManyChannel 0x1d494e25 &quot;clojure.core.async.impl.channels.ManyToManyChannel@1d494e25&quot;]</span>","value":"#object[clojure.core.async.impl.channels.ManyToManyChannel 0x1d494e25 \"clojure.core.async.impl.channels.ManyToManyChannel@1d494e25\"]"}],"value":"[:new-conns #object[clojure.core.async.impl.channels.ManyToManyChannel 0x1d494e25 \"clojure.core.async.impl.channels.ManyToManyChannel@1d494e25\"]]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:store</span>","value":":store"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:state</span>","value":":state"},{"type":"html","content":"<span class='clj-atom'>#object[clojure.lang.Atom 0x66381a52 {:status :ready, :val {}}]</span>","value":"#object[clojure.lang.Atom 0x66381a52 {:status :ready, :val {}}]"}],"value":"[:state #object[clojure.lang.Atom 0x66381a52 {:status :ready, :val {}}]]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:tag-table</span>","value":":tag-table"},{"type":"html","content":"<span class='clj-atom'>#object[clojure.lang.Atom 0x6d4831eb {:status :ready, :val {}}]</span>","value":"#object[clojure.lang.Atom 0x6d4831eb {:status :ready, :val {}}]"}],"value":"[:tag-table #object[clojure.lang.Atom 0x6d4831eb {:status :ready, :val {}}]]"}],"value":"#konserve.store.MemAsyncKeyValueStore{:state #object[clojure.lang.Atom 0x66381a52 {:status :ready, :val {}}], :tag-table #object[clojure.lang.Atom 0x6d4831eb {:status :ready, :val {}}]}"}],"value":"[:store #konserve.store.MemAsyncKeyValueStore{:state #object[clojure.lang.Atom 0x66381a52 {:status :ready, :val {}}], :tag-table #object[clojure.lang.Atom 0x6d4831eb {:status :ready, :val {}}]}]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:server</span>","value":":server"},{"type":"html","content":"<span class='clj-unkown'>#object[clojure.lang.AFunction$1 0x454c2a6d &quot;clojure.lang.AFunction$1@454c2a6d&quot;]</span>","value":"#object[clojure.lang.AFunction$1 0x454c2a6d \"clojure.lang.AFunction$1@454c2a6d\"]"}],"value":"[:server #object[clojure.lang.AFunction$1 0x454c2a6d \"clojure.lang.AFunction$1@454c2a6d\"]]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:error-ch</span>","value":":error-ch"},{"type":"html","content":"<span class='clj-unkown'>#object[clojure.core.async.impl.channels.ManyToManyChannel 0xb757da6 &quot;clojure.core.async.impl.channels.ManyToManyChannel@b757da6&quot;]</span>","value":"#object[clojure.core.async.impl.channels.ManyToManyChannel 0xb757da6 \"clojure.core.async.impl.channels.ManyToManyChannel@b757da6\"]"}],"value":"[:error-ch #object[clojure.core.async.impl.channels.ManyToManyChannel 0xb757da6 \"clojure.core.async.impl.channels.ManyToManyChannel@b757da6\"]]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:chans</span>","value":":chans"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>#object[clojure.core.async.impl.channels.ManyToManyChannel 0x4e8c232a &quot;clojure.core.async.impl.channels.ManyToManyChannel@4e8c232a&quot;]</span>","value":"#object[clojure.core.async.impl.channels.ManyToManyChannel 0x4e8c232a \"clojure.core.async.impl.channels.ManyToManyChannel@4e8c232a\"]"},{"type":"html","content":"<span class='clj-unkown'>#object[clojure.core.async$pub$reify__21395 0x41559965 &quot;clojure.core.async$pub$reify__21395@41559965&quot;]</span>","value":"#object[clojure.core.async$pub$reify__21395 0x41559965 \"clojure.core.async$pub$reify__21395@41559965\"]"}],"value":"[#object[clojure.core.async.impl.channels.ManyToManyChannel 0x4e8c232a \"clojure.core.async.impl.channels.ManyToManyChannel@4e8c232a\"] #object[clojure.core.async$pub$reify__21395 0x41559965 \"clojure.core.async$pub$reify__21395@41559965\"]]"}],"value":"[:chans [#object[clojure.core.async.impl.channels.ManyToManyChannel 0x4e8c232a \"clojure.core.async.impl.channels.ManyToManyChannel@4e8c232a\"] #object[clojure.core.async$pub$reify__21395 0x41559965 \"clojure.core.async$pub$reify__21395@41559965\"]]]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:middleware</span>","value":":middleware"},{"type":"html","content":"<span class='clj-unkown'>#object[clojure.core$comp$fn__4495 0x795c5673 &quot;clojure.core$comp$fn__4495@795c5673&quot;]</span>","value":"#object[clojure.core$comp$fn__4495 0x795c5673 \"clojure.core$comp$fn__4495@795c5673\"]"}],"value":"[:middleware #object[clojure.core$comp$fn__4495 0x795c5673 \"clojure.core$comp$fn__4495@795c5673\"]]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:url</span>","value":":url"},{"type":"html","content":"<span class='clj-string'>&quot;ws://127.0.0.1:37050&quot;</span>","value":"\"ws://127.0.0.1:37050\""}],"value":"[:url \"ws://127.0.0.1:37050\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:channel-hub</span>","value":":channel-hub"},{"type":"html","content":"<span class='clj-atom'>#object[clojure.lang.Atom 0x1d2fd644 {:status :ready, :val {}}]</span>","value":"#object[clojure.lang.Atom 0x1d2fd644 {:status :ready, :val {}}]"}],"value":"[:channel-hub #object[clojure.lang.Atom 0x1d2fd644 {:status :ready, :val {}}]]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:handler</span>","value":":handler"},{"type":"html","content":"<span class='clj-unkown'>#object[replikativ.platform$create_http_kit_handler_BANG_$handler__25855 0x3142f237 &quot;replikativ.platform$create_http_kit_handler_BANG_$handler__25855@3142f237&quot;]</span>","value":"#object[replikativ.platform$create_http_kit_handler_BANG_$handler__25855 0x3142f237 \"replikativ.platform$create_http_kit_handler_BANG_$handler__25855@3142f237\"]"}],"value":"[:handler #object[replikativ.platform$create_http_kit_handler_BANG_$handler__25855 0x3142f237 \"replikativ.platform$create_http_kit_handler_BANG_$handler__25855@3142f237\"]]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:log</span>","value":":log"},{"type":"html","content":"<span class='clj-atom'>#object[clojure.lang.Atom 0x222c1225 {:status :ready, :val {}}]</span>","value":"#object[clojure.lang.Atom 0x222c1225 {:status :ready, :val {}}]"}],"value":"[:log #object[clojure.lang.Atom 0x222c1225 {:status :ready, :val {}}]]"}],"value":"{:new-conns #object[clojure.core.async.impl.channels.ManyToManyChannel 0x1d494e25 \"clojure.core.async.impl.channels.ManyToManyChannel@1d494e25\"], :store #konserve.store.MemAsyncKeyValueStore{:state #object[clojure.lang.Atom 0x66381a52 {:status :ready, :val {}}], :tag-table #object[clojure.lang.Atom 0x6d4831eb {:status :ready, :val {}}]}, :server #object[clojure.lang.AFunction$1 0x454c2a6d \"clojure.lang.AFunction$1@454c2a6d\"], :error-ch #object[clojure.core.async.impl.channels.ManyToManyChannel 0xb757da6 \"clojure.core.async.impl.channels.ManyToManyChannel@b757da6\"], :chans [#object[clojure.core.async.impl.channels.ManyToManyChannel 0x4e8c232a \"clojure.core.async.impl.channels.ManyToManyChannel@4e8c232a\"] #object[clojure.core.async$pub$reify__21395 0x41559965 \"clojure.core.async$pub$reify__21395@41559965\"]], :middleware #object[clojure.core$comp$fn__4495 0x795c5673 \"clojure.core$comp$fn__4495@795c5673\"], :url \"ws://127.0.0.1:37050\", :channel-hub #object[clojure.lang.Atom 0x1d2fd644 {:status :ready, :val {}}], :handler #object[replikativ.platform$create_http_kit_handler_BANG_$handler__25855 0x3142f237 \"replikativ.platform$create_http_kit_handler_BANG_$handler__25855@3142f237\"], :log #object[clojure.lang.Atom 0x222c1225 {:status :ready, :val {}}]}"}],"value":"[:volatile {:new-conns #object[clojure.core.async.impl.channels.ManyToManyChannel 0x1d494e25 \"clojure.core.async.impl.channels.ManyToManyChannel@1d494e25\"], :store #konserve.store.MemAsyncKeyValueStore{:state #object[clojure.lang.Atom 0x66381a52 {:status :ready, :val {}}], :tag-table #object[clojure.lang.Atom 0x6d4831eb {:status :ready, :val {}}]}, :server #object[clojure.lang.AFunction$1 0x454c2a6d \"clojure.lang.AFunction$1@454c2a6d\"], :error-ch #object[clojure.core.async.impl.channels.ManyToManyChannel 0xb757da6 \"clojure.core.async.impl.channels.ManyToManyChannel@b757da6\"], :chans [#object[clojure.core.async.impl.channels.ManyToManyChannel 0x4e8c232a \"clojure.core.async.impl.channels.ManyToManyChannel@4e8c232a\"] #object[clojure.core.async$pub$reify__21395 0x41559965 \"clojure.core.async$pub$reify__21395@41559965\"]], :middleware #object[clojure.core$comp$fn__4495 0x795c5673 \"clojure.core$comp$fn__4495@795c5673\"], :url \"ws://127.0.0.1:37050\", :channel-hub #object[clojure.lang.Atom 0x1d2fd644 {:status :ready, :val {}}], :handler #object[replikativ.platform$create_http_kit_handler_BANG_$handler__25855 0x3142f237 \"replikativ.platform$create_http_kit_handler_BANG_$handler__25855@3142f237\"], :log #object[clojure.lang.Atom 0x222c1225 {:status :ready, :val {}}]}]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:name</span>","value":":name"},{"type":"html","content":"<span class='clj-string'>&quot;ws://127.0.0.1:37050&quot;</span>","value":"\"ws://127.0.0.1:37050\""}],"value":"[:name \"ws://127.0.0.1:37050\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:subscriptions</span>","value":":subscriptions"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[],"value":"{}"}],"value":"[:subscriptions {}]"}],"value":"{:volatile {:new-conns #object[clojure.core.async.impl.channels.ManyToManyChannel 0x1d494e25 \"clojure.core.async.impl.channels.ManyToManyChannel@1d494e25\"], :store #konserve.store.MemAsyncKeyValueStore{:state #object[clojure.lang.Atom 0x66381a52 {:status :ready, :val {}}], :tag-table #object[clojure.lang.Atom 0x6d4831eb {:status :ready, :val {}}]}, :server #object[clojure.lang.AFunction$1 0x454c2a6d \"clojure.lang.AFunction$1@454c2a6d\"], :error-ch #object[clojure.core.async.impl.channels.ManyToManyChannel 0xb757da6 \"clojure.core.async.impl.channels.ManyToManyChannel@b757da6\"], :chans [#object[clojure.core.async.impl.channels.ManyToManyChannel 0x4e8c232a \"clojure.core.async.impl.channels.ManyToManyChannel@4e8c232a\"] #object[clojure.core.async$pub$reify__21395 0x41559965 \"clojure.core.async$pub$reify__21395@41559965\"]], :middleware #object[clojure.core$comp$fn__4495 0x795c5673 \"clojure.core$comp$fn__4495@795c5673\"], :url \"ws://127.0.0.1:37050\", :channel-hub #object[clojure.lang.Atom 0x1d2fd644 {:status :ready, :val {}}], :handler #object[replikativ.platform$create_http_kit_handler_BANG_$handler__25855 0x3142f237 \"replikativ.platform$create_http_kit_handler_BANG_$handler__25855@3142f237\"], :log #object[clojure.lang.Atom 0x222c1225 {:status :ready, :val {}}]}, :name \"ws://127.0.0.1:37050\", :subscriptions {}}"}
;; <=

;; **
;;; Create a local *stage* and connect it to the peer.
;; **

;; @@
(def stage (<?? (s/create-stage! user peer eval)))
(<?? (s/connect! stage socket))
;; @@
;; ->
;;; 2015-Jul-13 18:58:06 +0200 phobos INFO [replikativ.platform-log] - (ws://127.0.0.1:37050 connecting to: ws://127.0.0.1:37050)
;;; 2015-Jul-13 18:58:07 +0200 phobos DEBUG [replikativ.platform-log] - (connect: backsubscription? #uuid &quot;2145ebf8-0daf-4b59-badb-9613c2dda9b3&quot; {:type :sub/identities-ack, :identities {}, :peer ws://127.0.0.1:37050, :id #uuid &quot;2145ebf8-0daf-4b59-badb-9613c2dda9b3&quot;})
;;; 2015-Jul-13 18:58:07 +0200 phobos INFO [replikativ.platform-log] - (connect!: connected  ws://127.0.0.1:37050)
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Now we can create a local *repo* which we can use to commit our data.
;; **

;; @@
(def repo (<?? (rs/create-repo! stage :description "cake collection")))
;; @@
;; ->
;;; 2015-Jul-13 18:58:08 +0200 phobos DEBUG [replikativ.platform-log] - (creating new repo for  kordano@topiq.es with id #uuid &quot;cedf0d95-9c35-4ce9-a237-ac064f5a340c&quot;)
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;harmonious-creek/repo</span>","value":"#'harmonious-creek/repo"}
;; <=

;; **
;;; In *geschichte-example.core* we defined the eval-map and some helper-functions. Now we initialize the repo with the basic data structure which will look like this:
;; **

;; @@
{:name "cakes"
 :data []}
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:name</span>","value":":name"},{"type":"html","content":"<span class='clj-string'>&quot;cakes&quot;</span>","value":"\"cakes\""}],"value":"[:name \"cakes\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:data</span>","value":":data"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[],"value":"[]"}],"value":"[:data []]"}],"value":"{:name \"cakes\", :data []}"}
;; <=

;; **
;;; Let's *transact* and *commit* that in the stage.
;; **

;; @@
(<?? (rs/transact stage [user repo "master"] [[(find-fn 'init) "cakes"]]))
(<?? (rs/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Jul-13 18:58:12 +0200 phobos INFO [replikativ.platform-log] - (transact: new stage value after trans  [[(fn init [_ name] {:store name, :data []}) cakes]] : 
;;;  {kordano@topiq.es {#uuid &quot;cedf0d95-9c35-4ce9-a237-ac064f5a340c&quot; {master :foo}}})
;;; 2015-Jul-13 18:58:12 +0200 phobos DEBUG [replikativ.platform-log] - (committing to  master :  #uuid &quot;1261f65e-d043-5424-972a-a1a4bc7101a2&quot; {:transactions [[#uuid &quot;057e1301-3e1a-5292-8e71-7b9fd9c5ef3a&quot; #uuid &quot;18e24702-45d1-52ac-aa57-bcd0f30c09c8&quot;]], :ts #inst &quot;2015-07-13T16:58:12.209-00:00&quot;, :branch master, :parents [#uuid &quot;3004b2bd-3dd9-5524-a09c-2da166ffad6a&quot;], :crdt :repo, :version 1, :author kordano@topiq.es, :crdt-refs #{}})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Now let's see what this looks like:
;; **

;; @@
(defn plot-graph [stage]
  (v/vega-view 
  (g/vega-commit-graph
    (get-in @stage [user repo :state :commit-graph])
    (get-in @stage [user repo :state :branches])
    (into {} 
                    (mapcat
                      (fn [br]
                        (map 
                          (fn [id] 
                            [id br]) 
                          (commit-history 
                            (get-in @stage [user repo :state :commit-graph])
                            (first (get-in @stage [user repo :state :branches br])))))
                      (keys (get-in @stage [user repo :state :branches])))))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;harmonious-creek/plot-graph</span>","value":"#'harmonious-creek/plot-graph"}
;; <=

;; @@
(plot-graph stage)
;; @@
;; =>
;;; {"type":"vega","content":{"width":600,"height":370.82818603515625,"padding":{"top":10,"left":50,"bottom":20,"right":10},"marks":[{"type":"path","from":{"data":"links"},"properties":{"enter":{"path":{"field":"data.path"},"strokeWidth":{"value":2},"stroke":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}}}}},{"type":"symbol","from":{"data":"nodes"},"properties":{"enter":{"x":{"field":"data.x"},"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"}},"update":{"shape":"circle","size":{"value":110},"stroke":["value","transparent"]}}},{"type":"text","from":{"data":"labels"},"properties":{"enter":{"x":{"field":"data.x"},"y":{"field":"data.y"},"align":{"value":"left"},"dx":{"value":10},"fontSize":{"value":15},"fill":{"value":"black"}},"update":{"text":{"field":"data.value"}}}}],"data":[{"name":"nodes","values":[{"value":"3004b2bd-3dd9-5524-a09c-2da166ffad6a","x":30.0,"y":185.41409301757812,"r":182,"g":240,"b":200},{"value":"1261f65e-d043-5424-972a-a1a4bc7101a2","x":570.0,"y":185.41409301757812,"r":182,"g":240,"b":200}]},{"name":"links","values":[{"path":"M 30.000000447034836 185.41409301757812 L 569.9999928474426 185.41409301757812","r":182,"g":240,"b":200}]},{"name":"labels","values":[{"value":"master","x":570.0,"y":185.41409301757812}]}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 600, :height 370.8282, :padding {:top 10, :left 50, :bottom 20, :right 10}, :marks [{:type \"path\", :from {:data \"links\"}, :properties {:enter {:path {:field \"data.path\"}, :strokeWidth {:value 2}, :stroke {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}}}} {:type \"symbol\", :from {:data \"nodes\"}, :properties {:enter {:x {:field \"data.x\"}, :y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}}, :update {:shape \"circle\", :size {:value 110}, :stroke [:value \"transparent\"]}}} {:type \"text\", :from {:data \"labels\"}, :properties {:enter {:x {:field \"data.x\"}, :y {:field \"data.y\"}, :align {:value \"left\"}, :dx {:value 10}, :fontSize {:value 15}, :fill {:value \"black\"}}, :update {:text {:field \"data.value\"}}}}], :data [{:name \"nodes\", :values [{:value #uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\", :x 30.0, :y 185.41409301757812, :r 182, :g 240, :b 200} {:value #uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\", :x 570.0, :y 185.41409301757812, :r 182, :g 240, :b 200}]} {:name \"links\", :values [{:path \"M 30.000000447034836 185.41409301757812 L 569.9999928474426 185.41409301757812\", :r 182, :g 240, :b 200}]} {:name \"labels\", :values [{:value \"master\", :x 570.0, :y 185.41409301757812}]}]}}"}
;; <=

;; **
;;; Commit some more:
;; **

;; @@
(<?? (rs/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "apple cake" :baker "eve"}]]]))
(<?? (rs/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Jul-13 18:58:20 +0200 phobos INFO [replikativ.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:name apple cake, :baker eve}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;cedf0d95-9c35-4ce9-a237-ac064f5a340c&quot; {master :foo}}})
;;; 2015-Jul-13 18:58:20 +0200 phobos DEBUG [replikativ.platform-log] - (committing to  master :  #uuid &quot;2499b250-8d7e-56b3-9d38-7a27a69b1450&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;2d5727fa-06a9-510a-b108-21a32241ea5c&quot;]], :ts #inst &quot;2015-07-13T16:58:20.161-00:00&quot;, :branch master, :parents [#uuid &quot;1261f65e-d043-5424-972a-a1a4bc7101a2&quot;], :crdt :repo, :version 1, :author kordano@topiq.es, :crdt-refs #{}})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<?? (rs/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "butter cake" :baker "adam"}]]]))
(<?? (rs/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Jul-13 18:58:21 +0200 phobos INFO [replikativ.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:name butter cake, :baker adam}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;cedf0d95-9c35-4ce9-a237-ac064f5a340c&quot; {master :foo}}})
;;; 2015-Jul-13 18:58:21 +0200 phobos DEBUG [replikativ.platform-log] - (committing to  master :  #uuid &quot;251e51c9-5d14-54af-8abe-a8a83f56c95c&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;0a524374-b1db-5e1c-8c5b-bff4bd0072af&quot;]], :ts #inst &quot;2015-07-13T16:58:21.923-00:00&quot;, :branch master, :parents [#uuid &quot;2499b250-8d7e-56b3-9d38-7a27a69b1450&quot;], :crdt :repo, :version 1, :author kordano@topiq.es, :crdt-refs #{}})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(plot-graph stage)
;; @@
;; =>
;;; {"type":"vega","content":{"width":600,"height":370.82818603515625,"padding":{"top":10,"left":50,"bottom":20,"right":10},"marks":[{"type":"path","from":{"data":"links"},"properties":{"enter":{"path":{"field":"data.path"},"strokeWidth":{"value":2},"stroke":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}}}}},{"type":"symbol","from":{"data":"nodes"},"properties":{"enter":{"x":{"field":"data.x"},"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"}},"update":{"shape":"circle","size":{"value":110},"stroke":["value","transparent"]}}},{"type":"text","from":{"data":"labels"},"properties":{"enter":{"x":{"field":"data.x"},"y":{"field":"data.y"},"align":{"value":"left"},"dx":{"value":10},"fontSize":{"value":15},"fill":{"value":"black"}},"update":{"text":{"field":"data.value"}}}}],"data":[{"name":"nodes","values":[{"value":"3004b2bd-3dd9-5524-a09c-2da166ffad6a","x":30.0,"y":185.41409301757812,"r":169,"g":84,"b":210},{"value":"1261f65e-d043-5424-972a-a1a4bc7101a2","x":210.0000000000002,"y":185.41409301757812,"r":169,"g":84,"b":210},{"value":"2499b250-8d7e-56b3-9d38-7a27a69b1450","x":390.0000000000002,"y":185.41409301757812,"r":169,"g":84,"b":210},{"value":"251e51c9-5d14-54af-8abe-a8a83f56c95c","x":570.0,"y":185.41409301757812,"r":169,"g":84,"b":210}]},{"name":"links","values":[{"path":"M 30.000000447034836 185.41409301757812 L 209.9999964237213 185.41409301757812","r":169,"g":84,"b":210},{"path":"M 209.9999964237213 185.41409301757812 L 389.99998569488525 185.41409301757812","r":169,"g":84,"b":210},{"path":"M 389.99998569488525 185.41409301757812 L 569.9999928474426 185.41409301757812","r":169,"g":84,"b":210}]},{"name":"labels","values":[{"value":"master","x":570.0,"y":185.41409301757812}]}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 600, :height 370.8282, :padding {:top 10, :left 50, :bottom 20, :right 10}, :marks [{:type \"path\", :from {:data \"links\"}, :properties {:enter {:path {:field \"data.path\"}, :strokeWidth {:value 2}, :stroke {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}}}} {:type \"symbol\", :from {:data \"nodes\"}, :properties {:enter {:x {:field \"data.x\"}, :y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}}, :update {:shape \"circle\", :size {:value 110}, :stroke [:value \"transparent\"]}}} {:type \"text\", :from {:data \"labels\"}, :properties {:enter {:x {:field \"data.x\"}, :y {:field \"data.y\"}, :align {:value \"left\"}, :dx {:value 10}, :fontSize {:value 15}, :fill {:value \"black\"}}, :update {:text {:field \"data.value\"}}}}], :data [{:name \"nodes\", :values [{:value #uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\", :x 30.0, :y 185.41409301757812, :r 169, :g 84, :b 210} {:value #uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\", :x 210.0000000000002, :y 185.41409301757812, :r 169, :g 84, :b 210} {:value #uuid \"2499b250-8d7e-56b3-9d38-7a27a69b1450\", :x 390.0000000000002, :y 185.41409301757812, :r 169, :g 84, :b 210} {:value #uuid \"251e51c9-5d14-54af-8abe-a8a83f56c95c\", :x 570.0, :y 185.41409301757812, :r 169, :g 84, :b 210}]} {:name \"links\", :values [{:path \"M 30.000000447034836 185.41409301757812 L 209.9999964237213 185.41409301757812\", :r 169, :g 84, :b 210} {:path \"M 209.9999964237213 185.41409301757812 L 389.99998569488525 185.41409301757812\", :r 169, :g 84, :b 210} {:path \"M 389.99998569488525 185.41409301757812 L 569.9999928474426 185.41409301757812\", :r 169, :g 84, :b 210}]} {:name \"labels\", :values [{:value \"master\", :x 570.0, :y 185.41409301757812}]}]}}"}
;; <=

;; @@
(<?? (rs/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "cheese cake" :baker "eve"}]]]))
(<?? (rs/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Jul-13 18:58:29 +0200 phobos INFO [replikativ.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:name cheese cake, :baker eve}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;cedf0d95-9c35-4ce9-a237-ac064f5a340c&quot; {master :foo}}})
;;; 2015-Jul-13 18:58:29 +0200 phobos DEBUG [replikativ.platform-log] - (committing to  master :  #uuid &quot;2f887e43-5bdb-5579-8cf4-e5670850decb&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;3edf069a-073b-5023-a652-965ed4ff3113&quot;]], :ts #inst &quot;2015-07-13T16:58:29.384-00:00&quot;, :branch master, :parents [#uuid &quot;251e51c9-5d14-54af-8abe-a8a83f56c95c&quot;], :crdt :repo, :version 1, :author kordano@topiq.es, :crdt-refs #{}})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Let's make a *branch* for desserts
;; **

;; @@
(<?? (rs/branch! stage
                  [user repo]
                  "desserts"
                  (first (get-in @stage [user repo :state :branches "master"]))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<?? (rs/checkout! stage [user repo] "desserts"))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<?? (rs/transact stage [user repo "desserts"] [[(find-fn 'transact-entry) [{:name "chocolate strawberries" :baker "eve"}]]]))
(<?? (rs/commit! stage {user {repo #{"desserts"}}}))
;; @@
;; ->
;;; 2015-Jul-13 18:58:35 +0200 phobos INFO [replikativ.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:name chocolate strawberries, :baker eve}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;cedf0d95-9c35-4ce9-a237-ac064f5a340c&quot; {master :foo, desserts :foo}}})
;;; 2015-Jul-13 18:58:35 +0200 phobos DEBUG [replikativ.platform-log] - (committing to  desserts :  #uuid &quot;01750728-d771-5bb0-8cef-659acad25a95&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;1ceab641-dd8c-55fd-9a9d-008495955e6d&quot;]], :ts #inst &quot;2015-07-13T16:58:35.135-00:00&quot;, :branch desserts, :parents [#uuid &quot;2f887e43-5bdb-5579-8cf4-e5670850decb&quot;], :crdt :repo, :version 1, :author kordano@topiq.es, :crdt-refs #{}})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<?? (rs/transact stage [user repo "desserts"] [[(find-fn 'transact-entry) [{:name "gingerbread" :baker "adam"}]]]))
(<?? (rs/commit! stage {user {repo #{"desserts"}}}))
;; @@
;; ->
;;; 2015-Jul-13 18:58:36 +0200 phobos INFO [replikativ.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:name gingerbread, :baker adam}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;cedf0d95-9c35-4ce9-a237-ac064f5a340c&quot; {master :foo, desserts :foo}}})
;;; 2015-Jul-13 18:58:36 +0200 phobos DEBUG [replikativ.platform-log] - (committing to  desserts :  #uuid &quot;0cc8f093-9ada-5bb5-97a8-d5b4b595f10c&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;1a55eecb-c27a-5fcc-9b16-79030064d103&quot;]], :ts #inst &quot;2015-07-13T16:58:36.281-00:00&quot;, :branch desserts, :parents [#uuid &quot;01750728-d771-5bb0-8cef-659acad25a95&quot;], :crdt :repo, :version 1, :author kordano@topiq.es, :crdt-refs #{}})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(mapcat
                      (fn [br]
                        (map 
                          (fn [id] 
                            [id br]) 
                          (commit-history 
                            (get-in @stage [user repo :state :commit-graph])
                            (first (get-in @stage [user repo :state :branches br])))))
                      (keys (get-in @stage [user repo :state :branches])))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>#uuid &quot;3004b2bd-3dd9-5524-a09c-2da166ffad6a&quot;</span>","value":"#uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\""},{"type":"html","content":"<span class='clj-string'>&quot;master&quot;</span>","value":"\"master\""}],"value":"[#uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\" \"master\"]"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>#uuid &quot;1261f65e-d043-5424-972a-a1a4bc7101a2&quot;</span>","value":"#uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\""},{"type":"html","content":"<span class='clj-string'>&quot;master&quot;</span>","value":"\"master\""}],"value":"[#uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\" \"master\"]"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>#uuid &quot;2499b250-8d7e-56b3-9d38-7a27a69b1450&quot;</span>","value":"#uuid \"2499b250-8d7e-56b3-9d38-7a27a69b1450\""},{"type":"html","content":"<span class='clj-string'>&quot;master&quot;</span>","value":"\"master\""}],"value":"[#uuid \"2499b250-8d7e-56b3-9d38-7a27a69b1450\" \"master\"]"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>#uuid &quot;251e51c9-5d14-54af-8abe-a8a83f56c95c&quot;</span>","value":"#uuid \"251e51c9-5d14-54af-8abe-a8a83f56c95c\""},{"type":"html","content":"<span class='clj-string'>&quot;master&quot;</span>","value":"\"master\""}],"value":"[#uuid \"251e51c9-5d14-54af-8abe-a8a83f56c95c\" \"master\"]"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>#uuid &quot;2f887e43-5bdb-5579-8cf4-e5670850decb&quot;</span>","value":"#uuid \"2f887e43-5bdb-5579-8cf4-e5670850decb\""},{"type":"html","content":"<span class='clj-string'>&quot;master&quot;</span>","value":"\"master\""}],"value":"[#uuid \"2f887e43-5bdb-5579-8cf4-e5670850decb\" \"master\"]"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>#uuid &quot;3004b2bd-3dd9-5524-a09c-2da166ffad6a&quot;</span>","value":"#uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\""},{"type":"html","content":"<span class='clj-string'>&quot;desserts&quot;</span>","value":"\"desserts\""}],"value":"[#uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\" \"desserts\"]"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>#uuid &quot;1261f65e-d043-5424-972a-a1a4bc7101a2&quot;</span>","value":"#uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\""},{"type":"html","content":"<span class='clj-string'>&quot;desserts&quot;</span>","value":"\"desserts\""}],"value":"[#uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\" \"desserts\"]"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>#uuid &quot;2499b250-8d7e-56b3-9d38-7a27a69b1450&quot;</span>","value":"#uuid \"2499b250-8d7e-56b3-9d38-7a27a69b1450\""},{"type":"html","content":"<span class='clj-string'>&quot;desserts&quot;</span>","value":"\"desserts\""}],"value":"[#uuid \"2499b250-8d7e-56b3-9d38-7a27a69b1450\" \"desserts\"]"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>#uuid &quot;251e51c9-5d14-54af-8abe-a8a83f56c95c&quot;</span>","value":"#uuid \"251e51c9-5d14-54af-8abe-a8a83f56c95c\""},{"type":"html","content":"<span class='clj-string'>&quot;desserts&quot;</span>","value":"\"desserts\""}],"value":"[#uuid \"251e51c9-5d14-54af-8abe-a8a83f56c95c\" \"desserts\"]"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>#uuid &quot;2f887e43-5bdb-5579-8cf4-e5670850decb&quot;</span>","value":"#uuid \"2f887e43-5bdb-5579-8cf4-e5670850decb\""},{"type":"html","content":"<span class='clj-string'>&quot;desserts&quot;</span>","value":"\"desserts\""}],"value":"[#uuid \"2f887e43-5bdb-5579-8cf4-e5670850decb\" \"desserts\"]"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>#uuid &quot;01750728-d771-5bb0-8cef-659acad25a95&quot;</span>","value":"#uuid \"01750728-d771-5bb0-8cef-659acad25a95\""},{"type":"html","content":"<span class='clj-string'>&quot;desserts&quot;</span>","value":"\"desserts\""}],"value":"[#uuid \"01750728-d771-5bb0-8cef-659acad25a95\" \"desserts\"]"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>#uuid &quot;0cc8f093-9ada-5bb5-97a8-d5b4b595f10c&quot;</span>","value":"#uuid \"0cc8f093-9ada-5bb5-97a8-d5b4b595f10c\""},{"type":"html","content":"<span class='clj-string'>&quot;desserts&quot;</span>","value":"\"desserts\""}],"value":"[#uuid \"0cc8f093-9ada-5bb5-97a8-d5b4b595f10c\" \"desserts\"]"}],"value":"([#uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\" \"master\"] [#uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\" \"master\"] [#uuid \"2499b250-8d7e-56b3-9d38-7a27a69b1450\" \"master\"] [#uuid \"251e51c9-5d14-54af-8abe-a8a83f56c95c\" \"master\"] [#uuid \"2f887e43-5bdb-5579-8cf4-e5670850decb\" \"master\"] [#uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\" \"desserts\"] [#uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\" \"desserts\"] [#uuid \"2499b250-8d7e-56b3-9d38-7a27a69b1450\" \"desserts\"] [#uuid \"251e51c9-5d14-54af-8abe-a8a83f56c95c\" \"desserts\"] [#uuid \"2f887e43-5bdb-5579-8cf4-e5670850decb\" \"desserts\"] [#uuid \"01750728-d771-5bb0-8cef-659acad25a95\" \"desserts\"] [#uuid \"0cc8f093-9ada-5bb5-97a8-d5b4b595f10c\" \"desserts\"])"}
;; <=

;; @@
(plot-graph stage)
;; @@

;; @@
(<?? (s/checkout! stage [user repo] "master"))

;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<?? (s/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "baumkuchen" :baker "eve"}]]]))
(<?? (s/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Apr-05 21:30:45 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker eve, :name baumkuchen}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;45ba9d24-b865-4a89-85d5-417d0cc14a73&quot; {desserts :foo, master :foo}}})
;;; 2015-Apr-05 21:30:45 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;114ae591-d493-58fd-ab46-2da8854f540d&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;1cb833ba-497b-5e63-bdd5-6f2355ac75e6&quot;]], :ts #inst &quot;2015-04-05T19:30:45.785-00:00&quot;, :branch master, :parents [#uuid &quot;2f887e43-5bdb-5579-8cf4-e5670850decb&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<?? (s/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "apple pie" :baker "eve"}]]]))
(<?? (s/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Apr-05 21:30:48 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker eve, :name apple pie}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;45ba9d24-b865-4a89-85d5-417d0cc14a73&quot; {desserts :foo, master :foo}}})
;;; 2015-Apr-05 21:30:48 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;3144f9a8-10ea-5cce-bd6a-8d1510131ec5&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;14dd32f9-44e3-5ef5-80ca-274358539e87&quot;]], :ts #inst &quot;2015-04-05T19:30:48.346-00:00&quot;, :branch master, :parents [#uuid &quot;114ae591-d493-58fd-ab46-2da8854f540d&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<?? (s/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "brownie" :baker "adam"}]]]))
(<?? (s/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Apr-05 21:30:50 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker adam, :name brownie}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;45ba9d24-b865-4a89-85d5-417d0cc14a73&quot; {desserts :foo, master :foo}}})
;;; 2015-Apr-05 21:30:50 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;3d2d64de-5e7f-5b86-b432-e882d6846134&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;3aa38840-6296-5432-a8e3-f0a3c80e48eb&quot;]], :ts #inst &quot;2015-04-05T19:30:50.752-00:00&quot;, :branch master, :parents [#uuid &quot;3144f9a8-10ea-5cce-bd6a-8d1510131ec5&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(v/vega-view (g/commit-graph (get-in @stage [user repo :state])))
;; @@
;; =>
;;; {"type":"vega","content":{"data":[{"name":"nodes","values":[{"value":"3004b2bd-3dd9-5524-a09c-2da166ffad6a","x":83.75,"y":268.1211395263672,"r":150,"g":111,"b":196},{"value":"1261f65e-d043-5424-972a-a1a4bc7101a2","x":147.5,"y":268.1211395263672,"r":150,"g":111,"b":196},{"value":"2499b250-8d7e-56b3-9d38-7a27a69b1450","x":211.25,"y":268.1211395263672,"r":150,"g":111,"b":196},{"value":"251e51c9-5d14-54af-8abe-a8a83f56c95c","x":275,"y":268.1211395263672,"r":150,"g":111,"b":196},{"value":"2f887e43-5bdb-5579-8cf4-e5670850decb","x":338.75,"y":268.1211395263672,"r":150,"g":111,"b":196},{"value":"114ae591-d493-58fd-ab46-2da8854f540d","x":402.5,"y":268.1211395263672,"r":150,"g":111,"b":196},{"value":"3144f9a8-10ea-5cce-bd6a-8d1510131ec5","x":466.25,"y":268.1211395263672,"r":150,"g":111,"b":196},{"value":"3d2d64de-5e7f-5b86-b432-e882d6846134","x":530,"y":268.1211395263672,"r":150,"g":111,"b":196},{"value":"01750728-d771-5bb0-8cef-659acad25a95","x":434.375,"y":102.70704650878906,"r":219,"g":86,"b":232},{"value":"0cc8f093-9ada-5bb5-97a8-d5b4b595f10c","x":530,"y":102.70704650878906,"r":219,"g":86,"b":232}]},{"name":"links","values":[{"path":"M 83.75 268.12115 L 147.5 268.12115"},{"path":"M 147.5 268.12115 L 211.25 268.12115"},{"path":"M 211.25 268.12115 L 275.0 268.12115"},{"path":"M 275.0 268.12115 L 338.75 268.12115"},{"path":"M 338.75 268.12115 L 402.5 268.12115"},{"path":"M 402.5 268.12115 L 466.25 268.12115"},{"path":"M 466.25 268.12115 L 530.0 268.12115"},{"path":"M 434.375 102.70705 L 530.0 102.70705"},{"path":"M 338.75 268.12115 L 434.375 102.70705"}]},{"name":"labels","values":[{"value":"desserts","x":530,"y":102.70704650878906},{"value":"master","x":530,"y":268.1211395263672}]}],"marks":[{"properties":{"enter":{"path":{"field":"data.path"},"stroke":{"value":"grey"},"strokeWidth":{"value":1}}},"type":"path","from":{"data":"links"}},{"properties":{"update":{"stroke":["value","transparent"],"size":{"value":90},"shape":"circle"},"enter":{"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"},"x":{"field":"data.x"}}},"type":"symbol","from":{"data":"nodes"}},{"properties":{"update":{"text":{"field":"data.value"}},"enter":{"y":{"field":"data.y"},"align":{"value":"left"},"dx":{"value":10},"fill":{"value":"black"},"x":{"field":"data.x"},"fontSize":{"value":15}}},"type":"text","from":{"data":"labels"}}],"width":600,"height":370.82818603515625,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:data [{:name \"nodes\", :values [{:value #uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\", :x 335/4, :y 268.1211395263672, :r 150, :g 111, :b 196} {:value #uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\", :x 295/2, :y 268.1211395263672, :r 150, :g 111, :b 196} {:value #uuid \"2499b250-8d7e-56b3-9d38-7a27a69b1450\", :x 845/4, :y 268.1211395263672, :r 150, :g 111, :b 196} {:value #uuid \"251e51c9-5d14-54af-8abe-a8a83f56c95c\", :x 275N, :y 268.1211395263672, :r 150, :g 111, :b 196} {:value #uuid \"2f887e43-5bdb-5579-8cf4-e5670850decb\", :x 1355/4, :y 268.1211395263672, :r 150, :g 111, :b 196} {:value #uuid \"114ae591-d493-58fd-ab46-2da8854f540d\", :x 805/2, :y 268.1211395263672, :r 150, :g 111, :b 196} {:value #uuid \"3144f9a8-10ea-5cce-bd6a-8d1510131ec5\", :x 1865/4, :y 268.1211395263672, :r 150, :g 111, :b 196} {:value #uuid \"3d2d64de-5e7f-5b86-b432-e882d6846134\", :x 530N, :y 268.1211395263672, :r 150, :g 111, :b 196} {:value #uuid \"01750728-d771-5bb0-8cef-659acad25a95\", :x 3475/8, :y 102.70704650878906, :r 219, :g 86, :b 232} {:value #uuid \"0cc8f093-9ada-5bb5-97a8-d5b4b595f10c\", :x 530N, :y 102.70704650878906, :r 219, :g 86, :b 232}]} {:name \"links\", :values [{:path \"M 83.75 268.12115 L 147.5 268.12115\"} {:path \"M 147.5 268.12115 L 211.25 268.12115\"} {:path \"M 211.25 268.12115 L 275.0 268.12115\"} {:path \"M 275.0 268.12115 L 338.75 268.12115\"} {:path \"M 338.75 268.12115 L 402.5 268.12115\"} {:path \"M 402.5 268.12115 L 466.25 268.12115\"} {:path \"M 466.25 268.12115 L 530.0 268.12115\"} {:path \"M 434.375 102.70705 L 530.0 102.70705\"} {:path \"M 338.75 268.12115 L 434.375 102.70705\"}]} {:name \"labels\", :values [{:value \"desserts\", :x 530N, :y 102.70704650878906} {:value \"master\", :x 530N, :y 268.1211395263672}]}], :marks [{:properties {:enter {:path {:field \"data.path\"}, :stroke {:value \"grey\"}, :strokeWidth {:value 1}}}, :type \"path\", :from {:data \"links\"}} {:properties {:update {:stroke [:value \"transparent\"], :size {:value 90}, :shape \"circle\"}, :enter {:y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}, :x {:field \"data.x\"}}}, :type \"symbol\", :from {:data \"nodes\"}} {:properties {:update {:text {:field \"data.value\"}}, :enter {:y {:field \"data.y\"}, :align {:value \"left\"}, :dx {:value 10}, :fill {:value \"black\"}, :x {:field \"data.x\"}, :fontSize {:value 15}}}, :type \"text\", :from {:data \"labels\"}}], :width 600, :height 370.8282, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; @@
(<?? (s/branch! stage
                  [user repo]
                  "soups"
                  (first (get-in @stage [user repo :state :branches "master"]))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<?? (s/checkout! stage [user repo] "soups"))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<?? (s/transact stage [user repo "soups"] [[(find-fn 'transact-entry) [{:name "chicken soup" :chef "eve"}]]]))
(<?? (s/commit! stage {user {repo #{"soups"}}}))
;; @@
;; ->
;;; 2015-Apr-05 21:32:49 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:name chicken soup, :chef eve}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;45ba9d24-b865-4a89-85d5-417d0cc14a73&quot; {soups :foo, desserts :foo, master :foo}}})
;;; 2015-Apr-05 21:32:49 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  soups :  #uuid &quot;23389b89-aabc-5d64-98fc-53698844d87a&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;20587419-78b1-5f90-ad21-1c872a51e2e5&quot;]], :ts #inst &quot;2015-04-05T19:32:49.378-00:00&quot;, :branch soups, :parents [#uuid &quot;3d2d64de-5e7f-5b86-b432-e882d6846134&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<?? (s/transact stage [user repo "soups"] [[(find-fn 'transact-entry) [{:name "onion soup" :chef "eve"}]]]))
(<?? (s/commit! stage {user {repo #{"soups"}}}))
;; @@
;; ->
;;; 2015-Apr-05 21:33:16 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:name onion soup, :chef eve}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;45ba9d24-b865-4a89-85d5-417d0cc14a73&quot; {soups :foo, desserts :foo, master :foo}}})
;;; 2015-Apr-05 21:33:16 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  soups :  #uuid &quot;116af799-de42-506d-9780-23532b33937c&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;04313014-6f40-5dfb-b078-fcd8f84fbd87&quot;]], :ts #inst &quot;2015-04-05T19:33:16.729-00:00&quot;, :branch soups, :parents [#uuid &quot;23389b89-aabc-5d64-98fc-53698844d87a&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(v/vega-view (g/commit-graph (get-in @stage [user repo :state])))
;; @@
;; =>
;;; {"type":"vega","content":{"data":[{"name":"nodes","values":[{"value":"3004b2bd-3dd9-5524-a09c-2da166ffad6a","x":83.75,"y":185.41409301757812,"r":155,"g":115,"b":191},{"value":"1261f65e-d043-5424-972a-a1a4bc7101a2","x":147.5,"y":185.41409301757812,"r":155,"g":115,"b":191},{"value":"2499b250-8d7e-56b3-9d38-7a27a69b1450","x":211.25,"y":185.41409301757812,"r":155,"g":115,"b":191},{"value":"251e51c9-5d14-54af-8abe-a8a83f56c95c","x":275,"y":185.41409301757812,"r":155,"g":115,"b":191},{"value":"2f887e43-5bdb-5579-8cf4-e5670850decb","x":338.75,"y":185.41409301757812,"r":155,"g":115,"b":191},{"value":"114ae591-d493-58fd-ab46-2da8854f540d","x":402.5,"y":185.41409301757812,"r":155,"g":115,"b":191},{"value":"3144f9a8-10ea-5cce-bd6a-8d1510131ec5","x":466.25,"y":185.41409301757812,"r":155,"g":115,"b":191},{"value":"3d2d64de-5e7f-5b86-b432-e882d6846134","x":530,"y":185.41409301757812,"r":155,"g":115,"b":191},{"value":"01750728-d771-5bb0-8cef-659acad25a95","x":434.375,"y":75.13803100585938,"r":128,"g":189,"b":145},{"value":"0cc8f093-9ada-5bb5-97a8-d5b4b595f10c","x":530,"y":75.13803100585938,"r":128,"g":189,"b":145},{"value":"23389b89-aabc-5d64-98fc-53698844d87a","x":275,"y":295.6901550292969,"r":225,"g":227,"b":62},{"value":"116af799-de42-506d-9780-23532b33937c","x":530,"y":295.6901550292969,"r":225,"g":227,"b":62}]},{"name":"links","values":[{"path":"M 83.75 185.4141 L 147.5 185.4141"},{"path":"M 147.5 185.4141 L 211.25 185.4141"},{"path":"M 211.25 185.4141 L 275.0 185.4141"},{"path":"M 275.0 185.4141 L 338.75 185.4141"},{"path":"M 338.75 185.4141 L 402.5 185.4141"},{"path":"M 402.5 185.4141 L 466.25 185.4141"},{"path":"M 466.25 185.4141 L 530.0 185.4141"},{"path":"M 434.375 75.13803 L 530.0 75.13803"},{"path":"M 275.0 295.69016 L 530.0 295.69016"},{"path":"M 338.75 185.4141 L 434.375 75.13803"}]},{"name":"labels","values":[{"value":"soups","x":530,"y":295.6901550292969},{"value":"desserts","x":530,"y":75.13803100585938},{"value":"master","x":530,"y":185.41409301757812}]}],"marks":[{"properties":{"enter":{"path":{"field":"data.path"},"stroke":{"value":"grey"},"strokeWidth":{"value":1}}},"type":"path","from":{"data":"links"}},{"properties":{"update":{"stroke":["value","transparent"],"size":{"value":90},"shape":"circle"},"enter":{"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"},"x":{"field":"data.x"}}},"type":"symbol","from":{"data":"nodes"}},{"properties":{"update":{"text":{"field":"data.value"}},"enter":{"y":{"field":"data.y"},"align":{"value":"left"},"dx":{"value":10},"fill":{"value":"black"},"x":{"field":"data.x"},"fontSize":{"value":15}}},"type":"text","from":{"data":"labels"}}],"width":600,"height":370.82818603515625,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:data [{:name \"nodes\", :values [{:value #uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\", :x 335/4, :y 185.41409301757812, :r 155, :g 115, :b 191} {:value #uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\", :x 295/2, :y 185.41409301757812, :r 155, :g 115, :b 191} {:value #uuid \"2499b250-8d7e-56b3-9d38-7a27a69b1450\", :x 845/4, :y 185.41409301757812, :r 155, :g 115, :b 191} {:value #uuid \"251e51c9-5d14-54af-8abe-a8a83f56c95c\", :x 275N, :y 185.41409301757812, :r 155, :g 115, :b 191} {:value #uuid \"2f887e43-5bdb-5579-8cf4-e5670850decb\", :x 1355/4, :y 185.41409301757812, :r 155, :g 115, :b 191} {:value #uuid \"114ae591-d493-58fd-ab46-2da8854f540d\", :x 805/2, :y 185.41409301757812, :r 155, :g 115, :b 191} {:value #uuid \"3144f9a8-10ea-5cce-bd6a-8d1510131ec5\", :x 1865/4, :y 185.41409301757812, :r 155, :g 115, :b 191} {:value #uuid \"3d2d64de-5e7f-5b86-b432-e882d6846134\", :x 530N, :y 185.41409301757812, :r 155, :g 115, :b 191} {:value #uuid \"01750728-d771-5bb0-8cef-659acad25a95\", :x 3475/8, :y 75.13803100585938, :r 128, :g 189, :b 145} {:value #uuid \"0cc8f093-9ada-5bb5-97a8-d5b4b595f10c\", :x 530N, :y 75.13803100585938, :r 128, :g 189, :b 145} {:value #uuid \"23389b89-aabc-5d64-98fc-53698844d87a\", :x 275, :y 295.6901550292969, :r 225, :g 227, :b 62} {:value #uuid \"116af799-de42-506d-9780-23532b33937c\", :x 530, :y 295.6901550292969, :r 225, :g 227, :b 62}]} {:name \"links\", :values [{:path \"M 83.75 185.4141 L 147.5 185.4141\"} {:path \"M 147.5 185.4141 L 211.25 185.4141\"} {:path \"M 211.25 185.4141 L 275.0 185.4141\"} {:path \"M 275.0 185.4141 L 338.75 185.4141\"} {:path \"M 338.75 185.4141 L 402.5 185.4141\"} {:path \"M 402.5 185.4141 L 466.25 185.4141\"} {:path \"M 466.25 185.4141 L 530.0 185.4141\"} {:path \"M 434.375 75.13803 L 530.0 75.13803\"} {:path \"M 275.0 295.69016 L 530.0 295.69016\"} {:path \"M 338.75 185.4141 L 434.375 75.13803\"}]} {:name \"labels\", :values [{:value \"soups\", :x 530, :y 295.6901550292969} {:value \"desserts\", :x 530N, :y 75.13803100585938} {:value \"master\", :x 530N, :y 185.41409301757812}]}], :marks [{:properties {:enter {:path {:field \"data.path\"}, :stroke {:value \"grey\"}, :strokeWidth {:value 1}}}, :type \"path\", :from {:data \"links\"}} {:properties {:update {:stroke [:value \"transparent\"], :size {:value 90}, :shape \"circle\"}, :enter {:y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}, :x {:field \"data.x\"}}}, :type \"symbol\", :from {:data \"nodes\"}} {:properties {:update {:text {:field \"data.value\"}}, :enter {:y {:field \"data.y\"}, :align {:value \"left\"}, :dx {:value 10}, :fill {:value \"black\"}, :x {:field \"data.x\"}, :fontSize {:value 15}}}, :type \"text\", :from {:data \"labels\"}}], :width 600, :height 370.8282, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; @@
(<?? (s/checkout! stage [user repo] "master"))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<?? (s/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "banana pie" :baker "eve"}]]]))
(<?? (s/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Apr-05 21:34:28 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker eve, :name banana pie}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;45ba9d24-b865-4a89-85d5-417d0cc14a73&quot; {soups :foo, desserts :foo, master :foo}}})
;;; 2015-Apr-05 21:34:28 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;19dc53b2-7dc3-53ee-b7fd-541187a1c2d2&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;1274363e-8533-571d-8153-2c8ea2903ef5&quot;]], :ts #inst &quot;2015-04-05T19:34:28.394-00:00&quot;, :branch master, :parents [#uuid &quot;3d2d64de-5e7f-5b86-b432-e882d6846134&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<!? (s/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "ice cake" :baker "eve"}]]]))
(<!? (s/commit! stage {user {repo #{"master"}}}))
(<!? (s/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "apfelstrudel" :baker "adam"}]]]))
(<!? (s/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Apr-05 21:36:02 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker eve, :name ice cake}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;45ba9d24-b865-4a89-85d5-417d0cc14a73&quot; {soups :foo, desserts :foo, master :foo}}})
;;; 2015-Apr-05 21:36:02 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;36a3c5ee-8be0-5f00-bbca-6cf374578148&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;252bafcf-3b96-56a8-b277-a080dea8e5cb&quot;]], :ts #inst &quot;2015-04-05T19:36:02.271-00:00&quot;, :branch master, :parents [#uuid &quot;19dc53b2-7dc3-53ee-b7fd-541187a1c2d2&quot;], :author kordano@topiq.es})
;;; 2015-Apr-05 21:36:02 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker adam, :name apfelstrudel}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;45ba9d24-b865-4a89-85d5-417d0cc14a73&quot; {soups :foo, desserts :foo, master :foo}}})
;;; 2015-Apr-05 21:36:02 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;21d9638e-e325-549b-aae4-4396fb9247d8&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;17b3a6fe-8940-5f91-b7e5-bf6d5e4c87d4&quot;]], :ts #inst &quot;2015-04-05T19:36:02.295-00:00&quot;, :branch master, :parents [#uuid &quot;36a3c5ee-8be0-5f00-bbca-6cf374578148&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(v/vega-view (g/commit-graph (get-in @stage [user repo :state])))
;; @@
;; =>
;;; {"type":"vega","content":{"data":[{"name":"nodes","values":[{"value":"3004b2bd-3dd9-5524-a09c-2da166ffad6a","x":66.36363636363636,"y":185.41409301757812,"r":238,"g":139,"b":238},{"value":"1261f65e-d043-5424-972a-a1a4bc7101a2","x":112.7272727272727,"y":185.41409301757812,"r":238,"g":139,"b":238},{"value":"2499b250-8d7e-56b3-9d38-7a27a69b1450","x":159.0909090909091,"y":185.41409301757812,"r":238,"g":139,"b":238},{"value":"251e51c9-5d14-54af-8abe-a8a83f56c95c","x":205.4545454545455,"y":185.41409301757812,"r":238,"g":139,"b":238},{"value":"2f887e43-5bdb-5579-8cf4-e5670850decb","x":251.8181818181818,"y":185.41409301757812,"r":238,"g":139,"b":238},{"value":"114ae591-d493-58fd-ab46-2da8854f540d","x":298.1818181818182,"y":185.41409301757812,"r":238,"g":139,"b":238},{"value":"3144f9a8-10ea-5cce-bd6a-8d1510131ec5","x":344.5454545454545,"y":185.41409301757812,"r":238,"g":139,"b":238},{"value":"3d2d64de-5e7f-5b86-b432-e882d6846134","x":390.9090909090909,"y":185.41409301757812,"r":238,"g":139,"b":238},{"value":"19dc53b2-7dc3-53ee-b7fd-541187a1c2d2","x":437.2727272727273,"y":185.41409301757812,"r":238,"g":139,"b":238},{"value":"36a3c5ee-8be0-5f00-bbca-6cf374578148","x":483.6363636363636,"y":185.41409301757812,"r":238,"g":139,"b":238},{"value":"21d9638e-e325-549b-aae4-4396fb9247d8","x":530,"y":185.41409301757812,"r":238,"g":139,"b":238},{"value":"23389b89-aabc-5d64-98fc-53698844d87a","x":460.4545454545455,"y":75.13803100585938,"r":139,"g":186,"b":129},{"value":"116af799-de42-506d-9780-23532b33937c","x":530,"y":75.13803100585938,"r":139,"g":186,"b":129},{"value":"01750728-d771-5bb0-8cef-659acad25a95","x":390.9090909090909,"y":295.6901550292969,"r":219,"g":172,"b":96},{"value":"0cc8f093-9ada-5bb5-97a8-d5b4b595f10c","x":530,"y":295.6901550292969,"r":219,"g":172,"b":96}]},{"name":"links","values":[{"path":"M 66.36364 185.4141 L 112.72727 185.4141"},{"path":"M 112.72727 185.4141 L 159.09091 185.4141"},{"path":"M 159.09091 185.4141 L 205.45454 185.4141"},{"path":"M 205.45454 185.4141 L 251.81818 185.4141"},{"path":"M 251.81818 185.4141 L 298.18182 185.4141"},{"path":"M 298.18182 185.4141 L 344.54544 185.4141"},{"path":"M 344.54544 185.4141 L 390.9091 185.4141"},{"path":"M 390.9091 185.4141 L 437.27274 185.4141"},{"path":"M 437.27274 185.4141 L 483.63635 185.4141"},{"path":"M 483.63635 185.4141 L 530.0 185.4141"},{"path":"M 460.45456 75.13803 L 530.0 75.13803"},{"path":"M 390.9091 295.69016 L 530.0 295.69016"},{"path":"M 251.81818 185.4141 L 390.9091 295.69016"},{"path":"M 390.9091 185.4141 L 460.45456 75.13803"}]},{"name":"labels","values":[{"value":"soups","x":530,"y":75.13803100585938},{"value":"desserts","x":530,"y":295.6901550292969},{"value":"master","x":530,"y":185.41409301757812}]}],"marks":[{"properties":{"enter":{"path":{"field":"data.path"},"stroke":{"value":"grey"},"strokeWidth":{"value":1}}},"type":"path","from":{"data":"links"}},{"properties":{"update":{"stroke":["value","transparent"],"size":{"value":90},"shape":"circle"},"enter":{"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"},"x":{"field":"data.x"}}},"type":"symbol","from":{"data":"nodes"}},{"properties":{"update":{"text":{"field":"data.value"}},"enter":{"y":{"field":"data.y"},"align":{"value":"left"},"dx":{"value":10},"fill":{"value":"black"},"x":{"field":"data.x"},"fontSize":{"value":15}}},"type":"text","from":{"data":"labels"}}],"width":600,"height":370.82818603515625,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:data [{:name \"nodes\", :values [{:value #uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\", :x 730/11, :y 185.41409301757812, :r 238, :g 139, :b 238} {:value #uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\", :x 1240/11, :y 185.41409301757812, :r 238, :g 139, :b 238} {:value #uuid \"2499b250-8d7e-56b3-9d38-7a27a69b1450\", :x 1750/11, :y 185.41409301757812, :r 238, :g 139, :b 238} {:value #uuid \"251e51c9-5d14-54af-8abe-a8a83f56c95c\", :x 2260/11, :y 185.41409301757812, :r 238, :g 139, :b 238} {:value #uuid \"2f887e43-5bdb-5579-8cf4-e5670850decb\", :x 2770/11, :y 185.41409301757812, :r 238, :g 139, :b 238} {:value #uuid \"114ae591-d493-58fd-ab46-2da8854f540d\", :x 3280/11, :y 185.41409301757812, :r 238, :g 139, :b 238} {:value #uuid \"3144f9a8-10ea-5cce-bd6a-8d1510131ec5\", :x 3790/11, :y 185.41409301757812, :r 238, :g 139, :b 238} {:value #uuid \"3d2d64de-5e7f-5b86-b432-e882d6846134\", :x 4300/11, :y 185.41409301757812, :r 238, :g 139, :b 238} {:value #uuid \"19dc53b2-7dc3-53ee-b7fd-541187a1c2d2\", :x 4810/11, :y 185.41409301757812, :r 238, :g 139, :b 238} {:value #uuid \"36a3c5ee-8be0-5f00-bbca-6cf374578148\", :x 5320/11, :y 185.41409301757812, :r 238, :g 139, :b 238} {:value #uuid \"21d9638e-e325-549b-aae4-4396fb9247d8\", :x 530N, :y 185.41409301757812, :r 238, :g 139, :b 238} {:value #uuid \"23389b89-aabc-5d64-98fc-53698844d87a\", :x 5065/11, :y 75.13803100585938, :r 139, :g 186, :b 129} {:value #uuid \"116af799-de42-506d-9780-23532b33937c\", :x 530N, :y 75.13803100585938, :r 139, :g 186, :b 129} {:value #uuid \"01750728-d771-5bb0-8cef-659acad25a95\", :x 4300/11, :y 295.6901550292969, :r 219, :g 172, :b 96} {:value #uuid \"0cc8f093-9ada-5bb5-97a8-d5b4b595f10c\", :x 530N, :y 295.6901550292969, :r 219, :g 172, :b 96}]} {:name \"links\", :values [{:path \"M 66.36364 185.4141 L 112.72727 185.4141\"} {:path \"M 112.72727 185.4141 L 159.09091 185.4141\"} {:path \"M 159.09091 185.4141 L 205.45454 185.4141\"} {:path \"M 205.45454 185.4141 L 251.81818 185.4141\"} {:path \"M 251.81818 185.4141 L 298.18182 185.4141\"} {:path \"M 298.18182 185.4141 L 344.54544 185.4141\"} {:path \"M 344.54544 185.4141 L 390.9091 185.4141\"} {:path \"M 390.9091 185.4141 L 437.27274 185.4141\"} {:path \"M 437.27274 185.4141 L 483.63635 185.4141\"} {:path \"M 483.63635 185.4141 L 530.0 185.4141\"} {:path \"M 460.45456 75.13803 L 530.0 75.13803\"} {:path \"M 390.9091 295.69016 L 530.0 295.69016\"} {:path \"M 251.81818 185.4141 L 390.9091 295.69016\"} {:path \"M 390.9091 185.4141 L 460.45456 75.13803\"}]} {:name \"labels\", :values [{:value \"soups\", :x 530N, :y 75.13803100585938} {:value \"desserts\", :x 530N, :y 295.6901550292969} {:value \"master\", :x 530N, :y 185.41409301757812}]}], :marks [{:properties {:enter {:path {:field \"data.path\"}, :stroke {:value \"grey\"}, :strokeWidth {:value 1}}}, :type \"path\", :from {:data \"links\"}} {:properties {:update {:stroke [:value \"transparent\"], :size {:value 90}, :shape \"circle\"}, :enter {:y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}, :x {:field \"data.x\"}}}, :type \"symbol\", :from {:data \"nodes\"}} {:properties {:update {:text {:field \"data.value\"}}, :enter {:y {:field \"data.y\"}, :align {:value \"left\"}, :dx {:value 10}, :fill {:value \"black\"}, :x {:field \"data.x\"}, :fontSize {:value 15}}}, :type \"text\", :from {:data \"labels\"}}], :width 600, :height 370.8282, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; @@
(<!? (s/merge! stage [user repo "soups"]
               (get-in @stage [user repo :state :branches "soups"])))
;; @@
;; ->
;;; 2015-Apr-05 21:37:49 +0200 phobos DEBUG [geschichte.platform-log] - (merging: into  kordano@topiq.es #uuid &quot;45ba9d24-b865-4a89-85d5-417d0cc14a73&quot; {:cut #{#uuid &quot;116af799-de42-506d-9780-23532b33937c&quot;}, :returnpaths-a {#uuid &quot;116af799-de42-506d-9780-23532b33937c&quot; #{}}, :returnpaths-b {#uuid &quot;116af799-de42-506d-9780-23532b33937c&quot; #{}}})
;;; 2015-Apr-05 21:37:49 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  soups :  #uuid &quot;2292f491-666c-59a9-a3a7-38c5adad693f&quot; {:transactions [], :ts #inst &quot;2015-04-05T19:37:49.950-00:00&quot;, :branch soups, :parents [#uuid &quot;116af799-de42-506d-9780-23532b33937c&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<!? (s/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Apr-05 21:38:23 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;3ebb69c9-e391-5978-87ba-7a7ea6ac1b7d&quot; {:transactions [], :ts #inst &quot;2015-04-05T19:38:23.691-00:00&quot;, :branch master, :parents [#uuid &quot;21d9638e-e325-549b-aae4-4396fb9247d8&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(v/vega-view (g/commit-graph (get-in @stage [user repo :state])))
;; @@
;; =>
;;; {"type":"vega","content":{"data":[{"name":"nodes","values":[{"value":"3004b2bd-3dd9-5524-a09c-2da166ffad6a","x":62.5,"y":185.41409301757812,"r":32,"g":102,"b":14},{"value":"1261f65e-d043-5424-972a-a1a4bc7101a2","x":105,"y":185.41409301757812,"r":32,"g":102,"b":14},{"value":"2499b250-8d7e-56b3-9d38-7a27a69b1450","x":147.5,"y":185.41409301757812,"r":32,"g":102,"b":14},{"value":"251e51c9-5d14-54af-8abe-a8a83f56c95c","x":190,"y":185.41409301757812,"r":32,"g":102,"b":14},{"value":"2f887e43-5bdb-5579-8cf4-e5670850decb","x":232.5,"y":185.41409301757812,"r":32,"g":102,"b":14},{"value":"114ae591-d493-58fd-ab46-2da8854f540d","x":275,"y":185.41409301757812,"r":32,"g":102,"b":14},{"value":"3144f9a8-10ea-5cce-bd6a-8d1510131ec5","x":317.5,"y":185.41409301757812,"r":32,"g":102,"b":14},{"value":"3d2d64de-5e7f-5b86-b432-e882d6846134","x":360,"y":185.41409301757812,"r":32,"g":102,"b":14},{"value":"19dc53b2-7dc3-53ee-b7fd-541187a1c2d2","x":402.5,"y":185.41409301757812,"r":32,"g":102,"b":14},{"value":"36a3c5ee-8be0-5f00-bbca-6cf374578148","x":445,"y":185.41409301757812,"r":32,"g":102,"b":14},{"value":"21d9638e-e325-549b-aae4-4396fb9247d8","x":487.5,"y":185.41409301757812,"r":32,"g":102,"b":14},{"value":"3ebb69c9-e391-5978-87ba-7a7ea6ac1b7d","x":530,"y":185.41409301757812,"r":32,"g":102,"b":14},{"value":"23389b89-aabc-5d64-98fc-53698844d87a","x":416.6666666666667,"y":75.13803100585938,"r":167,"g":63,"b":75},{"value":"116af799-de42-506d-9780-23532b33937c","x":473.3333333333333,"y":75.13803100585938,"r":167,"g":63,"b":75},{"value":"2292f491-666c-59a9-a3a7-38c5adad693f","x":530,"y":75.13803100585938,"r":167,"g":63,"b":75},{"value":"01750728-d771-5bb0-8cef-659acad25a95","x":381.25,"y":295.6901550292969,"r":101,"g":188,"b":20},{"value":"0cc8f093-9ada-5bb5-97a8-d5b4b595f10c","x":530,"y":295.6901550292969,"r":101,"g":188,"b":20}]},{"name":"links","values":[{"path":"M 62.5 185.4141 L 105.0 185.4141"},{"path":"M 105.0 185.4141 L 147.5 185.4141"},{"path":"M 147.5 185.4141 L 190.0 185.4141"},{"path":"M 190.0 185.4141 L 232.5 185.4141"},{"path":"M 232.5 185.4141 L 275.0 185.4141"},{"path":"M 275.0 185.4141 L 317.5 185.4141"},{"path":"M 317.5 185.4141 L 360.0 185.4141"},{"path":"M 360.0 185.4141 L 402.5 185.4141"},{"path":"M 402.5 185.4141 L 445.0 185.4141"},{"path":"M 445.0 185.4141 L 487.5 185.4141"},{"path":"M 487.5 185.4141 L 530.0 185.4141"},{"path":"M 416.66666 75.13803 L 473.33334 75.13803"},{"path":"M 473.33334 75.13803 L 530.0 75.13803"},{"path":"M 381.25 295.69016 L 530.0 295.69016"},{"path":"M 232.5 185.4141 L 381.25 295.69016"},{"path":"M 360.0 185.4141 L 416.66666 75.13803"}]},{"name":"labels","values":[{"value":"soups","x":530,"y":75.13803100585938},{"value":"desserts","x":530,"y":295.6901550292969},{"value":"master","x":530,"y":185.41409301757812}]}],"marks":[{"properties":{"enter":{"path":{"field":"data.path"},"stroke":{"value":"grey"},"strokeWidth":{"value":1}}},"type":"path","from":{"data":"links"}},{"properties":{"update":{"stroke":["value","transparent"],"size":{"value":90},"shape":"circle"},"enter":{"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"},"x":{"field":"data.x"}}},"type":"symbol","from":{"data":"nodes"}},{"properties":{"update":{"text":{"field":"data.value"}},"enter":{"y":{"field":"data.y"},"align":{"value":"left"},"dx":{"value":10},"fill":{"value":"black"},"x":{"field":"data.x"},"fontSize":{"value":15}}},"type":"text","from":{"data":"labels"}}],"width":600,"height":370.82818603515625,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:data [{:name \"nodes\", :values [{:value #uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\", :x 125/2, :y 185.41409301757812, :r 32, :g 102, :b 14} {:value #uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\", :x 105N, :y 185.41409301757812, :r 32, :g 102, :b 14} {:value #uuid \"2499b250-8d7e-56b3-9d38-7a27a69b1450\", :x 295/2, :y 185.41409301757812, :r 32, :g 102, :b 14} {:value #uuid \"251e51c9-5d14-54af-8abe-a8a83f56c95c\", :x 190N, :y 185.41409301757812, :r 32, :g 102, :b 14} {:value #uuid \"2f887e43-5bdb-5579-8cf4-e5670850decb\", :x 465/2, :y 185.41409301757812, :r 32, :g 102, :b 14} {:value #uuid \"114ae591-d493-58fd-ab46-2da8854f540d\", :x 275N, :y 185.41409301757812, :r 32, :g 102, :b 14} {:value #uuid \"3144f9a8-10ea-5cce-bd6a-8d1510131ec5\", :x 635/2, :y 185.41409301757812, :r 32, :g 102, :b 14} {:value #uuid \"3d2d64de-5e7f-5b86-b432-e882d6846134\", :x 360N, :y 185.41409301757812, :r 32, :g 102, :b 14} {:value #uuid \"19dc53b2-7dc3-53ee-b7fd-541187a1c2d2\", :x 805/2, :y 185.41409301757812, :r 32, :g 102, :b 14} {:value #uuid \"36a3c5ee-8be0-5f00-bbca-6cf374578148\", :x 445N, :y 185.41409301757812, :r 32, :g 102, :b 14} {:value #uuid \"21d9638e-e325-549b-aae4-4396fb9247d8\", :x 975/2, :y 185.41409301757812, :r 32, :g 102, :b 14} {:value #uuid \"3ebb69c9-e391-5978-87ba-7a7ea6ac1b7d\", :x 530N, :y 185.41409301757812, :r 32, :g 102, :b 14} {:value #uuid \"23389b89-aabc-5d64-98fc-53698844d87a\", :x 1250/3, :y 75.13803100585938, :r 167, :g 63, :b 75} {:value #uuid \"116af799-de42-506d-9780-23532b33937c\", :x 1420/3, :y 75.13803100585938, :r 167, :g 63, :b 75} {:value #uuid \"2292f491-666c-59a9-a3a7-38c5adad693f\", :x 530N, :y 75.13803100585938, :r 167, :g 63, :b 75} {:value #uuid \"01750728-d771-5bb0-8cef-659acad25a95\", :x 1525/4, :y 295.6901550292969, :r 101, :g 188, :b 20} {:value #uuid \"0cc8f093-9ada-5bb5-97a8-d5b4b595f10c\", :x 530N, :y 295.6901550292969, :r 101, :g 188, :b 20}]} {:name \"links\", :values [{:path \"M 62.5 185.4141 L 105.0 185.4141\"} {:path \"M 105.0 185.4141 L 147.5 185.4141\"} {:path \"M 147.5 185.4141 L 190.0 185.4141\"} {:path \"M 190.0 185.4141 L 232.5 185.4141\"} {:path \"M 232.5 185.4141 L 275.0 185.4141\"} {:path \"M 275.0 185.4141 L 317.5 185.4141\"} {:path \"M 317.5 185.4141 L 360.0 185.4141\"} {:path \"M 360.0 185.4141 L 402.5 185.4141\"} {:path \"M 402.5 185.4141 L 445.0 185.4141\"} {:path \"M 445.0 185.4141 L 487.5 185.4141\"} {:path \"M 487.5 185.4141 L 530.0 185.4141\"} {:path \"M 416.66666 75.13803 L 473.33334 75.13803\"} {:path \"M 473.33334 75.13803 L 530.0 75.13803\"} {:path \"M 381.25 295.69016 L 530.0 295.69016\"} {:path \"M 232.5 185.4141 L 381.25 295.69016\"} {:path \"M 360.0 185.4141 L 416.66666 75.13803\"}]} {:name \"labels\", :values [{:value \"soups\", :x 530N, :y 75.13803100585938} {:value \"desserts\", :x 530N, :y 295.6901550292969} {:value \"master\", :x 530N, :y 185.41409301757812}]}], :marks [{:properties {:enter {:path {:field \"data.path\"}, :stroke {:value \"grey\"}, :strokeWidth {:value 1}}}, :type \"path\", :from {:data \"links\"}} {:properties {:update {:stroke [:value \"transparent\"], :size {:value 90}, :shape \"circle\"}, :enter {:y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}, :x {:field \"data.x\"}}}, :type \"symbol\", :from {:data \"nodes\"}} {:properties {:update {:text {:field \"data.value\"}}, :enter {:y {:field \"data.y\"}, :align {:value \"left\"}, :dx {:value 10}, :fill {:value \"black\"}, :x {:field \"data.x\"}, :fontSize {:value 15}}}, :type \"text\", :from {:data \"labels\"}}], :width 600, :height 370.8282, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; @@
(<!? (s/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "donauwelle" :baker "adam"}]]]))
(<!? (s/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Apr-05 21:39:10 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker adam, :name donauwelle}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;45ba9d24-b865-4a89-85d5-417d0cc14a73&quot; {soups :foo, desserts :foo, master :foo}}})
;;; 2015-Apr-05 21:39:11 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;217bb427-6c06-5631-96cf-a880f393841e&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;182b3774-bb43-58be-a4b1-87388dbe9318&quot;]], :ts #inst &quot;2015-04-05T19:39:11.002-00:00&quot;, :branch master, :parents [#uuid &quot;3ebb69c9-e391-5978-87ba-7a7ea6ac1b7d&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<!? (s/pull! stage [user repo "soups"] [user repo "master"]))
;; @@

;; @@
(v/vega-view (g/commit-graph (get-in @stage [user repo :state])))
;; @@
