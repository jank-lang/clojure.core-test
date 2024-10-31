(ns clojure.core-test.bit-shift-right
  (:require [clojure.test :as t]))

(t/deftest common
  #?(:clj (t/is (thrown? NullPointerException (bit-shift-right nil 1)))
     :cljs (t/is (bit-shift-right nil 1)))
  #?(:clj (t/is (thrown? NullPointerException (bit-shift-right 1 nil)))
     :cljs (t/is (bit-shift-right 1 nil)))

  (t/are [ex a b] (= ex (bit-shift-right a b))
         2r1101 2r1101 0
         2r110 2r1101 1
         2r11 2r1101 2
         2r1 2r1101 3
         2r0 2r1101 4
         2r0 2r1101 63))
