(ns clojure.core-test.double
  (:require [clojure.test :as t :refer [deftest testing is are]]))

(deftest test-double
  (are [expected x] (= expected (double x))
    (double 1.0) 1
    (double 0.0) 0
    (double -1.0) -1
    (double 1.0) 1N
    (double 0.0) 0N
    (double -1.0) -1N
    (double 1.0) 12/12
    (double 0.0) 0/12
    (double -1.0) -12/12
    (double 1.0) 1.0M
    (double 0.0) 0.0M
    (double -1.0) -1.0M)
  (is (NaN? (double ##NaN)))

  #?@(:cljs []
      :default
      [(is (instance? java.lang.Double (double 0)))
       (is (instance? java.lang.Double (double 0.0)))
       (is (instance? java.lang.Double (double 0N)))
       (is (instance? java.lang.Double (double 0.0M)))])
  (is (thrown? ClassCastException (double "0")))
  (is (thrown? ClassCastException (double :0))))
