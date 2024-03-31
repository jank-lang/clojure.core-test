(ns jank.core.tests-test
  (:require [clojure.test :refer :all]))

(deftest test-abs
  (are [in ex] (= ex (abs in))
    -1 1
    1 1
    Long/MIN_VALUE Long/MIN_VALUE ;; special case!
    -1.0 1.0
    -0.0 0.0
    ##-Inf ##Inf
    ##Inf ##Inf
    -123.456M 123.456M
    -123N 123N
    -1/5 1/5)
  (is (NaN? (abs ##NaN))))