(ns clojure.core-test.bit-and
  (:require [clojure.test :as t]
            [clojure.core-test.number-range :as r]))

(t/deftest common
  #?(:clj (t/is (thrown? NullPointerException (bit-and nil 1)))
     :cljs (t/is (bit-and nil 1)))
  #?(:clj (t/is (thrown? NullPointerException (bit-and 1 nil)))
     :cljs (t/is (bit-and 1 nil)))

  (t/are [ex a b] (= ex (bit-and a b))
         8 12 9
         8 8 0xff
         0 r/all-ones-int 0
         0 0 r/all-ones-int
         r/all-ones-int r/all-ones-int r/all-ones-int
         0 r/full-width-checker-pos 0
         r/full-width-checker-pos r/full-width-checker-pos r/full-width-checker-pos
         r/full-width-checker-pos r/full-width-checker-pos r/all-ones-int 
         0 r/full-width-checker-pos r/full-width-checker-neg))
