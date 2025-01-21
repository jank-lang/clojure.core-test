(ns clojure.core-test.false-qmark
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.number-range :as r]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/false?
 (deftest test-false?
   (are [expected x] (= expected (false? x))
     false 0
     false 1
     false -1
     false r/max-int
     false r/min-int
     false 0.0
     false 1.0
     false -1.0
     false (float 0.0)
     false (float 1.0)
     false (float -1.0)
     false (double 0.0)
     false (double 1.0)
     false (double -1.0)
     false r/max-double
     false r/min-double
     false ##Inf
     false ##-Inf
     false ##NaN
     false 0N
     false 1N
     false -1N
     #?@(:cljs []
         :default [false 0/2
                   false 1/2
                   false -1/2])
     false 0.0M
     false 1.0M
     false -1.0M
     false nil
     false true
     true  false
     false "a string"
     false "0"
     false "1"
     false "-1"
     false "true"
     false "false"
     false {:a :map}
     false #{:a-set}
     false [:a :vector]
     false '(:a :list)
     false \0
     false \1
     false :a-keyword
     false :true
     false :false
     false :0
     false :1
     false :-1
     false 'a-sym)))
