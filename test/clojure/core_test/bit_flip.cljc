(ns clojure.core-test.bit-flip
  (:require [clojure.test :as t]))

(t/deftest common
  #?(:clj (t/is (thrown? NullPointerException (bit-flip nil 1)))
     :cljs (t/is (bit-flip nil 1)))
  #?(:clj (t/is (thrown? NullPointerException (bit-flip 1 nil)))
     :cljs (t/is (bit-flip 1 nil)))

  (t/are [ex a b] (= ex (bit-flip a b))
         2r1111 2r1011 2
         2r1011 2r1111 2))
