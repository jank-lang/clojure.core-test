(ns clojure.core-test.bit-set
  (:require [clojure.test :as t]))

(t/deftest common
  (t/is (thrown? NullPointerException (bit-set nil 1)))
  (t/is (thrown? NullPointerException (bit-set 1 nil)))

  (t/are [ex a b] (= ex (bit-set a b))
         2r1111 2r1011 2
         -9223372036854775808 0 63
         4294967296 0 32
         65536 0 16
         256 0 8
         16 0 4))
