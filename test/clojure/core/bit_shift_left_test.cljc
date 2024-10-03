(ns clojure.core.bit-shift-left-test
  (:require [clojure.test :as t]))

(t/deftest common
  (t/is (thrown? NullPointerException (bit-shift-left nil 1)))
  (t/is (thrown? NullPointerException (bit-shift-left 1 nil)))

  (t/are [ex a b] (= ex (bit-shift-left a b))
         1024 1 10
         2r110100 2r1101 2))
