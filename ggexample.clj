;; gorilla-repl.fileformat = 1

;; **
;;; # Geschichte-Gorilla Simple Example
;; **

;; @@
(ns mirthful-storm
  (:require [gorilla-repl.vega :as v]
            [geschichte-gorilla.core :as g]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(def test-repo
    {:causal-order {10 []
                    20 [10]
                    30 [20]
                    40 [20]
                    50 [40]
                    60 [30 50]
                    70 [60]
                    80 [30]
                    90 [80]
                    100 [70 140]
                    110 [100 160]
                    120 [90]
                    130 [30]
                    140 [130]
                    150 [50]
                    160 [150]
                    170 [110]
                    }
     :branches {"master" #{170}
                "fix" #{160}
                "dev" #{120}
                "fix-2" #{140}}})
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;mirthful-storm/test-repo</span>","value":"#'mirthful-storm/test-repo"}
;; <=

;; @@
(v/vega-view (g/commit-graph test-repo))
;; @@
;; =>
;;; {"type":"vega","content":{"data":[{"name":"nodes","values":[{"value":10,"x":83.75,"y":226.76761627197266,"r":224,"g":104,"b":104},{"value":20,"x":147.5,"y":226.76761627197266,"r":224,"g":104,"b":104},{"value":30,"x":211.25,"y":226.76761627197266,"r":224,"g":104,"b":104},{"value":60,"x":275,"y":226.76761627197266,"r":224,"g":104,"b":104},{"value":70,"x":338.75,"y":226.76761627197266,"r":224,"g":104,"b":104},{"value":100,"x":402.5,"y":226.76761627197266,"r":224,"g":104,"b":104},{"value":110,"x":466.25,"y":226.76761627197266,"r":224,"g":104,"b":104},{"value":170,"x":530,"y":226.76761627197266,"r":224,"g":104,"b":104},{"value":40,"x":211.25,"y":144.0605697631836,"r":166,"g":118,"b":228},{"value":50,"x":275,"y":144.0605697631836,"r":166,"g":118,"b":228},{"value":150,"x":338.75,"y":144.0605697631836,"r":166,"g":118,"b":228},{"value":160,"x":402.5,"y":144.0605697631836,"r":166,"g":118,"b":228},{"value":80,"x":317.5,"y":309.4746627807617,"r":102,"g":87,"b":129},{"value":90,"x":423.75,"y":309.4746627807617,"r":102,"g":87,"b":129},{"value":120,"x":530,"y":309.4746627807617,"r":102,"g":87,"b":129},{"value":130,"x":275,"y":61.35352325439453,"r":91,"g":49,"b":203},{"value":140,"x":338.75,"y":61.35352325439453,"r":91,"g":49,"b":203}]},{"name":"links","values":[{"path":"M 83.75 226.76761 L 147.5 226.76761"},{"path":"M 147.5 226.76761 L 211.25 226.76761"},{"path":"M 211.25 226.76761 L 275.0 226.76761"},{"path":"M 275.0 226.76761 L 338.75 226.76761"},{"path":"M 338.75 226.76761 L 402.5 226.76761"},{"path":"M 402.5 226.76761 L 466.25 226.76761"},{"path":"M 466.25 226.76761 L 530.0 226.76761"},{"path":"M 211.25 144.06058 L 275.0 144.06058"},{"path":"M 275.0 144.06058 L 338.75 144.06058"},{"path":"M 338.75 144.06058 L 402.5 144.06058"},{"path":"M 317.5 309.47467 L 423.75 309.47467"},{"path":"M 423.75 309.47467 L 530.0 309.47467"},{"path":"M 275.0 61.353523 L 338.75 61.353523"},{"path":"M 211.25 226.76761 L 275.0 61.353523"},{"path":"M 211.25 226.76761 L 317.5 309.47467"},{"path":"M 147.5 226.76761 L 211.25 144.06058"},{"path":"M 338.75 61.353523 L 402.5 226.76761"},{"path":"M 402.5 144.06058 L 466.25 226.76761"},{"path":"M 275.0 144.06058 L 275.0 226.76761"}]},{"name":"labels","values":[{"value":"fix","x":402.5,"y":144.0605697631836},{"value":"dev","x":530,"y":309.4746627807617},{"value":"fix-2","x":338.75,"y":61.35352325439453},{"value":"master","x":530,"y":226.76761627197266}]}],"marks":[{"properties":{"enter":{"path":{"field":"data.path"},"stroke":{"value":"grey"},"strokeWidth":{"value":1}}},"type":"path","from":{"data":"links"}},{"properties":{"update":{"stroke":["value","transparent"],"size":{"value":90},"shape":"circle"},"enter":{"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"},"x":{"field":"data.x"}}},"type":"symbol","from":{"data":"nodes"}},{"properties":{"update":{"text":{"field":"data.value"}},"enter":{"y":{"field":"data.y"},"align":{"value":"left"},"dx":{"value":10},"fill":{"value":"black"},"x":{"field":"data.x"},"fontSize":{"value":15}}},"type":"text","from":{"data":"labels"}}],"width":600,"height":370.82818603515625,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:data [{:name \"nodes\", :values [{:value 10, :x 335/4, :y 226.76761627197266, :r 224, :g 104, :b 104} {:value 20, :x 295/2, :y 226.76761627197266, :r 224, :g 104, :b 104} {:value 30, :x 845/4, :y 226.76761627197266, :r 224, :g 104, :b 104} {:value 60, :x 275N, :y 226.76761627197266, :r 224, :g 104, :b 104} {:value 70, :x 1355/4, :y 226.76761627197266, :r 224, :g 104, :b 104} {:value 100, :x 805/2, :y 226.76761627197266, :r 224, :g 104, :b 104} {:value 110, :x 1865/4, :y 226.76761627197266, :r 224, :g 104, :b 104} {:value 170, :x 530N, :y 226.76761627197266, :r 224, :g 104, :b 104} {:value 40, :x 845/4, :y 144.0605697631836, :r 166, :g 118, :b 228} {:value 50, :x 275N, :y 144.0605697631836, :r 166, :g 118, :b 228} {:value 150, :x 1355/4, :y 144.0605697631836, :r 166, :g 118, :b 228} {:value 160, :x 805/2, :y 144.0605697631836, :r 166, :g 118, :b 228} {:value 80, :x 635/2, :y 309.4746627807617, :r 102, :g 87, :b 129} {:value 90, :x 1695/4, :y 309.4746627807617, :r 102, :g 87, :b 129} {:value 120, :x 530N, :y 309.4746627807617, :r 102, :g 87, :b 129} {:value 130, :x 275N, :y 61.35352325439453, :r 91, :g 49, :b 203} {:value 140, :x 1355/4, :y 61.35352325439453, :r 91, :g 49, :b 203}]} {:name \"links\", :values [{:path \"M 83.75 226.76761 L 147.5 226.76761\"} {:path \"M 147.5 226.76761 L 211.25 226.76761\"} {:path \"M 211.25 226.76761 L 275.0 226.76761\"} {:path \"M 275.0 226.76761 L 338.75 226.76761\"} {:path \"M 338.75 226.76761 L 402.5 226.76761\"} {:path \"M 402.5 226.76761 L 466.25 226.76761\"} {:path \"M 466.25 226.76761 L 530.0 226.76761\"} {:path \"M 211.25 144.06058 L 275.0 144.06058\"} {:path \"M 275.0 144.06058 L 338.75 144.06058\"} {:path \"M 338.75 144.06058 L 402.5 144.06058\"} {:path \"M 317.5 309.47467 L 423.75 309.47467\"} {:path \"M 423.75 309.47467 L 530.0 309.47467\"} {:path \"M 275.0 61.353523 L 338.75 61.353523\"} {:path \"M 211.25 226.76761 L 275.0 61.353523\"} {:path \"M 211.25 226.76761 L 317.5 309.47467\"} {:path \"M 147.5 226.76761 L 211.25 144.06058\"} {:path \"M 338.75 61.353523 L 402.5 226.76761\"} {:path \"M 402.5 144.06058 L 466.25 226.76761\"} {:path \"M 275.0 144.06058 L 275.0 226.76761\"}]} {:name \"labels\", :values [{:value \"fix\", :x 805/2, :y 144.0605697631836} {:value \"dev\", :x 530N, :y 309.4746627807617} {:value \"fix-2\", :x 1355/4, :y 61.35352325439453} {:value \"master\", :x 530N, :y 226.76761627197266}]}], :marks [{:properties {:enter {:path {:field \"data.path\"}, :stroke {:value \"grey\"}, :strokeWidth {:value 1}}}, :type \"path\", :from {:data \"links\"}} {:properties {:update {:stroke [:value \"transparent\"], :size {:value 90}, :shape \"circle\"}, :enter {:y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}, :x {:field \"data.x\"}}}, :type \"symbol\", :from {:data \"nodes\"}} {:properties {:update {:text {:field \"data.value\"}}, :enter {:y {:field \"data.y\"}, :align {:value \"left\"}, :dx {:value 10}, :fill {:value \"black\"}, :x {:field \"data.x\"}, :fontSize {:value 15}}}, :type \"text\", :from {:data \"labels\"}}], :width 600, :height 370.8282, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=
