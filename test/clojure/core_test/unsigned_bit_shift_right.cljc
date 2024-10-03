(ns clojure.core-test.unsigned-bit-shift-right
  (:require [clojure.test :as t]))

(t/deftest common
  (t/is (thrown? NullPointerException (unsigned-bit-shift-right nil 1)))
  (t/is (thrown? NullPointerException (unsigned-bit-shift-right 1 nil)))

  (t/are [ex a b] (= ex (unsigned-bit-shift-right a b))
         18014398509481983 -1 10))
