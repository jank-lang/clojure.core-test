(ns clojure.core-test.even-questionmark
  (:require [clojure.test :as t]))

(t/deftest common
  (t/are [in ex] (= (even? in) ex)
    0 true
    -0 true
    12 true
    17 false
    -118 true
    -119 false
    123N false
    122N true
    -121N false
    -120N true))

(t/deftest invalid
  (t/are [x] (thrown? #?(:clj IllegalArgumentException :cljs js/Error) (even? x))
    #_:clj-kondo/ignore nil
    ##Inf
    ##-Inf
    ##NaN
    1.5
    #?@(:cljs []
        :default [1/2])
    0.2M))
