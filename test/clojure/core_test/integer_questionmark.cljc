(ns clojure.core-test.integer-questionmark
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.number-range :as r]))

(deftest test-integer?
  (are [expected x] (= expected (integer? x))
    true  0
    true  1
    true  -1
    true  r/max-int
    true  r/min-int
    false 0.0
    false 1.0
    false -1.0
    false r/max-double
    false r/min-double
    false ##Inf
    false ##-Inf
    false ##NaN
    true  0N
    true  1N
    true  -1N
    #?@(:cljs []
        :default [true 0/2      ; perhaps surprising
                  false 1/2
                  false -1/2])
    false 0.0M
    false 1.0M
    false -1.0M
    false nil
    false true
    false false
    false "a string"
    false "0"
    false "1"
    false "-1"
    false {:a :map}
    false #{:a-set}
    false [:a :vector]
    false '(:a :list)
    false \0
    false \1
    false :a-keyword
    false :0
    false :1
    false :-1
    false 'a-sym))
