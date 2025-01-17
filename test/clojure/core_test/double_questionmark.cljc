(ns clojure.core-test.double-questionmark
  (:require #?(:cljs  [cljs.reader])
            [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.number-range :as r]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/double?
 (deftest test-double?
   (are [expected x] (= expected (double? x))
     false 0
     false 1
     false -1
     false r/max-int
     false r/min-int
     true  0.0
     true  1.0
     true  -1.0
     false (float 0.0) ; surprising since (float? (double 0.0)) = true
     false (float 1.0) ; surprising since (float? (double 1.0)) = true
     false (float -1.0) ; surprising since (float? (double -1.0)) = true
     true  (double 0.0)
     true  (double 1.0)
     true  (double -1.0)
     true  r/max-double
     true  r/min-double
     true  ##Inf
     true  ##-Inf
     true  ##NaN
     false 0N
     false 1N
     false -1N
     #?@(:cljs []
         :default
         [false 0/2
          false 1/2
          false -1/2])
     false 0.0M
     false 1.0M
     false -1.0M
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
