(ns clojure.core-test.simple-symbol-qmark
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/simple-symbol?
 (deftest test-simple-symbol?
   (are [expected x] (= expected (simple-symbol? x))
     false :a-keyword
     true  'a-symbol
     false :a-ns/a-keyword
     false 'a-ns/a-keyword
     false "a string"
     false 0
     false 0N
     false 0.0
     #?@(:cljs []
         :default
         [false 1/2])
     false 0.0M
     false false
     false true
     false nil)))
