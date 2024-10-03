(ns clojure.core.bit-or-test
  (:require [clojure.test :as t]))

(t/deftest common
  (t/is (thrown? NullPointerException (bit-or nil 1)))
  (t/is (thrown? NullPointerException (bit-or 1 nil)))

  (t/are [ex a b] (= ex (bit-or a b))
         2r1101 2r1100 2r1001
         1 1 0))
