(ns clojure.core-test.bit-shift-left
  (:require [clojure.test :as t]))

(t/deftest common
  #?(:clj (t/is (thrown? NullPointerException (bit-shift-left nil 1)))
     :cljs (t/is (bit-shift-left nil 1)))
  #?(:clj (t/is (thrown? NullPointerException (bit-shift-left 1 nil)))
     :cljs (t/is (bit-shift-left 1 nil)))

  (t/are [ex a b] (= ex (bit-shift-left a b))
         1024 1 10
         2r110100 2r1101 2))
