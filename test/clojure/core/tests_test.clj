(ns clojure.core.tests-test
  (:require [clojure.test :refer :all]))

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

