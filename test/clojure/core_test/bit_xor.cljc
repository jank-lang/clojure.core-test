(ns clojure.core-test.bit-xor
  (:require [clojure.test :as t]
            [clojure.core-test.number-range :as r]))

(t/deftest common
  #?(:clj (t/is (thrown? NullPointerException (bit-xor nil 1)))
     :cljs (t/is (bit-xor nil 1)))
  #?(:clj (t/is (thrown? NullPointerException (bit-xor 1 nil)))
     :cljs (t/is (bit-xor 1 nil)))

  (t/are [ex a b] (= ex (bit-xor a b))
         2r0101 2r1100 2r1001
         r/all-ones-int r/all-ones-int 0
         r/all-ones-int 0 r/all-ones-int
         0 r/all-ones-int r/all-ones-int
         r/full-width-checker-pos r/full-width-checker-pos 0
         0 r/full-width-checker-pos r/full-width-checker-pos
         r/full-width-checker-neg r/full-width-checker-pos r/all-ones-int 
         r/all-ones-int r/full-width-checker-pos r/full-width-checker-neg))
