(ns clojure.core-test.odd-questionmark
  (:require [clojure.test :as t]))

(t/deftest common
  (t/are [in ex] (= (odd? in) ex)
    0 false
    -0 false
    12 false
    17 true
    -118 false
    -119 true
    123N true
    122N false
    -121N true
    -120N false))

(t/deftest invalid
  (t/are [x] (thrown? #?(:clj IllegalArgumentException :cljs js/Error) (odd? x))
    ##Inf
    ##-Inf
    ##NaN
    1.5
    1/2
    0.2M))
