(ns clojure.core-test.bit-set
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/bit-set
  (deftest test-bit-set
    #?(:clj (is (thrown? NullPointerException (bit-set nil 1)))
       :cljs (is (bit-set nil 1)))
    #?(:clj (is (thrown? NullPointerException (bit-set 1 nil)))
       :cljs (is (bit-set 1 nil)))

    (are [ex a b] (= ex (bit-set a b))
      2r1111               2r1011 2
      -9223372036854775808 0      63
      4294967296           0      32
      65536                0      16
      256                  0      8
      16                   0      4)))
