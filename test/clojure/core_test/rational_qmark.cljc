(ns clojure.core-test.rational-qmark
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.number-range :as r]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/rational?
 (deftest test-rational?
   (are [expected x] (= expected (rational? x))
     true  0
     true  1
     true  -1
     true  r/max-int
     true  r/min-int
     false 0.0
     false 1.0
     false -1.0
     false r/max-double
     false r/min-double
     false ##Inf
     false ##-Inf
     false ##NaN
     true  0N
     true  1N
     true  -1N
     #?@(:cljs []
         :default
         [true  0/2                          ; perhaps surprising
          true  1/2
          true  -1/2])
     true  0.0M                         ; perhaps surprising
     true  1.0M                         ; perhaps surprising
     true  -1.0M                        ; perhaps surprising
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
