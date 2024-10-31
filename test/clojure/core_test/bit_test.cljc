(ns clojure.core-test.bit-test
  (:require [clojure.test :as t]))

(t/deftest common
  #?(:clj (t/is (thrown? NullPointerException (bit-test nil 1)))
     :cljs (t/is (bit-test nil 1)))
  #?(:clj (t/is (thrown? NullPointerException (bit-test 1 nil)))
     :cljs (t/is (bit-test 1 nil)))

  (t/are [ex a b] (= ex (bit-test a b))
         true 2r1001 0
         false 2r1001 1
         false 2r1001 2
         true 2r1001 3
         false 2r1001 4
         false 2r1001 63))
