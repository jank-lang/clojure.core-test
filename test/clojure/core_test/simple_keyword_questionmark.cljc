(ns clojure.core-test.simple-keyword-questionmark
  (:require [clojure.test :as t :refer [deftest testing is are]]))

(deftest test-simple-keyword?
  (are [expected x] (= expected (simple-keyword? x))
    true  :a-keyword
    false 'a-symbol
    false :a-ns/a-keyword
    false 'a-ns/a-keyword
    false "a string"
    false 0
    false 0N
    false 0.0
    false 1/2
    false 0.0M
    false false
    false true
    false nil))
