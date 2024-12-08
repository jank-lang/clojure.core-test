(ns clojure.core-test.dec
  (:require [clojure.test :as t :refer [deftest testing is are]]))

(deftest common
  (are [in ex] (= (dec in) ex)
    1      0
    0      -1
    1N     0N
    0N     -1N
    14412  14411
    -3     -4
    7.4    6.4                          ; risky
    3/2    1/2
    1/2    -1/2
    ##Inf  ##Inf
    ##-Inf ##-Inf)

  (is (NaN? (dec ##NaN))))

(deftest underflow
  #?(:clj (is (thrown? ArithmeticException (dec Long/MIN_VALUE)))
     :cljs (is (= (dec js/Number.MIN_SAFE_INTEGER) (- js/Number.MIN_SAFE_INTEGER 2)))))

(deftest dec-nil
  ;; ClojureScript says (= -1 (dec nil)) because JavaScript casts null to 0
  #?(:clj (is (thrown? NullPointerException (dec #_:clj-kondo/ignore nil)))
     :cljs (is (= -1 (dec #_:clj-kondo/ignore nil)))))
