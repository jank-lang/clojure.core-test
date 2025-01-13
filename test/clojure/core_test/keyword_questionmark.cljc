(ns clojure.core-test.keyword-questionmark
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/keyword?
 (deftest test-keyword?
   (are [expected x] (= expected (keyword? x))
     true  :a-keyword
     false 'a-symbol
     true  :a-ns/a-keyword
     false 'a-ns/a-keyword
     false "a string"
     false 0
     false 0N
     false 0.0
     false 1/2
     false 0.0M
     false false
     false true
     false nil)))
