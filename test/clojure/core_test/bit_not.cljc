(ns clojure.core-test.bit-not
  (:require [clojure.test :as t]))

(t/deftest common
  (t/is (thrown? NullPointerException (bit-not nil)))

  (t/are [ex a] (= ex (bit-not a))
         -2r1000 2r0111
         2r0111 -2r1000))
