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
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:volatile</span>","value":":volatile"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:new-conns</span>","value":":new-conns"},{"type":"html","content":"<span class='clj-unkown'>#&lt;ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@37b45a53&gt;</span>","value":"#<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@37b45a53>"}],"value":"[:new-conns #<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@37b45a53>]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:store</span>","value":":store"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:state</span>","value":":state"},{"type":"html","content":"<span class='clj-atom'>#&lt;Atom@21961bb7: {}&gt;</span>","value":"#<Atom@21961bb7: {}>"}],"value":"[:state #<Atom@21961bb7: {}>]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:tag-table</span>","value":":tag-table"},{"type":"html","content":"<span class='clj-atom'>#&lt;Atom@5f59c9b: {}&gt;</span>","value":"#<Atom@5f59c9b: {}>"}],"value":"[:tag-table #<Atom@5f59c9b: {}>]"}],"value":"#konserve.store.MemAsyncKeyValueStore{:state #<Atom@21961bb7: {}>, :tag-table #<Atom@5f59c9b: {}>}"}],"value":"[:store #konserve.store.MemAsyncKeyValueStore{:state #<Atom@21961bb7: {}>, :tag-table #<Atom@5f59c9b: {}>}]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:server</span>","value":":server"},{"type":"html","content":"<span class='clj-unkown'>#&lt;clojure.lang.AFunction$1@29b7a609&gt;</span>","value":"#<clojure.lang.AFunction$1@29b7a609>"}],"value":"[:server #<clojure.lang.AFunction$1@29b7a609>]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:error-ch</span>","value":":error-ch"},{"type":"html","content":"<span class='clj-unkown'>#&lt;ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@57f5ae44&gt;</span>","value":"#<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@57f5ae44>"}],"value":"[:error-ch #<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@57f5ae44>]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:chans</span>","value":":chans"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>#&lt;ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@42841f79&gt;</span>","value":"#<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@42841f79>"},{"type":"html","content":"<span class='clj-unkown'>#&lt;async$pub$reify__18654 clojure.core.async$pub$reify__18654@7568e25d&gt;</span>","value":"#<async$pub$reify__18654 clojure.core.async$pub$reify__18654@7568e25d>"}],"value":"[#<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@42841f79> #<async$pub$reify__18654 clojure.core.async$pub$reify__18654@7568e25d>]"}],"value":"[:chans [#<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@42841f79> #<async$pub$reify__18654 clojure.core.async$pub$reify__18654@7568e25d>]]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:middleware</span>","value":":middleware"},{"type":"html","content":"<span class='clj-unkown'>#&lt;core$comp$fn__4196 clojure.core$comp$fn__4196@6435c943&gt;</span>","value":"#<core$comp$fn__4196 clojure.core$comp$fn__4196@6435c943>"}],"value":"[:middleware #<core$comp$fn__4196 clojure.core$comp$fn__4196@6435c943>]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:url</span>","value":":url"},{"type":"html","content":"<span class='clj-string'>&quot;ws://127.0.0.1:37050&quot;</span>","value":"\"ws://127.0.0.1:37050\""}],"value":"[:url \"ws://127.0.0.1:37050\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:channel-hub</span>","value":":channel-hub"},{"type":"html","content":"<span class='clj-atom'>#&lt;Atom@730811b: {}&gt;</span>","value":"#<Atom@730811b: {}>"}],"value":"[:channel-hub #<Atom@730811b: {}>]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:handler</span>","value":":handler"},{"type":"html","content":"<span class='clj-unkown'>#&lt;platform$create_http_kit_handler_BANG_$handler__22145 geschichte.platform$create_http_kit_handler_BANG_$handler__22145@5f6dc0f5&gt;</span>","value":"#<platform$create_http_kit_handler_BANG_$handler__22145 geschichte.platform$create_http_kit_handler_BANG_$handler__22145@5f6dc0f5>"}],"value":"[:handler #<platform$create_http_kit_handler_BANG_$handler__22145 geschichte.platform$create_http_kit_handler_BANG_$handler__22145@5f6dc0f5>]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:log</span>","value":":log"},{"type":"html","content":"<span class='clj-atom'>#&lt;Atom@1e9745c5: {}&gt;</span>","value":"#<Atom@1e9745c5: {}>"}],"value":"[:log #<Atom@1e9745c5: {}>]"}],"value":"{:new-conns #<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@37b45a53>, :store #konserve.store.MemAsyncKeyValueStore{:state #<Atom@21961bb7: {}>, :tag-table #<Atom@5f59c9b: {}>}, :server #<clojure.lang.AFunction$1@29b7a609>, :error-ch #<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@57f5ae44>, :chans [#<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@42841f79> #<async$pub$reify__18654 clojure.core.async$pub$reify__18654@7568e25d>], :middleware #<core$comp$fn__4196 clojure.core$comp$fn__4196@6435c943>, :url \"ws://127.0.0.1:37050\", :channel-hub #<Atom@730811b: {}>, :handler #<platform$create_http_kit_handler_BANG_$handler__22145 geschichte.platform$create_http_kit_handler_BANG_$handler__22145@5f6dc0f5>, :log #<Atom@1e9745c5: {}>}"}],"value":"[:volatile {:new-conns #<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@37b45a53>, :store #konserve.store.MemAsyncKeyValueStore{:state #<Atom@21961bb7: {}>, :tag-table #<Atom@5f59c9b: {}>}, :server #<clojure.lang.AFunction$1@29b7a609>, :error-ch #<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@57f5ae44>, :chans [#<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@42841f79> #<async$pub$reify__18654 clojure.core.async$pub$reify__18654@7568e25d>], :middleware #<core$comp$fn__4196 clojure.core$comp$fn__4196@6435c943>, :url \"ws://127.0.0.1:37050\", :channel-hub #<Atom@730811b: {}>, :handler #<platform$create_http_kit_handler_BANG_$handler__22145 geschichte.platform$create_http_kit_handler_BANG_$handler__22145@5f6dc0f5>, :log #<Atom@1e9745c5: {}>}]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:name</span>","value":":name"},{"type":"html","content":"<span class='clj-string'>&quot;ws://127.0.0.1:37050&quot;</span>","value":"\"ws://127.0.0.1:37050\""}],"value":"[:name \"ws://127.0.0.1:37050\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:meta-sub</span>","value":":meta-sub"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[],"value":"{}"}],"value":"[:meta-sub {}]"}],"value":"{:volatile {:new-conns #<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@37b45a53>, :store #konserve.store.MemAsyncKeyValueStore{:state #<Atom@21961bb7: {}>, :tag-table #<Atom@5f59c9b: {}>}, :server #<clojure.lang.AFunction$1@29b7a609>, :error-ch #<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@57f5ae44>, :chans [#<ManyToManyChannel clojure.core.async.impl.channels.ManyToManyChannel@42841f79> #<async$pub$reify__18654 clojure.core.async$pub$reify__18654@7568e25d>], :middleware #<core$comp$fn__4196 clojure.core$comp$fn__4196@6435c943>, :url \"ws://127.0.0.1:37050\", :channel-hub #<Atom@730811b: {}>, :handler #<platform$create_http_kit_handler_BANG_$handler__22145 geschichte.platform$create_http_kit_handler_BANG_$handler__22145@5f6dc0f5>, :log #<Atom@1e9745c5: {}>}, :name \"ws://127.0.0.1:37050\", :meta-sub {}}"}
;; <=

;; **
;;; Create a local *stage* and connect it to the peer.
;; **

;; @@
(def stage (<!? (s/create-stage! user peer eval)))
(<!? (s/connect! stage socket))
;; @@
;; ->
;;; 2015-Apr-05 18:48:26 +0200 phobos INFO [geschichte.platform-log] - (ws://127.0.0.1:37050 connecting to: ws://127.0.0.1:37050)
;;; 2015-Apr-05 18:48:26 +0200 phobos DEBUG [geschichte.platform-log] - (connect: backsubscription? #uuid &quot;0e96c47b-9e24-4ab0-8d29-f1fdb54ed317&quot; {:topic :meta-subed, :metas {}, :peer ws://127.0.0.1:37050, :id #uuid &quot;0e96c47b-9e24-4ab0-8d29-f1fdb54ed317&quot;})
;;; 2015-Apr-05 18:48:26 +0200 phobos INFO [geschichte.platform-log] - (ws://127.0.0.1:37050 subscribe: starting subscription  #uuid &quot;0e96c47b-9e24-4ab0-8d29-f1fdb54ed317&quot;  from  ws://127.0.0.1:37050)
;;; 2015-Apr-05 18:48:26 +0200 phobos INFO [geschichte.platform-log] - (connect!: connected  ws://127.0.0.1:37050)
;;; 2015-Apr-05 18:48:26 +0200 phobos DEBUG [geschichte.platform-log] - (ws://127.0.0.1:37050 subscribe: subscriptions  {})
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
;;; 2015-Apr-05 18:49:46 +0200 phobos DEBUG [geschichte.platform-log] - (creating new repo for  kordano@topiq.es with id #uuid &quot;7cedd8db-af7f-41bd-95fc-b8e217309cbc&quot;)
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
;;; 2015-Apr-05 18:56:04 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn init [_ name] {:store name, :data []}) cakes]] : 
;;;  {kordano@topiq.es {#uuid &quot;7cedd8db-af7f-41bd-95fc-b8e217309cbc&quot; {master :foo}}})
;;; 2015-Apr-05 18:56:04 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;1261f65e-d043-5424-972a-a1a4bc7101a2&quot; {:transactions [[#uuid &quot;057e1301-3e1a-5292-8e71-7b9fd9c5ef3a&quot; #uuid &quot;18e24702-45d1-52ac-aa57-bcd0f30c09c8&quot;]], :ts #inst &quot;2015-04-05T16:56:04.272-00:00&quot;, :branch master, :parents [#uuid &quot;3004b2bd-3dd9-5524-a09c-2da166ffad6a&quot;], :author kordano@topiq.es})
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
;;; {"type":"vega","content":{"data":[{"name":"nodes","values":[{"value":"3004b2bd-3dd9-5524-a09c-2da166ffad6a","x":250,"y":154.51174926757812,"r":85,"g":178,"b":42},{"value":"1261f65e-d043-5424-972a-a1a4bc7101a2","x":480,"y":154.51174926757812,"r":85,"g":178,"b":42}]},{"name":"links","values":[{"path":"M 250.0 154.51175 L 480.0 154.51175"}]}],"marks":[{"properties":{"enter":{"path":{"field":"data.path"},"stroke":{"value":"grey"},"strokeWidth":{"value":1}}},"type":"path","from":{"data":"links"}},{"properties":{"update":{"stroke":["value","transparent"],"size":{"value":90},"shape":"circle"},"enter":{"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"},"x":{"field":"data.x"}}},"type":"symbol","from":{"data":"nodes"}}],"width":500,"height":309.02349853515625,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:data [{:name \"nodes\", :values [{:value #uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\", :x 250, :y 154.51174926757812, :r 85, :g 178, :b 42} {:value #uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\", :x 480, :y 154.51174926757812, :r 85, :g 178, :b 42}]} {:name \"links\", :values [{:path \"M 250.0 154.51175 L 480.0 154.51175\"}]}], :marks [{:properties {:enter {:path {:field \"data.path\"}, :stroke {:value \"grey\"}, :strokeWidth {:value 1}}}, :type \"path\", :from {:data \"links\"}} {:properties {:update {:stroke [:value \"transparent\"], :size {:value 90}, :shape \"circle\"}, :enter {:y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}, :x {:field \"data.x\"}}}, :type \"symbol\", :from {:data \"nodes\"}}], :width 500, :height 309.0235, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; **
;;; Commit some more:
;; **

;; @@
(<!? (s/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "apple cake" :baker "eve"}]]]))
(<!? (s/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Apr-05 19:01:31 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker eve, :name apple cake}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;7cedd8db-af7f-41bd-95fc-b8e217309cbc&quot; {master :foo}}})
;;; 2015-Apr-05 19:01:31 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;2499b250-8d7e-56b3-9d38-7a27a69b1450&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;2d5727fa-06a9-510a-b108-21a32241ea5c&quot;]], :ts #inst &quot;2015-04-05T17:01:31.376-00:00&quot;, :branch master, :parents [#uuid &quot;1261f65e-d043-5424-972a-a1a4bc7101a2&quot;], :author kordano@topiq.es})
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
;;; 2015-Apr-05 19:02:38 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker adam, :name butter cake}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;7cedd8db-af7f-41bd-95fc-b8e217309cbc&quot; {master :foo}}})
;;; 2015-Apr-05 19:02:38 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;251e51c9-5d14-54af-8abe-a8a83f56c95c&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;0a524374-b1db-5e1c-8c5b-bff4bd0072af&quot;]], :ts #inst &quot;2015-04-05T17:02:38.744-00:00&quot;, :branch master, :parents [#uuid &quot;2499b250-8d7e-56b3-9d38-7a27a69b1450&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(v/vega-view (g/commit-graph (get-in @stage [user repo :state])))
;; @@
;; =>
;;; {"type":"vega","content":{"data":[{"name":"nodes","values":[{"value":"3004b2bd-3dd9-5524-a09c-2da166ffad6a","x":135,"y":154.51174926757812,"r":102,"g":46,"b":218},{"value":"1261f65e-d043-5424-972a-a1a4bc7101a2","x":250,"y":154.51174926757812,"r":102,"g":46,"b":218},{"value":"2499b250-8d7e-56b3-9d38-7a27a69b1450","x":365,"y":154.51174926757812,"r":102,"g":46,"b":218},{"value":"251e51c9-5d14-54af-8abe-a8a83f56c95c","x":480,"y":154.51174926757812,"r":102,"g":46,"b":218}]},{"name":"links","values":[{"path":"M 135.0 154.51175 L 250.0 154.51175"},{"path":"M 250.0 154.51175 L 365.0 154.51175"},{"path":"M 365.0 154.51175 L 480.0 154.51175"}]}],"marks":[{"properties":{"enter":{"path":{"field":"data.path"},"stroke":{"value":"grey"},"strokeWidth":{"value":1}}},"type":"path","from":{"data":"links"}},{"properties":{"update":{"stroke":["value","transparent"],"size":{"value":90},"shape":"circle"},"enter":{"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"},"x":{"field":"data.x"}}},"type":"symbol","from":{"data":"nodes"}}],"width":500,"height":309.02349853515625,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:data [{:name \"nodes\", :values [{:value #uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\", :x 135, :y 154.51174926757812, :r 102, :g 46, :b 218} {:value #uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\", :x 250, :y 154.51174926757812, :r 102, :g 46, :b 218} {:value #uuid \"2499b250-8d7e-56b3-9d38-7a27a69b1450\", :x 365, :y 154.51174926757812, :r 102, :g 46, :b 218} {:value #uuid \"251e51c9-5d14-54af-8abe-a8a83f56c95c\", :x 480, :y 154.51174926757812, :r 102, :g 46, :b 218}]} {:name \"links\", :values [{:path \"M 135.0 154.51175 L 250.0 154.51175\"} {:path \"M 250.0 154.51175 L 365.0 154.51175\"} {:path \"M 365.0 154.51175 L 480.0 154.51175\"}]}], :marks [{:properties {:enter {:path {:field \"data.path\"}, :stroke {:value \"grey\"}, :strokeWidth {:value 1}}}, :type \"path\", :from {:data \"links\"}} {:properties {:update {:stroke [:value \"transparent\"], :size {:value 90}, :shape \"circle\"}, :enter {:y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}, :x {:field \"data.x\"}}}, :type \"symbol\", :from {:data \"nodes\"}}], :width 500, :height 309.0235, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; @@
(<!? (s/transact stage [user repo "master"] [[(find-fn 'transact-entry) [{:name "cheese cake" :baker "eve"}]]]))
(<!? (s/commit! stage {user {repo #{"master"}}}))
;; @@
;; ->
;;; 2015-Apr-05 19:03:41 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker eve, :name cheese cake}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;7cedd8db-af7f-41bd-95fc-b8e217309cbc&quot; {master :foo}}})
;;; 2015-Apr-05 19:03:41 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  master :  #uuid &quot;2f887e43-5bdb-5579-8cf4-e5670850decb&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;3edf069a-073b-5023-a652-965ed4ff3113&quot;]], :ts #inst &quot;2015-04-05T17:03:41.271-00:00&quot;, :branch master, :parents [#uuid &quot;251e51c9-5d14-54af-8abe-a8a83f56c95c&quot;], :author kordano@topiq.es})
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
;; ->
;;; 2015-Apr-05 19:05:05 +0200 phobos DEBUG [geschichte.platform-log] - (fetching success for  #{})
;;; 
;; <-
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
;;; 2015-Apr-05 19:06:37 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker eve, :name chocolate strawberries}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;7cedd8db-af7f-41bd-95fc-b8e217309cbc&quot; {desserts :foo, master :foo}}})
;;; 2015-Apr-05 19:06:37 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  desserts :  #uuid &quot;01750728-d771-5bb0-8cef-659acad25a95&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;1ceab641-dd8c-55fd-9a9d-008495955e6d&quot;]], :ts #inst &quot;2015-04-05T17:06:37.520-00:00&quot;, :branch desserts, :parents [#uuid &quot;2f887e43-5bdb-5579-8cf4-e5670850decb&quot;], :author kordano@topiq.es})
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
;;; 2015-Apr-05 19:08:03 +0200 phobos INFO [geschichte.platform-log] - (transact: new stage value after trans  [[(fn transact-entry [old entry] (update-in old [:data] concat entry)) [{:baker adam, :name gingerbread}]]] : 
;;;  {kordano@topiq.es {#uuid &quot;7cedd8db-af7f-41bd-95fc-b8e217309cbc&quot; {desserts :foo, master :foo}}})
;;; 2015-Apr-05 19:08:03 +0200 phobos DEBUG [geschichte.platform-log] - (committing to  desserts :  #uuid &quot;0cc8f093-9ada-5bb5-97a8-d5b4b595f10c&quot; {:transactions [[#uuid &quot;0472fcb0-9f62-5e7e-9222-5ea9cd12a15f&quot; #uuid &quot;1a55eecb-c27a-5fcc-9b16-79030064d103&quot;]], :ts #inst &quot;2015-04-05T17:08:03.063-00:00&quot;, :branch desserts, :parents [#uuid &quot;01750728-d771-5bb0-8cef-659acad25a95&quot;], :author kordano@topiq.es})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(v/vega-view (g/commit-graph (get-in @stage [user repo :state])))
;; @@
;; =>
;;; {"type":"vega","content":{"data":[{"name":"nodes","values":[{"value":"3004b2bd-3dd9-5524-a09c-2da166ffad6a","x":112,"y":221.7676239013672,"r":45,"g":196,"b":105},{"value":"1261f65e-d043-5424-972a-a1a4bc7101a2","x":204,"y":221.7676239013672,"r":45,"g":196,"b":105},{"value":"2499b250-8d7e-56b3-9d38-7a27a69b1450","x":296,"y":221.7676239013672,"r":45,"g":196,"b":105},{"value":"251e51c9-5d14-54af-8abe-a8a83f56c95c","x":388,"y":221.7676239013672,"r":45,"g":196,"b":105},{"value":"2f887e43-5bdb-5579-8cf4-e5670850decb","x":480,"y":221.7676239013672,"r":45,"g":196,"b":105},{"value":"01750728-d771-5bb0-8cef-659acad25a95","x":250,"y":87.25587463378906,"r":240,"g":100,"b":150},{"value":"0cc8f093-9ada-5bb5-97a8-d5b4b595f10c","x":480,"y":87.25587463378906,"r":240,"g":100,"b":150}]},{"name":"links","values":[{"path":"M 112.0 221.76762 L 204.0 221.76762"},{"path":"M 204.0 221.76762 L 296.0 221.76762"},{"path":"M 296.0 221.76762 L 388.0 221.76762"},{"path":"M 388.0 221.76762 L 480.0 221.76762"},{"path":"M 250.0 87.255875 L 480.0 87.255875"}]}],"marks":[{"properties":{"enter":{"path":{"field":"data.path"},"stroke":{"value":"grey"},"strokeWidth":{"value":1}}},"type":"path","from":{"data":"links"}},{"properties":{"update":{"stroke":["value","transparent"],"size":{"value":90},"shape":"circle"},"enter":{"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"},"x":{"field":"data.x"}}},"type":"symbol","from":{"data":"nodes"}}],"width":500,"height":309.02349853515625,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:data [{:name \"nodes\", :values [{:value #uuid \"3004b2bd-3dd9-5524-a09c-2da166ffad6a\", :x 112, :y 221.7676239013672, :r 45, :g 196, :b 105} {:value #uuid \"1261f65e-d043-5424-972a-a1a4bc7101a2\", :x 204, :y 221.7676239013672, :r 45, :g 196, :b 105} {:value #uuid \"2499b250-8d7e-56b3-9d38-7a27a69b1450\", :x 296, :y 221.7676239013672, :r 45, :g 196, :b 105} {:value #uuid \"251e51c9-5d14-54af-8abe-a8a83f56c95c\", :x 388, :y 221.7676239013672, :r 45, :g 196, :b 105} {:value #uuid \"2f887e43-5bdb-5579-8cf4-e5670850decb\", :x 480, :y 221.7676239013672, :r 45, :g 196, :b 105} {:value #uuid \"01750728-d771-5bb0-8cef-659acad25a95\", :x 250, :y 87.25587463378906, :r 240, :g 100, :b 150} {:value #uuid \"0cc8f093-9ada-5bb5-97a8-d5b4b595f10c\", :x 480, :y 87.25587463378906, :r 240, :g 100, :b 150}]} {:name \"links\", :values [{:path \"M 112.0 221.76762 L 204.0 221.76762\"} {:path \"M 204.0 221.76762 L 296.0 221.76762\"} {:path \"M 296.0 221.76762 L 388.0 221.76762\"} {:path \"M 388.0 221.76762 L 480.0 221.76762\"} {:path \"M 250.0 87.255875 L 480.0 87.255875\"}]}], :marks [{:properties {:enter {:path {:field \"data.path\"}, :stroke {:value \"grey\"}, :strokeWidth {:value 1}}}, :type \"path\", :from {:data \"links\"}} {:properties {:update {:stroke [:value \"transparent\"], :size {:value 90}, :shape \"circle\"}, :enter {:y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}, :x {:field \"data.x\"}}}, :type \"symbol\", :from {:data \"nodes\"}}], :width 500, :height 309.0235, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; @@

;; @@
