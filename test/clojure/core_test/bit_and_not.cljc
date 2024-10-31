(ns clojure.core-test.bit-and-not
  (:require [clojure.test :as t]
            [clojure.core-test.number-range :as r]))

(t/deftest common
  #?(:clj (t/is (thrown? NullPointerException (bit-and-not nil 1)))
     :cljs (t/is (bit-and-not nil 1)))
  #?(:clj (t/is (thrown? NullPointerException (bit-and-not 1 nil)))
     :cljs (t/is (bit-and-not 1 nil)))

  (t/are [ex a b] (= ex (bit-and-not a b))
         0 0 0
         8 12 4
         0xff 0xff 0
         0x80 0xff 0x7f
         r/all-ones-int r/all-ones-int 0
         0 0 r/all-ones-int
         0 r/all-ones-int r/all-ones-int
         r/full-width-checker-pos r/full-width-checker-pos 0
         0 r/full-width-checker-pos r/full-width-checker-pos
         0 r/full-width-checker-pos r/all-ones-int 
         r/full-width-checker-pos r/full-width-checker-pos r/full-width-checker-neg))
