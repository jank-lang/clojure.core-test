(ns clojure.core.bit-xor-test
  (:require [clojure.test :as t]))

(t/deftest common
  (t/is (thrown? NullPointerException (bit-xor nil 1)))
  (t/is (thrown? NullPointerException (bit-xor 1 nil)))

  (t/are [ex a b] (= ex (bit-xor a b))
         2r0101 2r1100 2r1001))
