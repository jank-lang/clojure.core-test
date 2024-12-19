(ns clojure.core-test.short
  (:require [clojure.test :as t :refer [deftest testing is are]]))

(deftest test-short
  ;; There is no platform independent predicate to test for a
  ;; short (e.g., `short?`). In ClojureJVM, it's an instance of
  ;; `java.lang.Short`, but there is no predicate for it. Here, we just
  ;; test whether it's a fixed-length integer of some sort.
  (is (int? (short 0)))
  #?@(:cljs nil
      :default
      [(is (instance? java.lang.Short (short 0)))])

  ;; Check conversions and rounding from other numeric types
  (are [expected x] (= expected (short x))
    -32768 -32768
    0    0
    32767 32767
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

  ;; `short` throws outside the range of 32767 ... -32768.
  (is (thrown? IllegalArgumentException (short -32768.000001)))
  (is (thrown? IllegalArgumentException (short -32769)))
  (is (thrown? IllegalArgumentException (short 32768)))
  (is (thrown? IllegalArgumentException (short 32767.000001)))

  ;; Check handling of other types
  (is (thrown? ClassCastException (short "0")))
  (is (thrown? ClassCastException (short :0)))
  (is (thrown? ClassCastException (short [0])))
  (is (thrown? Exception (short nil))))

