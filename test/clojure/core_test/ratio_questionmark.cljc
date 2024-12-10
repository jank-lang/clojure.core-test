(ns clojure.core-test.ratio-questionmark
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.number-range :as r]))

(deftest test-ratio?
  (are [expected x] (= expected (ratio? x))
    false 0
    false 1
    false -1
    false r/max-int
    false r/min-int
    false 0.0
    false 1.0
    false -1.0
    false r/max-double
    false r/min-double
    false ##Inf
    false ##-Inf
    false ##NaN
    false 0N
    false 1N
    false -1N
    false 0/2                            ; perhaps surprising
    true  1/2
    true  -1/2
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
