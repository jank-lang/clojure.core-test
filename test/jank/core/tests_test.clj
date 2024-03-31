(ns jank.core.tests-test
  (:require [clojure.test :refer :all]))

(deftest test-abs
  (are [in ex] (= ex (abs in))
    -1 1
    1 1
    Long/MIN_VALUE Long/MIN_VALUE ;; special case!
    -1.0 1.0
    -0.0 0.0
    ##-Inf ##Inf
    ##Inf ##Inf
    -123.456M 123.456M
    -123N 123N
    -1/5 1/5)
  (is (NaN? (abs ##NaN))))

(deftest test-and
  ; (and) should return true
  (is true (and))
  ; if left hand side is truthy (not nil or false) return right hand side
  (are [ex a b] (= ex (and a b))
    true true true
    false true false
    nil true nil))

(deftest test-any?
  ; return true for any argument
  (are [x] (= (any? x) true)
    nil
    true
    false
    ""
    0
    1))