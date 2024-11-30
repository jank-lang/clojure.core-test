(ns clojure.core-test.nil-questionmark
  (:require [clojure.test :as t]))

(t/deftest common
  (t/are [in ex] (= (nil? in) ex)
    nil true
    0 false
    false false
    "" false
    "nil" false
    :nil false
    ##Inf false
    ##NaN false
    0.0 false
    {} false
    '() false
    [] false))

(t/deftest infinite-sequence
  (t/is (= false (nil? (range)))))
