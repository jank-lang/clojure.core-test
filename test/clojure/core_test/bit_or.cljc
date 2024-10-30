(ns clojure.core-test.bit-or
  (:require [clojure.test :as t]
            [clojure.core-test.number-range :as r]))

(t/deftest common
  #?(:clj (t/is (thrown? NullPointerException (bit-or nil 1)))
     :cljs (t/is (bit-or nil 1)))
  #?(:clj (t/is (thrown? NullPointerException (bit-or 1 nil)))
     :cljs (t/is (bit-or 1 nil)))

  (t/are [ex a b] (= ex (bit-or a b))
         2r1101 2r1100 2r1001
         1 1 0
         r/all-ones-int r/all-ones-int 0
         r/all-ones-int 0 r/all-ones-int
         r/all-ones-int r/all-ones-int r/all-ones-int
         r/full-width-checker-pos r/full-width-checker-pos 0
         r/full-width-checker-pos r/full-width-checker-pos r/full-width-checker-pos
         r/all-ones-int r/full-width-checker-pos r/all-ones-int 
         r/all-ones-int r/full-width-checker-pos r/full-width-checker-neg))

