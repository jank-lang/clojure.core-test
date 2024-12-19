(ns clojure.core-test.int
  (:require [clojure.test :as t :refer [deftest testing is are]]))

(deftest test-int
  ;; There is no platform independent predicate to test specifically
  ;; for an int. While `int?` exists, it returns true for any
  ;; fixed-range integer type (e.g., byte, short, int, or long). In
  ;; ClojureJVM, it's an instance of `java.lang.Int`, but there is no
  ;; predicate for it. Here, we just test whether it's a fixed-length
  ;; integer of some sort.
  (is (int? (int 0)))
  #?@(:cljs nil
      :default
      [(is (instance? java.lang.Integer (int 0)))])

  ;; Check conversions and rounding from other numeric types
  (are [expected x] (= expected (int x))
    -2147483648 -2147483648
    0    0
    2147483647 2147483647
    1    1N
    0    0N
    -1   -1N
    1    1.0M
    0    0.0M
    -1   -1.0M
    1    1.1
    -1   -1.1
    1    1.9
    1    3/2
    -1   -3/2
    0    1/10
    0    -1/10
    1    1.1M
    -1   -1.1M)

  ;; `int` throws outside the range of 32767 ... -32768.
  (is (thrown? IllegalArgumentException (int -2147483648.000001)))
  (is (thrown? ArithmeticException (int -2147483649)))
  (is (thrown? ArithmeticException (int 2147483648)))
  (is (thrown? IllegalArgumentException (int 2147483647.000001)))

  ;; Check handling of other types
  (is (thrown? ClassCastException (int "0")))
  (is (thrown? ClassCastException (int :0)))
  (is (thrown? ClassCastException (int [0])))
  (is (thrown? Exception (int nil))))

