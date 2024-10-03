(ns clojure.core.bit-and-test
  (:require [clojure.test :as t]))

(t/deftest common
  (t/is (thrown? NullPointerException (bit-and nil 1)))
  (t/is (thrown? NullPointerException (bit-and 1 nil)))

  (t/are [ex a b] (= ex (bit-and a b))
         8 12 9
         8 8 0xff))
