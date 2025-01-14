(ns clojure.core-test.qualified-ident-questionmark
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/qualified-ident?
 (deftest test-qualified-ident?
   (are [expected x] (= expected (qualified-ident? x))
     false :a-keyword
     false 'a-symbol
     true  :a-ns/a-keyword
     true  'a-ns/a-keyword
     false "a string"
     false 0
     false 0N
     false 0.0
     false 1/2
     false 0.0M
     false false
     false true
     false nil)))
