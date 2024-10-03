(ns clojure.core.bit-flip-test
  (:require [clojure.test :as t]))

(t/deftest common
  (t/is (thrown? NullPointerException (bit-flip nil 1)))
  (t/is (thrown? NullPointerException (bit-flip 1 nil)))

  (t/are [ex a b] (= ex (bit-flip a b))
         2r1111 2r1011 2
         2r1011 2r1111 2))
