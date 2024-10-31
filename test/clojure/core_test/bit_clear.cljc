(ns clojure.core-test.bit-clear
  (:require [clojure.test :as t]))

(t/deftest common
  #?(:clj (t/is (thrown? NullPointerException (bit-clear nil 1)))
     :cljs (t/is (bit-clear nil 1)))
  #?(:clj (t/is (thrown? NullPointerException (bit-clear 1 nil)))
     :cljs (t/is (bit-clear 1 nil)))

  (t/are [ex a b] (= ex (bit-clear a b))
         3 11 3))
