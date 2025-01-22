(ns clojure.core-test.neg-int-qmark
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.number-range :as r]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/neg-int?
 (deftest test-neg-int?
   (are [expected x] (= expected (neg-int? x))
     false 0
     false 1
     true  -1
     false r/max-int
     true  r/min-int
     false 0.0
     false 1.0
     #?@(:cljs [true -1.0]
         :default [false -1.0])
     false r/max-double
     false r/min-double
     false ##Inf
     false ##-Inf
     false ##NaN
     false 0N
     false 1N
     #?@(:cljs [true -1N]
         :default [false -1N])
     #?@(:cljs []
         :default
         [false 0/2
          false 1/2
          false -1/2])
     false 0.0M
     false 1.0M
     #?@(:cljs [true -1.0M]
         :default [false -1.0M])
     false nil
     false true
     false false
     false "a string"
     false "0"
     false "1"
     false "-1"
     false {:a :map}
     false #{:a-set}
     false [:a :vector]
     false '(:a :list)
     false \0
     false \1
     false :a-keyword
     false :0
     false :1
     false :-1
     false 'a-sym)))
