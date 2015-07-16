;; gorilla-repl.fileformat = 1

;; **
;;; # Replikativ example
;; **

;; @@
(ns sunset-beach
  (:require [gorilla-repl.vega :as v]
            [geschichte-gorilla.core :as g]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(def test-repo
  (atom
    {:commit-graph {10 []}
     :commits
     {10 "master"}
     :branches {"master" #{10}}}))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;sunset-beach/test-repo</span>","value":"#'sunset-beach/test-repo"}
;; <=

;; @@
(defn plot-graph [repo]
  (v/vega-view (g/vega-commit-graph (:commit-graph repo) (:branches repo) (:commits repo)))
  )
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;sunset-beach/plot-graph</span>","value":"#'sunset-beach/plot-graph"}
;; <=

;; **
;;; Bare repo
;; **

;; @@
(plot-graph @test-repo)
;; @@
;; =>
;;; {"type":"vega","content":{"width":600,"height":370.82818603515625,"padding":{"top":10,"left":50,"bottom":20,"right":10},"marks":[{"type":"path","from":{"data":"links"},"properties":{"enter":{"path":{"field":"data.path"},"strokeWidth":{"value":2},"stroke":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}}}}},{"type":"symbol","from":{"data":"nodes"},"properties":{"enter":{"x":{"field":"data.x"},"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"}},"update":{"shape":"circle","size":{"value":110},"stroke":["value","transparent"]}}},{"type":"text","from":{"data":"labels"},"properties":{"enter":{"x":{"field":"data.x"},"y":{"field":"data.y"},"align":{"value":"left"},"dx":{"value":10},"fontSize":{"value":15},"fill":{"value":"black"}},"update":{"text":{"field":"data.value"}}}}],"data":[{"name":"nodes","values":[{"value":10,"x":570.0,"y":185.41409301757812,"r":210,"g":104,"b":83}]},{"name":"links","values":[]},{"name":"labels","values":[{"value":"master","x":570.0,"y":185.41409301757812}]}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 600, :height 370.8282, :padding {:top 10, :left 50, :bottom 20, :right 10}, :marks [{:type \"path\", :from {:data \"links\"}, :properties {:enter {:path {:field \"data.path\"}, :strokeWidth {:value 2}, :stroke {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}}}} {:type \"symbol\", :from {:data \"nodes\"}, :properties {:enter {:x {:field \"data.x\"}, :y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}}, :update {:shape \"circle\", :size {:value 110}, :stroke [:value \"transparent\"]}}} {:type \"text\", :from {:data \"labels\"}, :properties {:enter {:x {:field \"data.x\"}, :y {:field \"data.y\"}, :align {:value \"left\"}, :dx {:value 10}, :fontSize {:value 15}, :fill {:value \"black\"}}, :update {:text {:field \"data.value\"}}}}], :data [{:name \"nodes\", :values [{:value 10, :x 570.0, :y 185.41409301757812, :r 210, :g 104, :b 83}]} {:name \"links\", :values []} {:name \"labels\", :values [{:value \"master\", :x 570.0, :y 185.41409301757812}]}]}}"}
;; <=

;; **
;;; Commit some things to "master" branch
;; **

;; @@
(swap! test-repo #(assoc-in % [:commit-graph 20] [10]))
(swap! test-repo #(assoc-in % [:commits 20] "master"))
(swap! test-repo #(assoc-in % [:branches "master"] #{20}))
(plot-graph @test-repo)

(swap! test-repo #(assoc-in % [:commit-graph 30] [20]))
(swap! test-repo #(assoc-in % [:commits 30] "master"))
(swap! test-repo #(assoc-in % [:branches "master"] #{30}))
(plot-graph @test-repo)

(swap! test-repo #(assoc-in % [:commit-graph 40] [30]))
(swap! test-repo #(assoc-in % [:commits 40] "master"))
(swap! test-repo #(assoc-in % [:branches "master"] #{40}))
(plot-graph @test-repo)
;; @@
;; =>
;;; {"type":"vega","content":{"width":600,"height":370.82818603515625,"padding":{"top":10,"left":50,"bottom":20,"right":10},"marks":[{"type":"path","from":{"data":"links"},"properties":{"enter":{"path":{"field":"data.path"},"strokeWidth":{"value":2},"stroke":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}}}}},{"type":"symbol","from":{"data":"nodes"},"properties":{"enter":{"x":{"field":"data.x"},"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"}},"update":{"shape":"circle","size":{"value":110},"stroke":["value","transparent"]}}},{"type":"text","from":{"data":"labels"},"properties":{"enter":{"x":{"field":"data.x"},"y":{"field":"data.y"},"align":{"value":"left"},"dx":{"value":10},"fontSize":{"value":15},"fill":{"value":"black"}},"update":{"text":{"field":"data.value"}}}}],"data":[{"name":"nodes","values":[{"value":10,"x":30.0,"y":185.41409301757812,"r":183,"g":200,"b":8},{"value":20,"x":209.99999999999977,"y":185.41409301757812,"r":183,"g":200,"b":8},{"value":30,"x":389.9999999999998,"y":185.41409301757812,"r":183,"g":200,"b":8},{"value":40,"x":570.0,"y":185.41409301757812,"r":183,"g":200,"b":8}]},{"name":"links","values":[{"path":"M 30.000000447034836 185.41409301757812 L 209.9999964237213 185.41409301757812","r":183,"g":200,"b":8},{"path":"M 209.9999964237213 185.41409301757812 L 389.99998569488525 185.41409301757812","r":183,"g":200,"b":8},{"path":"M 389.99998569488525 185.41409301757812 L 569.9999928474426 185.41409301757812","r":183,"g":200,"b":8}]},{"name":"labels","values":[{"value":"master","x":570.0,"y":185.41409301757812}]}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 600, :height 370.8282, :padding {:top 10, :left 50, :bottom 20, :right 10}, :marks [{:type \"path\", :from {:data \"links\"}, :properties {:enter {:path {:field \"data.path\"}, :strokeWidth {:value 2}, :stroke {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}}}} {:type \"symbol\", :from {:data \"nodes\"}, :properties {:enter {:x {:field \"data.x\"}, :y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}}, :update {:shape \"circle\", :size {:value 110}, :stroke [:value \"transparent\"]}}} {:type \"text\", :from {:data \"labels\"}, :properties {:enter {:x {:field \"data.x\"}, :y {:field \"data.y\"}, :align {:value \"left\"}, :dx {:value 10}, :fontSize {:value 15}, :fill {:value \"black\"}}, :update {:text {:field \"data.value\"}}}}], :data [{:name \"nodes\", :values [{:value 10, :x 30.0, :y 185.41409301757812, :r 183, :g 200, :b 8} {:value 20, :x 209.99999999999977, :y 185.41409301757812, :r 183, :g 200, :b 8} {:value 30, :x 389.9999999999998, :y 185.41409301757812, :r 183, :g 200, :b 8} {:value 40, :x 570.0, :y 185.41409301757812, :r 183, :g 200, :b 8}]} {:name \"links\", :values [{:path \"M 30.000000447034836 185.41409301757812 L 209.9999964237213 185.41409301757812\", :r 183, :g 200, :b 8} {:path \"M 209.9999964237213 185.41409301757812 L 389.99998569488525 185.41409301757812\", :r 183, :g 200, :b 8} {:path \"M 389.99998569488525 185.41409301757812 L 569.9999928474426 185.41409301757812\", :r 183, :g 200, :b 8}]} {:name \"labels\", :values [{:value \"master\", :x 570.0, :y 185.41409301757812}]}]}}"}
;; <=

;; **
;;; Create a new branch and commit to it:
;; **

;; @@
(swap! test-repo #(assoc-in % [:commit-graph 50] [30]))
(swap! test-repo #(assoc-in % [:commits 50] "dev"))
(swap! test-repo #(assoc-in % [:branches "dev"] #{50}))
(plot-graph @test-repo)

(swap! test-repo #(assoc-in % [:commit-graph 60] [50]))
(swap! test-repo #(assoc-in % [:commits 60] "dev"))
(swap! test-repo #(assoc-in % [:branches "dev"] #{60}))
(plot-graph @test-repo)

(swap! test-repo #(assoc-in % [:commit-graph 70] [60]))
(swap! test-repo #(assoc-in % [:commits 70] "dev"))
(swap! test-repo #(assoc-in % [:branches "dev"] #{70}))
(plot-graph @test-repo)
;; @@
;; =>
;;; {"type":"vega","content":{"width":600,"height":370.82818603515625,"padding":{"top":10,"left":50,"bottom":20,"right":10},"marks":[{"type":"path","from":{"data":"links"},"properties":{"enter":{"path":{"field":"data.path"},"strokeWidth":{"value":2},"stroke":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}}}}},{"type":"symbol","from":{"data":"nodes"},"properties":{"enter":{"x":{"field":"data.x"},"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"}},"update":{"shape":"circle","size":{"value":110},"stroke":["value","transparent"]}}},{"type":"text","from":{"data":"labels"},"properties":{"enter":{"x":{"field":"data.x"},"y":{"field":"data.y"},"align":{"value":"left"},"dx":{"value":10},"fontSize":{"value":15},"fill":{"value":"black"}},"update":{"text":{"field":"data.value"}}}}],"data":[{"name":"nodes","values":[{"value":10,"x":30.0,"y":123.60939534505208,"r":56,"g":164,"b":123},{"value":20,"x":226.3636363636361,"y":123.60939534505208,"r":56,"g":164,"b":123},{"value":30,"x":422.72727272727235,"y":123.60939534505208,"r":56,"g":164,"b":123},{"value":40,"x":570.0,"y":123.60939534505208,"r":56,"g":164,"b":123},{"value":50,"x":471.81818181818124,"y":247.21879069010419,"r":169,"g":183,"b":39},{"value":60,"x":520.9090909090904,"y":247.21879069010419,"r":169,"g":183,"b":39},{"value":70,"x":570.0,"y":247.21879069010419,"r":169,"g":183,"b":39}]},{"name":"links","values":[{"path":"M 30.000000447034836 123.60939902889913 L 226.3636350631714 123.60939902889913","r":56,"g":164,"b":123},{"path":"M 226.3636350631714 123.60939902889913 L 422.7272629737854 123.60939902889913","r":56,"g":164,"b":123},{"path":"M 422.7272629737854 123.60939902889913 L 569.9999928474426 123.60939902889913","r":56,"g":164,"b":123},{"path":"M 471.81819677352905 247.21879805779827 L 520.9090948104858 247.21879805779827","r":169,"g":183,"b":39},{"path":"M 520.9090948104858 247.21879805779827 L 569.9999928474426 247.21879805779827","r":169,"g":183,"b":39},{"path":"M 422.7272629737854 123.60939902889913 L 471.81819677352905 247.21879805779827","r":169,"g":183,"b":39}]},{"name":"labels","values":[{"value":"master","x":570.0,"y":123.60939534505208},{"value":"dev","x":570.0,"y":247.21879069010419}]}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 600, :height 370.8282, :padding {:top 10, :left 50, :bottom 20, :right 10}, :marks [{:type \"path\", :from {:data \"links\"}, :properties {:enter {:path {:field \"data.path\"}, :strokeWidth {:value 2}, :stroke {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}}}} {:type \"symbol\", :from {:data \"nodes\"}, :properties {:enter {:x {:field \"data.x\"}, :y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}}, :update {:shape \"circle\", :size {:value 110}, :stroke [:value \"transparent\"]}}} {:type \"text\", :from {:data \"labels\"}, :properties {:enter {:x {:field \"data.x\"}, :y {:field \"data.y\"}, :align {:value \"left\"}, :dx {:value 10}, :fontSize {:value 15}, :fill {:value \"black\"}}, :update {:text {:field \"data.value\"}}}}], :data [{:name \"nodes\", :values [{:value 10, :x 30.0, :y 123.60939534505208, :r 56, :g 164, :b 123} {:value 20, :x 226.3636363636361, :y 123.60939534505208, :r 56, :g 164, :b 123} {:value 30, :x 422.72727272727235, :y 123.60939534505208, :r 56, :g 164, :b 123} {:value 40, :x 570.0, :y 123.60939534505208, :r 56, :g 164, :b 123} {:value 50, :x 471.81818181818124, :y 247.21879069010419, :r 169, :g 183, :b 39} {:value 60, :x 520.9090909090904, :y 247.21879069010419, :r 169, :g 183, :b 39} {:value 70, :x 570.0, :y 247.21879069010419, :r 169, :g 183, :b 39}]} {:name \"links\", :values [{:path \"M 30.000000447034836 123.60939902889913 L 226.3636350631714 123.60939902889913\", :r 56, :g 164, :b 123} {:path \"M 226.3636350631714 123.60939902889913 L 422.7272629737854 123.60939902889913\", :r 56, :g 164, :b 123} {:path \"M 422.7272629737854 123.60939902889913 L 569.9999928474426 123.60939902889913\", :r 56, :g 164, :b 123} {:path \"M 471.81819677352905 247.21879805779827 L 520.9090948104858 247.21879805779827\", :r 169, :g 183, :b 39} {:path \"M 520.9090948104858 247.21879805779827 L 569.9999928474426 247.21879805779827\", :r 169, :g 183, :b 39} {:path \"M 422.7272629737854 123.60939902889913 L 471.81819677352905 247.21879805779827\", :r 169, :g 183, :b 39}]} {:name \"labels\", :values [{:value \"master\", :x 570.0, :y 123.60939534505208} {:value \"dev\", :x 570.0, :y 247.21879069010419}]}]}}"}
;; <=

;; **
;;; Merge "master" and "dev" branch-heads:
;; **

;; @@
(swap! test-repo #(assoc-in % [:commit-graph 80] [70 40]))
(swap! test-repo #(assoc-in % [:commits 80] "master"))
(swap! test-repo #(assoc-in % [:branches "master"] #{80}))
(plot-graph @test-repo)
;; @@
;; =>
;;; {"type":"vega","content":{"width":600,"height":370.82818603515625,"padding":{"top":10,"left":50,"bottom":20,"right":10},"marks":[{"type":"path","from":{"data":"links"},"properties":{"enter":{"path":{"field":"data.path"},"strokeWidth":{"value":2},"stroke":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}}}}},{"type":"symbol","from":{"data":"nodes"},"properties":{"enter":{"x":{"field":"data.x"},"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"}},"update":{"shape":"circle","size":{"value":110},"stroke":["value","transparent"]}}},{"type":"text","from":{"data":"labels"},"properties":{"enter":{"x":{"field":"data.x"},"y":{"field":"data.y"},"align":{"value":"left"},"dx":{"value":10},"fontSize":{"value":15},"fill":{"value":"black"}},"update":{"text":{"field":"data.value"}}}}],"data":[{"name":"nodes","values":[{"value":10,"x":30.0,"y":123.60939534505208,"r":79,"g":227,"b":215},{"value":20,"x":191.99999999999946,"y":123.60939534505208,"r":79,"g":227,"b":215},{"value":30,"x":353.9999999999991,"y":123.60939534505208,"r":79,"g":227,"b":215},{"value":40,"x":461.99999999999943,"y":123.60939534505208,"r":79,"g":227,"b":215},{"value":80,"x":570.0,"y":123.60939534505208,"r":79,"g":227,"b":215},{"value":50,"x":407.9999999999988,"y":247.21879069010419,"r":248,"g":103,"b":155},{"value":60,"x":461.99999999999886,"y":247.21879069010419,"r":248,"g":103,"b":155},{"value":70,"x":515.9999999999992,"y":247.21879069010419,"r":248,"g":103,"b":155}]},{"name":"links","values":[{"path":"M 30.000000447034836 123.60939902889913 L 191.99999570846558 123.60939902889913","r":79,"g":227,"b":215},{"path":"M 191.99999570846558 123.60939902889913 L 353.9999842643738 123.60939902889913","r":79,"g":227,"b":215},{"path":"M 353.9999842643738 123.60939902889913 L 461.9999885559082 123.60939902889913","r":79,"g":227,"b":215},{"path":"M 461.9999885559082 123.60939902889913 L 569.9999928474426 123.60939902889913","r":79,"g":227,"b":215},{"path":"M 408.0000042915344 247.21879805779827 L 461.9999885559082 247.21879805779827","r":248,"g":103,"b":155},{"path":"M 461.9999885559082 247.21879805779827 L 516.0000085830688 247.21879805779827","r":248,"g":103,"b":155},{"path":"M 353.9999842643738 123.60939902889913 L 408.0000042915344 247.21879805779827","r":248,"g":103,"b":155},{"path":"M 569.9999928474426 123.60939902889913 L 516.0000085830688 247.21879805779827","r":248,"g":103,"b":155}]},{"name":"labels","values":[{"value":"master","x":570.0,"y":123.60939534505208},{"value":"dev","x":515.9999999999992,"y":247.21879069010419}]}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 600, :height 370.8282, :padding {:top 10, :left 50, :bottom 20, :right 10}, :marks [{:type \"path\", :from {:data \"links\"}, :properties {:enter {:path {:field \"data.path\"}, :strokeWidth {:value 2}, :stroke {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}}}} {:type \"symbol\", :from {:data \"nodes\"}, :properties {:enter {:x {:field \"data.x\"}, :y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}}, :update {:shape \"circle\", :size {:value 110}, :stroke [:value \"transparent\"]}}} {:type \"text\", :from {:data \"labels\"}, :properties {:enter {:x {:field \"data.x\"}, :y {:field \"data.y\"}, :align {:value \"left\"}, :dx {:value 10}, :fontSize {:value 15}, :fill {:value \"black\"}}, :update {:text {:field \"data.value\"}}}}], :data [{:name \"nodes\", :values [{:value 10, :x 30.0, :y 123.60939534505208, :r 79, :g 227, :b 215} {:value 20, :x 191.99999999999946, :y 123.60939534505208, :r 79, :g 227, :b 215} {:value 30, :x 353.9999999999991, :y 123.60939534505208, :r 79, :g 227, :b 215} {:value 40, :x 461.99999999999943, :y 123.60939534505208, :r 79, :g 227, :b 215} {:value 80, :x 570.0, :y 123.60939534505208, :r 79, :g 227, :b 215} {:value 50, :x 407.9999999999988, :y 247.21879069010419, :r 248, :g 103, :b 155} {:value 60, :x 461.99999999999886, :y 247.21879069010419, :r 248, :g 103, :b 155} {:value 70, :x 515.9999999999992, :y 247.21879069010419, :r 248, :g 103, :b 155}]} {:name \"links\", :values [{:path \"M 30.000000447034836 123.60939902889913 L 191.99999570846558 123.60939902889913\", :r 79, :g 227, :b 215} {:path \"M 191.99999570846558 123.60939902889913 L 353.9999842643738 123.60939902889913\", :r 79, :g 227, :b 215} {:path \"M 353.9999842643738 123.60939902889913 L 461.9999885559082 123.60939902889913\", :r 79, :g 227, :b 215} {:path \"M 461.9999885559082 123.60939902889913 L 569.9999928474426 123.60939902889913\", :r 79, :g 227, :b 215} {:path \"M 408.0000042915344 247.21879805779827 L 461.9999885559082 247.21879805779827\", :r 248, :g 103, :b 155} {:path \"M 461.9999885559082 247.21879805779827 L 516.0000085830688 247.21879805779827\", :r 248, :g 103, :b 155} {:path \"M 353.9999842643738 123.60939902889913 L 408.0000042915344 247.21879805779827\", :r 248, :g 103, :b 155} {:path \"M 569.9999928474426 123.60939902889913 L 516.0000085830688 247.21879805779827\", :r 248, :g 103, :b 155}]} {:name \"labels\", :values [{:value \"master\", :x 570.0, :y 123.60939534505208} {:value \"dev\", :x 515.9999999999992, :y 247.21879069010419}]}]}}"}
;; <=

;; **
;;; Commit both branches:
;; **

;; @@
(swap! test-repo #(assoc-in % [:commit-graph 90] [70]))
(swap! test-repo #(assoc-in % [:commits 90] "dev"))
(swap! test-repo #(assoc-in % [:branches "dev"] #{90}))
(plot-graph @test-repo)

(swap! test-repo #(assoc-in % [:commit-graph 100] [80]))
(swap! test-repo #(assoc-in % [:commits 100] "master"))
(swap! test-repo #(assoc-in % [:branches "master"] #{100}))
(plot-graph @test-repo)
;; @@
;; =>
;;; {"type":"vega","content":{"width":600,"height":370.82818603515625,"padding":{"top":10,"left":50,"bottom":20,"right":10},"marks":[{"type":"path","from":{"data":"links"},"properties":{"enter":{"path":{"field":"data.path"},"strokeWidth":{"value":2},"stroke":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}}}}},{"type":"symbol","from":{"data":"nodes"},"properties":{"enter":{"x":{"field":"data.x"},"y":{"field":"data.y"},"fill":{"r":{"field":"data.r"},"g":{"field":"data.g"},"b":{"field":"data.b"}},"fillOpacity":{"value":"1"}},"update":{"shape":"circle","size":{"value":110},"stroke":["value","transparent"]}}},{"type":"text","from":{"data":"labels"},"properties":{"enter":{"x":{"field":"data.x"},"y":{"field":"data.y"},"align":{"value":"left"},"dx":{"value":10},"fontSize":{"value":15},"fill":{"value":"black"}},"update":{"text":{"field":"data.value"}}}}],"data":[{"name":"nodes","values":[{"value":10,"x":30.0,"y":123.60939534505208,"r":240,"g":176,"b":7},{"value":20,"x":175.71428571427927,"y":123.60939534505208,"r":240,"g":176,"b":7},{"value":30,"x":321.4285714285606,"y":123.60939534505208,"r":240,"g":176,"b":7},{"value":40,"x":407.1428571428473,"y":123.60939534505208,"r":240,"g":176,"b":7},{"value":80,"x":492.857142857137,"y":123.60939534505208,"r":240,"g":176,"b":7},{"value":100,"x":570.0,"y":123.60939534505208,"r":240,"g":176,"b":7},{"value":50,"x":381.4285714285585,"y":247.21879069010419,"r":255,"g":92,"b":135},{"value":60,"x":441.4285714285602,"y":247.21879069010419,"r":255,"g":92,"b":135},{"value":70,"x":501.4285714285652,"y":247.21879069010419,"r":255,"g":92,"b":135},{"value":90,"x":570.0,"y":247.21879069010419,"r":255,"g":92,"b":135}]},{"name":"links","values":[{"path":"M 30.000000447034836 123.60939902889913 L 175.71428418159485 123.60939902889913","r":240,"g":176,"b":7},{"path":"M 175.71428418159485 123.60939902889913 L 321.4285612106323 123.60939902889913","r":240,"g":176,"b":7},{"path":"M 321.4285612106323 123.60939902889913 L 407.1428418159485 123.60939902889913","r":240,"g":176,"b":7},{"path":"M 407.1428418159485 123.60939902889913 L 492.8571581840515 123.60939902889913","r":240,"g":176,"b":7},{"path":"M 492.8571581840515 123.60939902889913 L 569.9999928474426 123.60939902889913","r":240,"g":176,"b":7},{"path":"M 381.42857551574707 247.21879805779827 L 441.42855405807495 247.21879805779827","r":255,"g":92,"b":135},{"path":"M 441.42855405807495 247.21879805779827 L 501.4285683631897 247.21879805779827","r":255,"g":92,"b":135},{"path":"M 501.4285683631897 247.21879805779827 L 569.9999928474426 247.21879805779827","r":255,"g":92,"b":135},{"path":"M 321.4285612106323 123.60939902889913 L 381.42857551574707 247.21879805779827","r":255,"g":92,"b":135},{"path":"M 492.8571581840515 123.60939902889913 L 501.4285683631897 247.21879805779827","r":255,"g":92,"b":135}]},{"name":"labels","values":[{"value":"master","x":570.0,"y":123.60939534505208},{"value":"dev","x":570.0,"y":247.21879069010419}]}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 600, :height 370.8282, :padding {:top 10, :left 50, :bottom 20, :right 10}, :marks [{:type \"path\", :from {:data \"links\"}, :properties {:enter {:path {:field \"data.path\"}, :strokeWidth {:value 2}, :stroke {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}}}} {:type \"symbol\", :from {:data \"nodes\"}, :properties {:enter {:x {:field \"data.x\"}, :y {:field \"data.y\"}, :fill {:r {:field \"data.r\"}, :g {:field \"data.g\"}, :b {:field \"data.b\"}}, :fillOpacity {:value \"1\"}}, :update {:shape \"circle\", :size {:value 110}, :stroke [:value \"transparent\"]}}} {:type \"text\", :from {:data \"labels\"}, :properties {:enter {:x {:field \"data.x\"}, :y {:field \"data.y\"}, :align {:value \"left\"}, :dx {:value 10}, :fontSize {:value 15}, :fill {:value \"black\"}}, :update {:text {:field \"data.value\"}}}}], :data [{:name \"nodes\", :values [{:value 10, :x 30.0, :y 123.60939534505208, :r 240, :g 176, :b 7} {:value 20, :x 175.71428571427927, :y 123.60939534505208, :r 240, :g 176, :b 7} {:value 30, :x 321.4285714285606, :y 123.60939534505208, :r 240, :g 176, :b 7} {:value 40, :x 407.1428571428473, :y 123.60939534505208, :r 240, :g 176, :b 7} {:value 80, :x 492.857142857137, :y 123.60939534505208, :r 240, :g 176, :b 7} {:value 100, :x 570.0, :y 123.60939534505208, :r 240, :g 176, :b 7} {:value 50, :x 381.4285714285585, :y 247.21879069010419, :r 255, :g 92, :b 135} {:value 60, :x 441.4285714285602, :y 247.21879069010419, :r 255, :g 92, :b 135} {:value 70, :x 501.4285714285652, :y 247.21879069010419, :r 255, :g 92, :b 135} {:value 90, :x 570.0, :y 247.21879069010419, :r 255, :g 92, :b 135}]} {:name \"links\", :values [{:path \"M 30.000000447034836 123.60939902889913 L 175.71428418159485 123.60939902889913\", :r 240, :g 176, :b 7} {:path \"M 175.71428418159485 123.60939902889913 L 321.4285612106323 123.60939902889913\", :r 240, :g 176, :b 7} {:path \"M 321.4285612106323 123.60939902889913 L 407.1428418159485 123.60939902889913\", :r 240, :g 176, :b 7} {:path \"M 407.1428418159485 123.60939902889913 L 492.8571581840515 123.60939902889913\", :r 240, :g 176, :b 7} {:path \"M 492.8571581840515 123.60939902889913 L 569.9999928474426 123.60939902889913\", :r 240, :g 176, :b 7} {:path \"M 381.42857551574707 247.21879805779827 L 441.42855405807495 247.21879805779827\", :r 255, :g 92, :b 135} {:path \"M 441.42855405807495 247.21879805779827 L 501.4285683631897 247.21879805779827\", :r 255, :g 92, :b 135} {:path \"M 501.4285683631897 247.21879805779827 L 569.9999928474426 247.21879805779827\", :r 255, :g 92, :b 135} {:path \"M 321.4285612106323 123.60939902889913 L 381.42857551574707 247.21879805779827\", :r 255, :g 92, :b 135} {:path \"M 492.8571581840515 123.60939902889913 L 501.4285683631897 247.21879805779827\", :r 255, :g 92, :b 135}]} {:name \"labels\", :values [{:value \"master\", :x 570.0, :y 123.60939534505208} {:value \"dev\", :x 570.0, :y 247.21879069010419}]}]}}"}
;; <=

;; @@

;; @@
