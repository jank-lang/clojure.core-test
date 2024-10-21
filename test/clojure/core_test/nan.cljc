(ns clojure.core-test.nan
  (:require [clojure.test :as t]))

(t/deftest common
  (t/is (thrown? Exception (NaN? nil)))
  (t/is (thrown? Exception (NaN? "##NaN")))
  (t/are [in ex] (= ex (NaN? in))
         0 false
         1.0 false
         -1.0 false
         (double 1.0) false
         (double -1.0) false
         ##Inf false
         ##-Inf false
         ##NaN true))
