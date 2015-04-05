;; gorilla-repl.fileformat = 1

;; **
;;; # Geschichte Example Pipeline
;; **

;; @@
(ns harmonious-creek
  (:require [geschichte-example.core :refer :all]
            [geschichte-gorilla.core :as g]
            [gorilla-repl.vega :as v]
            [hasch.core :refer [uuid]]
            [konserve.store :refer [new-mem-store]]
            [konserve.protocols :refer [-get-in -assoc-in -update-in -bassoc]]
            [geschichte.sync :refer [server-peer client-peer]]
            [geschichte.stage :as s]
            [geschichte.p2p.fetch :refer [fetch]]
            [geschichte.p2p.hash :refer [ensure-hash]]
            [geschichte.realize :refer [commit-value]]
            [geschichte.p2p.block-detector :refer [block-detector]]
            [geschichte.platform :refer [create-http-kit-handler! <!? start stop]]
            [clojure.core.async :refer [>!!]]
))
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
(def store (<!? (new-mem-store)))
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
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:volatile</span>","value":":volatile"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:new-conns</span>","value":":new-conns"},{"type":"html","content":"<span class='clj-unkown'>#&lt;ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@40992a82&gt;</span>","value":"#<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@40992a82>"}],"value":"[:new-conns #<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@40992a82>]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:store</span>","value":":store"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:state</span>","value":":state"},{"type":"html","content":"<span class='clj-atom'>#&lt;Atom@7b5ddf0: {}&gt;</span>","value":"#<Atom@7b5ddf0: {}>"}],"value":"[:state #<Atom@7b5ddf0: {}>]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:tag-table</span>","value":":tag-table"},{"type":"html","content":"<span class='clj-atom'>#&lt;Atom@34fdbb84: {}&gt;</span>","value":"#<Atom@34fdbb84: {}>"}],"value":"[:tag-table #<Atom@34fdbb84: {}>]"}],"value":"#konserve.store.MemAsyncKeyValueStore{:state #<Atom@7b5ddf0: {}>, :tag-table #<Atom@34fdbb84: {}>}"}],"value":"[:store #konserve.store.MemAsyncKeyValueStore{:state #<Atom@7b5ddf0: {}>, :tag-table #<Atom@34fdbb84: {}>}]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:server</span>","value":":server"},{"type":"html","content":"<span class='clj-unkown'>#&lt;clojure.lang.AFunction$1@1876f9d8&gt;</span>","value":"#<clojure.lang.AFunction$1@1876f9d8>"}],"value":"[:server #<clojure.lang.AFunction$1@1876f9d8>]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:error-ch</span>","value":":error-ch"},{"type":"html","content":"<span class='clj-unkown'>#&lt;ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@2b0ce074&gt;</span>","value":"#<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@2b0ce074>"}],"value":"[:error-ch #<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@2b0ce074>]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:chans</span>","value":":chans"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>#&lt;ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@5a63f5c0&gt;</span>","value":"#<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@5a63f5c0>"},{"type":"html","content":"<span class='clj-unkown'>#&lt;async$pub$reify__18654 clojure.core.async$pub$reify__18654@382ba136&gt;</span>","value":"#<async$pub$reify__18654 clojure.core.async$pub$reify__18654@382ba136>"}],"value":"[#<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@5a63f5c0> #<async$pub$reify__18654 clojure.core.async$pub$reify__18654@382ba136>]"}],"value":"[:chans [#<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@5a63f5c0> #<async$pub$reify__18654 clojure.core.async$pub$reify__18654@382ba136>]]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:middleware</span>","value":":middleware"},{"type":"html","content":"<span class='clj-unkown'>#&lt;core$comp$fn__4196 clojure.core$comp$fn__4196@6e2076cb&gt;</span>","value":"#<core$comp$fn__4196 clojure.core$comp$fn__4196@6e2076cb>"}],"value":"[:middleware #<core$comp$fn__4196 clojure.core$comp$fn__4196@6e2076cb>]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:url</span>","value":":url"},{"type":"html","content":"<span class='clj-string'>&quot;ws://127.0.0.1:37050&quot;</span>","value":"\"ws://127.0.0.1:37050\""}],"value":"[:url \"ws://127.0.0.1:37050\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:channel-hub</span>","value":":channel-hub"},{"type":"html","content":"<span class='clj-atom'>#&lt;Atom@155741e9: {}&gt;</span>","value":"#<Atom@155741e9: {}>"}],"value":"[:channel-hub #<Atom@155741e9: {}>]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:handler</span>","value":":handler"},{"type":"html","content":"<span class='clj-unkown'>#&lt;platform$create_http_kit_handler_BANG_$handler__22145 geschichte.platform$create_http_kit_handler_BANG_$handler__22145@7a8764c&gt;</span>","value":"#<platform$create_http_kit_handler_BANG_$handler__22145 geschichte.platform$create_http_kit_handler_BANG_$handler__22145@7a8764c>"}],"value":"[:handler #<platform$create_http_kit_handler_BANG_$handler__22145 geschichte.platform$create_http_kit_handler_BANG_$handler__22145@7a8764c>]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:log</span>","value":":log"},{"type":"html","content":"<span class='clj-atom'>#&lt;Atom@44ee7b81: {}&gt;</span>","value":"#<Atom@44ee7b81: {}>"}],"value":"[:log #<Atom@44ee7b81: {}>]"}],"value":"{:new-conns #<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@40992a82>, :store #konserve.store.MemAsyncKeyValueStore{:state #<Atom@7b5ddf0: {}>, :tag-table #<Atom@34fdbb84: {}>}, :server #<clojure.lang.AFunction$1@1876f9d8>, :error-ch #<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@2b0ce074>, :chans [#<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@5a63f5c0> #<async$pub$reify__18654 clojure.core.async$pub$reify__18654@382ba136>], :middleware #<core$comp$fn__4196 clojure.core$comp$fn__4196@6e2076cb>, :url \"ws://127.0.0.1:37050\", :channel-hub #<Atom@155741e9: {}>, :handler #<platform$create_http_kit_handler_BANG_$handler__22145 geschichte.platform$create_http_kit_handler_BANG_$handler__22145@7a8764c>, :log #<Atom@44ee7b81: {}>}"}],"value":"[:volatile {:new-conns #<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@40992a82>, :store #konserve.store.MemAsyncKeyValueStore{:state #<Atom@7b5ddf0: {}>, :tag-table #<Atom@34fdbb84: {}>}, :server #<clojure.lang.AFunction$1@1876f9d8>, :error-ch #<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@2b0ce074>, :chans [#<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@5a63f5c0> #<async$pub$reify__18654 clojure.core.async$pub$reify__18654@382ba136>], :middleware #<core$comp$fn__4196 clojure.core$comp$fn__4196@6e2076cb>, :url \"ws://127.0.0.1:37050\", :channel-hub #<Atom@155741e9: {}>, :handler #<platform$create_http_kit_handler_BANG_$handler__22145 geschichte.platform$create_http_kit_handler_BANG_$handler__22145@7a8764c>, :log #<Atom@44ee7b81: {}>}]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:name</span>","value":":name"},{"type":"html","content":"<span class='clj-string'>&quot;ws://127.0.0.1:37050&quot;</span>","value":"\"ws://127.0.0.1:37050\""}],"value":"[:name \"ws://127.0.0.1:37050\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:meta-sub</span>","value":":meta-sub"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[],"value":"{}"}],"value":"[:meta-sub {}]"}],"value":"{:volatile {:new-conns #<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@40992a82>, :store #konserve.store.MemAsyncKeyValueStore{:state #<Atom@7b5ddf0: {}>, :tag-table #<Atom@34fdbb84: {}>}, :server #<clojure.lang.AFunction$1@1876f9d8>, :error-ch #<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@2b0ce074>, :chans [#<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@5a63f5c0> #<async$pub$reify__18654 clojure.core.async$pub$reify__18654@382ba136>], :middleware #<core$comp$fn__4196 clojure.core$comp$fn__4196@6e2076cb>, :url \"ws://127.0.0.1:37050\", :channel-hub #<Atom@155741e9: {}>, :handler #<platform$create_http_kit_handler_BANG_$handler__22145 geschichte.platform$create_http_kit_handler_BANG_$handler__22145@7a8764c>, :log #<Atom@44ee7b81: {}>}, :name \"ws://127.0.0.1:37050\", :meta-sub {}}"}
;; <=

;; **
;;; Create a local *stage* and connect it to the peer.
;; **

;; @@
(def stage (<!? (s/create-stage! user peer eval)))
(<!? (s/connect! stage socket))
;; @@
;; ->
;;; 2015-Apr-05 19:16:19 +0200 phobos INFO [geschichte.platform-log] - (ws://127.0.0.1:37050 connecting to: ws://127.0.0.1:37050)
;;; 2015-Apr-05 19:16:20 +0200 phobos DEBUG [geschichte.platform-log] - (connect: backsubscription? #uuid &quot;26723cfd-31d7-43f2-afb8-c883a2a5a0c5&quot; {:topic :meta-subed, :metas {}, :peer ws://127.0.0.1:37050, :id #uuid &quot;26723cfd-31d7-43f2-afb8-c883a2a5a0c5&quot;})
;;; 2015-Apr-05 19:16:20 +0200 phobos INFO [geschichte.platform-log] - (connect!: connected  ws://127.0.0.1:37050)
;;; 2015-Apr-05 19:16:20 +0200 phobos INFO [geschichte.platform-log] - (ws://127.0.0.1:37050 subscribe: starting subscription  #uuid &quot;26723cfd-31d7-43f2-afb8-c883a2a5a0c5&quot;  from  ws://127.0.0.1:37050)
;;; 2015-Apr-05 19:16:20 +0200 phobos DEBUG [geschichte.platform-log] - (ws://127.0.0.1:37050 subscribe: subscriptions  {})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Now we can create a local *repo* which we can use to commit our data.
;; **

;; @@
(def repo (<!? (s/create-repo! stage "cake collection")))
;; @@
;; ->
;;; 2015-Apr-05 19:16:22 +0200 phobos DEBUG [geschichte.platform-log] - (creating new repo for  kordano@topiq.es with id #uuid &quot;4b04aa42-2d56-49b0-acdd-a91eb8119380&quot;)
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
(<!? (s/transact stage [user repo "master"] [[(find-fn 'init) "cakes"]]))
(<!? (s/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Apr-05 19:16:25 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn init [_ name] {:store name, :data []}) cakes]] : 
;;;  {kordano@topiq.es {#uuid &quot;4b04aa42-2d56-49b0-acdd-a91eb8119380&quot; {master :foo}}})
;;; 2015-Apr-05 19:16:25 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;1261f65e-d043-5424-972a-a1a4bc7101a2&quot; {:transactions [[#uuid &quot;057e1301-3e1a-5292-8e71-7b9fd9c5ef3a&quot; #uuid &quot;18e24702-45d1-52ac-aa57-bcd0f30c09c8&quot;]], :ts #inst &quot;2015-04-05T17:16:25.924-00:00&quot;, :branch master, :parents [#uuid &quot;3004b2bd-3dd9-5524-a09c-2da166ffad6a&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Now let's see what this looks like:
;; **

;; @@
(v/vega-view (g/commit-graph (get-in @stage [user repo :state])))
;; @@
;; =>
;;; {"type":"vega","content":{"data":[{"name":"nodes","values":[{"value":"3004b2bd-3dd9-5524-a09c-2da166ffad6a","x":275,"y":185.41409301757812,"r":208,"g":26,"b":103},{"value":"1261f65e-d043-5424-972a-a1a4bc7101a2","x":530,"y":185.41409301757812,"r":208,"g":26,"b":103}]},{"name":"links","values":[{"path":"M 275.0 185.4141 L 530.0 185.4141"}]},{"name":"labels","values":[{"value":"master","x":530,"y":185.41409301757812}]}],"marks":[{"properties":{"enter":{"path":{"field":"data.path"},"stroke":{"value":"grey"},"strokeWidth":{"value":1}}},"type":"path","from":{"data":"links"}},{"properties":{"update":{"stroke":["value","transparent"],"size":{"value":90},"shape":"circle"},"enter":{"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"},"x":{"field":"data.x"}}},"type":"symbol","from":{"data":"nodes"}},{"properties":{"update":{"text":{"field":"data.value"}},"enter":{"y":{"field":"data.y"},"align":{"value":"left"},"dx":{"value":10},"fill":{"value":"black"},"x":{"field":"data.x"},"fontSize":{"value":15}}},"type":"text","from":{"data":"labels"}}],"width":600,"height":370.82818603515625,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:data [{:name \"nodes\", :values [{:value #uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\", :x 275, :y 185.41409301757812, :r 208, :g 26, :b 103} {:value #uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\", :x 530, :y 185.41409301757812, :r 208, :g 26, :b 103}]} {:name \"links\", :values [{:path \"M 275.0 185.4141 L 530.0 185.4141\"}]} {:name \"labels\", :values [{:value \"master\", :x 530, :y 185.41409301757812}]}], :marks [{:properties {:enter {:path {:field \"data.path\"}, :stroke {:value \"grey\"}, :strokeWidth {:value 1}}}, :type \"path\", :from {:data \"links\"}} {:properties {:update {:stroke [:value \"transparent\"], :size {:value 90}, :shape \"circle\"}, :enter {:y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}, :x {:field \"data.x\"}}}, :type \"symbol\", :from {:data \"nodes\"}} {:properties {:update {:text {:field \"data.value\"}}, :enter {:y {:field \"data.y\"}, :align {:value \"left\"}, :dx {:value 10}, :fill {:value \"black\"}, :x {:field \"data.x\"}, :fontSize {:value 15}}}, :type \"text\", :from {:data \"labels\"}}], :width 600, :height 370.8282, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; **
;;; Commit some more:
;; **

;; @@
(<!? (s/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "apple cake" :baker "eve"}]]]))
(<!? (s/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Apr-05 19:16:30 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker eve, :name apple cake}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;4b04aa42-2d56-49b0-acdd-a91eb8119380&quot; {master :foo}}})
;;; 2015-Apr-05 19:16:30 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;2499b250-8d7e-56b3-9d38-7a27a69b1450&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;2d5727fa-06a9-510a-b108-21a32241ea5c&quot;]], :ts #inst &quot;2015-04-05T17:16:30.094-00:00&quot;, :branch master, :parents [#uuid &quot;1261f65e-d043-5424-972a-a1a4bc7101a2&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<!? (s/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "butter cake" :baker "adam"}]]]))
(<!? (s/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Apr-05 19:16:31 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker adam, :name butter cake}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;4b04aa42-2d56-49b0-acdd-a91eb8119380&quot; {master :foo}}})
;;; 2015-Apr-05 19:16:31 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;251e51c9-5d14-54af-8abe-a8a83f56c95c&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;0a524374-b1db-5e1c-8c5b-bff4bd0072af&quot;]], :ts #inst &quot;2015-04-05T17:16:31.500-00:00&quot;, :branch master, :parents [#uuid &quot;2499b250-8d7e-56b3-9d38-7a27a69b1450&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(v/vega-view (g/commit-graph (get-in @stage [user repo :state])))
;; @@
;; =>
;;; {"type":"vega","content":{"data":[{"name":"nodes","values":[{"value":"3004b2bd-3dd9-5524-a09c-2da166ffad6a","x":147.5,"y":185.41409301757812,"r":176,"g":10,"b":156},{"value":"1261f65e-d043-5424-972a-a1a4bc7101a2","x":275,"y":185.41409301757812,"r":176,"g":10,"b":156},{"value":"2499b250-8d7e-56b3-9d38-7a27a69b1450","x":402.5,"y":185.41409301757812,"r":176,"g":10,"b":156},{"value":"251e51c9-5d14-54af-8abe-a8a83f56c95c","x":530,"y":185.41409301757812,"r":176,"g":10,"b":156}]},{"name":"links","values":[{"path":"M 147.5 185.4141 L 275.0 185.4141"},{"path":"M 275.0 185.4141 L 402.5 185.4141"},{"path":"M 402.5 185.4141 L 530.0 185.4141"}]},{"name":"labels","values":[{"value":"master","x":530,"y":185.41409301757812}]}],"marks":[{"properties":{"enter":{"path":{"field":"data.path"},"stroke":{"value":"grey"},"strokeWidth":{"value":1}}},"type":"path","from":{"data":"links"}},{"properties":{"update":{"stroke":["value","transparent"],"size":{"value":90},"shape":"circle"},"enter":{"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"},"x":{"field":"data.x"}}},"type":"symbol","from":{"data":"nodes"}},{"properties":{"update":{"text":{"field":"data.value"}},"enter":{"y":{"field":"data.y"},"align":{"value":"left"},"dx":{"value":10},"fill":{"value":"black"},"x":{"field":"data.x"},"fontSize":{"value":15}}},"type":"text","from":{"data":"labels"}}],"width":600,"height":370.82818603515625,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:data [{:name \"nodes\", :values [{:value #uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\", :x 295/2, :y 185.41409301757812, :r 176, :g 10, :b 156} {:value #uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\", :x 275N, :y 185.41409301757812, :r 176, :g 10, :b 156} {:value #uuid \"2499b250-8d7e-56b3-9d38-7a27a69b1450\", :x 805/2, :y 185.41409301757812, :r 176, :g 10, :b 156} {:value #uuid \"251e51c9-5d14-54af-8abe-a8a83f56c95c\", :x 530N, :y 185.41409301757812, :r 176, :g 10, :b 156}]} {:name \"links\", :values [{:path \"M 147.5 185.4141 L 275.0 185.4141\"} {:path \"M 275.0 185.4141 L 402.5 185.4141\"} {:path \"M 402.5 185.4141 L 530.0 185.4141\"}]} {:name \"labels\", :values [{:value \"master\", :x 530N, :y 185.41409301757812}]}], :marks [{:properties {:enter {:path {:field \"data.path\"}, :stroke {:value \"grey\"}, :strokeWidth {:value 1}}}, :type \"path\", :from {:data \"links\"}} {:properties {:update {:stroke [:value \"transparent\"], :size {:value 90}, :shape \"circle\"}, :enter {:y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}, :x {:field \"data.x\"}}}, :type \"symbol\", :from {:data \"nodes\"}} {:properties {:update {:text {:field \"data.value\"}}, :enter {:y {:field \"data.y\"}, :align {:value \"left\"}, :dx {:value 10}, :fill {:value \"black\"}, :x {:field \"data.x\"}, :fontSize {:value 15}}}, :type \"text\", :from {:data \"labels\"}}], :width 600, :height 370.8282, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; @@
(<!? (s/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "cheese cake" :baker "eve"}]]]))
(<!? (s/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Apr-05 19:16:33 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker eve, :name cheese cake}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;4b04aa42-2d56-49b0-acdd-a91eb8119380&quot; {master :foo}}})
;;; 2015-Apr-05 19:16:33 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;2f887e43-5bdb-5579-8cf4-e5670850decb&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;3edf069a-073b-5023-a652-965ed4ff3113&quot;]], :ts #inst &quot;2015-04-05T17:16:33.659-00:00&quot;, :branch master, :parents [#uuid &quot;251e51c9-5d14-54af-8abe-a8a83f56c95c&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Let's make a *branch* for desserts
;; **

;; @@
(<!? (s/branch! stage
                  [user repo]
                  "desserts"
                  (first (get-in @stage [user repo :state :branches "master"]))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<!? (s/checkout! stage [user repo] "desserts"))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<!? (s/transact stage [user repo "desserts"] [[(find-fn 'transact-entry) [{:name "chocolate strawberries" :baker "eve"}]]]))
(<!? (s/commit! stage {user {repo #{"desserts"}}}))
;; @@
;; ->
;;; 2015-Apr-05 19:16:37 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker eve, :name chocolate strawberries}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;4b04aa42-2d56-49b0-acdd-a91eb8119380&quot; {desserts :foo, master :foo}}})
;;; 2015-Apr-05 19:16:37 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  desserts :  #uuid &quot;01750728-d771-5bb0-8cef-659acad25a95&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;1ceab641-dd8c-55fd-9a9d-008495955e6d&quot;]], :ts #inst &quot;2015-04-05T17:16:37.304-00:00&quot;, :branch desserts, :parents [#uuid &quot;2f887e43-5bdb-5579-8cf4-e5670850decb&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<!? (s/transact stage [user repo "desserts"] [[(find-fn 'transact-entry) [{:name "gingerbread" :baker "adam"}]]]))
(<!? (s/commit! stage {user {repo #{"desserts"}}}))
;; @@
;; ->
;;; 2015-Apr-05 19:16:37 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker adam, :name gingerbread}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;4b04aa42-2d56-49b0-acdd-a91eb8119380&quot; {desserts :foo, master :foo}}})
;;; 2015-Apr-05 19:16:37 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  desserts :  #uuid &quot;0cc8f093-9ada-5bb5-97a8-d5b4b595f10c&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;1a55eecb-c27a-5fcc-9b16-79030064d103&quot;]], :ts #inst &quot;2015-04-05T17:16:37.913-00:00&quot;, :branch desserts, :parents [#uuid &quot;01750728-d771-5bb0-8cef-659acad25a95&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(v/vega-view (g/commit-graph (get-in @stage [user repo :state])))
;; @@
;; =>
;;; {"type":"vega","content":{"data":[{"name":"nodes","values":[{"value":"3004b2bd-3dd9-5524-a09c-2da166ffad6a","x":122,"y":268.1211395263672,"r":0,"g":177,"b":25},{"value":"1261f65e-d043-5424-972a-a1a4bc7101a2","x":224,"y":268.1211395263672,"r":0,"g":177,"b":25},{"value":"2499b250-8d7e-56b3-9d38-7a27a69b1450","x":326,"y":268.1211395263672,"r":0,"g":177,"b":25},{"value":"251e51c9-5d14-54af-8abe-a8a83f56c95c","x":428,"y":268.1211395263672,"r":0,"g":177,"b":25},{"value":"2f887e43-5bdb-5579-8cf4-e5670850decb","x":530,"y":268.1211395263672,"r":0,"g":177,"b":25},{"value":"01750728-d771-5bb0-8cef-659acad25a95","x":275,"y":102.70704650878906,"r":95,"g":185,"b":0},{"value":"0cc8f093-9ada-5bb5-97a8-d5b4b595f10c","x":530,"y":102.70704650878906,"r":95,"g":185,"b":0}]},{"name":"links","values":[{"path":"M 122.0 268.12115 L 224.0 268.12115"},{"path":"M 224.0 268.12115 L 326.0 268.12115"},{"path":"M 326.0 268.12115 L 428.0 268.12115"},{"path":"M 428.0 268.12115 L 530.0 268.12115"},{"path":"M 275.0 102.70705 L 530.0 102.70705"}]},{"name":"labels","values":[{"value":"desserts","x":530,"y":102.70704650878906},{"value":"master","x":530,"y":268.1211395263672}]}],"marks":[{"properties":{"enter":{"path":{"field":"data.path"},"stroke":{"value":"grey"},"strokeWidth":{"value":1}}},"type":"path","from":{"data":"links"}},{"properties":{"update":{"stroke":["value","transparent"],"size":{"value":90},"shape":"circle"},"enter":{"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"},"x":{"field":"data.x"}}},"type":"symbol","from":{"data":"nodes"}},{"properties":{"update":{"text":{"field":"data.value"}},"enter":{"y":{"field":"data.y"},"align":{"value":"left"},"dx":{"value":10},"fill":{"value":"black"},"x":{"field":"data.x"},"fontSize":{"value":15}}},"type":"text","from":{"data":"labels"}}],"width":600,"height":370.82818603515625,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:data [{:name \"nodes\", :values [{:value #uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\", :x 122, :y 268.1211395263672, :r 0, :g 177, :b 25} {:value #uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\", :x 224, :y 268.1211395263672, :r 0, :g 177, :b 25} {:value #uuid \"2499b250-8d7e-56b3-9d38-7a27a69b1450\", :x 326, :y 268.1211395263672, :r 0, :g 177, :b 25} {:value #uuid \"251e51c9-5d14-54af-8abe-a8a83f56c95c\", :x 428, :y 268.1211395263672, :r 0, :g 177, :b 25} {:value #uuid \"2f887e43-5bdb-5579-8cf4-e5670850decb\", :x 530, :y 268.1211395263672, :r 0, :g 177, :b 25} {:value #uuid \"01750728-d771-5bb0-8cef-659acad25a95\", :x 275, :y 102.70704650878906, :r 95, :g 185, :b 0} {:value #uuid \"0cc8f093-9ada-5bb5-97a8-d5b4b595f10c\", :x 530, :y 102.70704650878906, :r 95, :g 185, :b 0}]} {:name \"links\", :values [{:path \"M 122.0 268.12115 L 224.0 268.12115\"} {:path \"M 224.0 268.12115 L 326.0 268.12115\"} {:path \"M 326.0 268.12115 L 428.0 268.12115\"} {:path \"M 428.0 268.12115 L 530.0 268.12115\"} {:path \"M 275.0 102.70705 L 530.0 102.70705\"}]} {:name \"labels\", :values [{:value \"desserts\", :x 530, :y 102.70704650878906} {:value \"master\", :x 530, :y 268.1211395263672}]}], :marks [{:properties {:enter {:path {:field \"data.path\"}, :stroke {:value \"grey\"}, :strokeWidth {:value 1}}}, :type \"path\", :from {:data \"links\"}} {:properties {:update {:stroke [:value \"transparent\"], :size {:value 90}, :shape \"circle\"}, :enter {:y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}, :x {:field \"data.x\"}}}, :type \"symbol\", :from {:data \"nodes\"}} {:properties {:update {:text {:field \"data.value\"}}, :enter {:y {:field \"data.y\"}, :align {:value \"left\"}, :dx {:value 10}, :fill {:value \"black\"}, :x {:field \"data.x\"}, :fontSize {:value 15}}}, :type \"text\", :from {:data \"labels\"}}], :width 600, :height 370.8282, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; @@
(<!? (s/checkout! stage [user repo] "master"))

;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<!? (s/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "baumkuchen" :baker "eve"}]]]))
(<!? (s/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Apr-05 19:18:22 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker eve, :name baumkuchen}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;4b04aa42-2d56-49b0-acdd-a91eb8119380&quot; {desserts :foo, master :foo}}})
;;; 2015-Apr-05 19:18:22 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;114ae591-d493-58fd-ab46-2da8854f540d&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;1cb833ba-497b-5e63-bdd5-6f2355ac75e6&quot;]], :ts #inst &quot;2015-04-05T17:18:22.740-00:00&quot;, :branch master, :parents [#uuid &quot;2f887e43-5bdb-5579-8cf4-e5670850decb&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<!? (s/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "apple pie" :baker "eve"}]]]))
(<!? (s/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Apr-05 19:18:39 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker eve, :name apple pie}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;4b04aa42-2d56-49b0-acdd-a91eb8119380&quot; {desserts :foo, master :foo}}})
;;; 2015-Apr-05 19:18:39 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;3144f9a8-10ea-5cce-bd6a-8d1510131ec5&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;14dd32f9-44e3-5ef5-80ca-274358539e87&quot;]], :ts #inst &quot;2015-04-05T17:18:39.057-00:00&quot;, :branch master, :parents [#uuid &quot;114ae591-d493-58fd-ab46-2da8854f540d&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(<!? (s/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "brownie" :baker "adam"}]]]))
(<!? (s/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Apr-05 19:19:20 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker adam, :name brownie}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;4b04aa42-2d56-49b0-acdd-a91eb8119380&quot; {desserts :foo, master :foo}}})
;;; 2015-Apr-05 19:19:20 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;3d2d64de-5e7f-5b86-b432-e882d6846134&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;3aa38840-6296-5432-a8e3-f0a3c80e48eb&quot;]], :ts #inst &quot;2015-04-05T17:19:20.438-00:00&quot;, :branch master, :parents [#uuid &quot;3144f9a8-10ea-5cce-bd6a-8d1510131ec5&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(v/vega-view (g/commit-graph (get-in @stage [user repo :state])))
;; @@
;; =>
;;; {"type":"vega","content":{"data":[{"name":"nodes","values":[{"value":"3004b2bd-3dd9-5524-a09c-2da166ffad6a","x":83.75,"y":268.1211395263672,"r":249,"g":52,"b":79},{"value":"1261f65e-d043-5424-972a-a1a4bc7101a2","x":147.5,"y":268.1211395263672,"r":249,"g":52,"b":79},{"value":"2499b250-8d7e-56b3-9d38-7a27a69b1450","x":211.25,"y":268.1211395263672,"r":249,"g":52,"b":79},{"value":"251e51c9-5d14-54af-8abe-a8a83f56c95c","x":275,"y":268.1211395263672,"r":249,"g":52,"b":79},{"value":"2f887e43-5bdb-5579-8cf4-e5670850decb","x":338.75,"y":268.1211395263672,"r":249,"g":52,"b":79},{"value":"114ae591-d493-58fd-ab46-2da8854f540d","x":402.5,"y":268.1211395263672,"r":249,"g":52,"b":79},{"value":"3144f9a8-10ea-5cce-bd6a-8d1510131ec5","x":466.25,"y":268.1211395263672,"r":249,"g":52,"b":79},{"value":"3d2d64de-5e7f-5b86-b432-e882d6846134","x":530,"y":268.1211395263672,"r":249,"g":52,"b":79},{"value":"01750728-d771-5bb0-8cef-659acad25a95","x":434.375,"y":102.70704650878906,"r":167,"g":135,"b":197},{"value":"0cc8f093-9ada-5bb5-97a8-d5b4b595f10c","x":530,"y":102.70704650878906,"r":167,"g":135,"b":197}]},{"name":"links","values":[{"path":"M 83.75 268.12115 L 147.5 268.12115"},{"path":"M 147.5 268.12115 L 211.25 268.12115"},{"path":"M 211.25 268.12115 L 275.0 268.12115"},{"path":"M 275.0 268.12115 L 338.75 268.12115"},{"path":"M 338.75 268.12115 L 402.5 268.12115"},{"path":"M 402.5 268.12115 L 466.25 268.12115"},{"path":"M 466.25 268.12115 L 530.0 268.12115"},{"path":"M 434.375 102.70705 L 530.0 102.70705"},{"path":"M 338.75 268.12115 L 434.375 102.70705"}]},{"name":"labels","values":[{"value":"desserts","x":530,"y":102.70704650878906},{"value":"master","x":530,"y":268.1211395263672}]}],"marks":[{"properties":{"enter":{"path":{"field":"data.path"},"stroke":{"value":"grey"},"strokeWidth":{"value":1}}},"type":"path","from":{"data":"links"}},{"properties":{"update":{"stroke":["value","transparent"],"size":{"value":90},"shape":"circle"},"enter":{"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"},"x":{"field":"data.x"}}},"type":"symbol","from":{"data":"nodes"}},{"properties":{"update":{"text":{"field":"data.value"}},"enter":{"y":{"field":"data.y"},"align":{"value":"left"},"dx":{"value":10},"fill":{"value":"black"},"x":{"field":"data.x"},"fontSize":{"value":15}}},"type":"text","from":{"data":"labels"}}],"width":600,"height":370.82818603515625,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:data [{:name \"nodes\", :values [{:value #uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\", :x 335/4, :y 268.1211395263672, :r 249, :g 52, :b 79} {:value #uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\", :x 295/2, :y 268.1211395263672, :r 249, :g 52, :b 79} {:value #uuid \"2499b250-8d7e-56b3-9d38-7a27a69b1450\", :x 845/4, :y 268.1211395263672, :r 249, :g 52, :b 79} {:value #uuid \"251e51c9-5d14-54af-8abe-a8a83f56c95c\", :x 275N, :y 268.1211395263672, :r 249, :g 52, :b 79} {:value #uuid \"2f887e43-5bdb-5579-8cf4-e5670850decb\", :x 1355/4, :y 268.1211395263672, :r 249, :g 52, :b 79} {:value #uuid \"114ae591-d493-58fd-ab46-2da8854f540d\", :x 805/2, :y 268.1211395263672, :r 249, :g 52, :b 79} {:value #uuid \"3144f9a8-10ea-5cce-bd6a-8d1510131ec5\", :x 1865/4, :y 268.1211395263672, :r 249, :g 52, :b 79} {:value #uuid \"3d2d64de-5e7f-5b86-b432-e882d6846134\", :x 530N, :y 268.1211395263672, :r 249, :g 52, :b 79} {:value #uuid \"01750728-d771-5bb0-8cef-659acad25a95\", :x 3475/8, :y 102.70704650878906, :r 167, :g 135, :b 197} {:value #uuid \"0cc8f093-9ada-5bb5-97a8-d5b4b595f10c\", :x 530N, :y 102.70704650878906, :r 167, :g 135, :b 197}]} {:name \"links\", :values [{:path \"M 83.75 268.12115 L 147.5 268.12115\"} {:path \"M 147.5 268.12115 L 211.25 268.12115\"} {:path \"M 211.25 268.12115 L 275.0 268.12115\"} {:path \"M 275.0 268.12115 L 338.75 268.12115\"} {:path \"M 338.75 268.12115 L 402.5 268.12115\"} {:path \"M 402.5 268.12115 L 466.25 268.12115\"} {:path \"M 466.25 268.12115 L 530.0 268.12115\"} {:path \"M 434.375 102.70705 L 530.0 102.70705\"} {:path \"M 338.75 268.12115 L 434.375 102.70705\"}]} {:name \"labels\", :values [{:value \"desserts\", :x 530N, :y 102.70704650878906} {:value \"master\", :x 530N, :y 268.1211395263672}]}], :marks [{:properties {:enter {:path {:field \"data.path\"}, :stroke {:value \"grey\"}, :strokeWidth {:value 1}}}, :type \"path\", :from {:data \"links\"}} {:properties {:update {:stroke [:value \"transparent\"], :size {:value 90}, :shape \"circle\"}, :enter {:y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}, :x {:field \"data.x\"}}}, :type \"symbol\", :from {:data \"nodes\"}} {:properties {:update {:text {:field \"data.value\"}}, :enter {:y {:field \"data.y\"}, :align {:value \"left\"}, :dx {:value 10}, :fill {:value \"black\"}, :x {:field \"data.x\"}, :fontSize {:value 15}}}, :type \"text\", :from {:data \"labels\"}}], :width 600, :height 370.8282, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; @@

;; @@
