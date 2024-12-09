(ns clojure.core-test.neg-questionmark
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.number-range :as r]))

(deftest test-neg?
  (are [expected x] (= expected (neg? x))
    false 0
    false 1
    true  -1
    true  r/min-int
    false r/max-int
    false 0.0
    false 1.0
    true  -1.0
    false r/min-double
    false r/max-double
    false ##Inf
    true  ##-Inf
    false ##NaN
    false 0N
    false 1N
    true  -1N
    false 0/2
    false 1/2
    true  -1/2
    false 0.0M
    false 1.0M
    true  -1.0M)

  (is (thrown? Exception (neg? nil)))
  (is (thrown? Exception (neg? false)))
  (is (thrown? Exception (neg? true))))
