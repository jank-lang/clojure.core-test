(ns clojure.core-test.ident-qmark
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/ident?
 (deftest test-ident?
   (are [expected x] (= expected (ident? x))
     true  :a-keyword
     true  'a-symbol
     true  :a-ns/a-keyword
     true  'a-ns/a-keyword
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
