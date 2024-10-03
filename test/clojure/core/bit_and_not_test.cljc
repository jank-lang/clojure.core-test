(ns clojure.core.bit-and-not-test
  (:require [clojure.test :as t]))

(t/deftest common
  (t/is (thrown? NullPointerException (bit-and-not nil 1)))
  (t/is (thrown? NullPointerException (bit-and-not 1 nil)))

  (t/are [ex a b] (= ex (bit-and-not a b))
         0 0 0
         8 12 4
         0xff 0xff 0
         0x80 0xff 0x7f))
