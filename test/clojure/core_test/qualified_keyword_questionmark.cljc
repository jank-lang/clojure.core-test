(ns clojure.core-test.qualified-keyword-questionmark
  (:require [clojure.test :as t :refer [deftest testing is are]]))

(deftest test-qualified-keyword?
  (are [expected x] (= expected (qualified-keyword? x))
    false :a-keyword
    false 'a-symbol
    true  :a-ns/a-keyword
    false 'a-ns/a-keyword
    false "a string"
    false 0
    false 0N
    false 0.0
    #?@(:cljs []
        :default [false 1/2])
    false 0.0M
    false false
    false true
    false nil))
