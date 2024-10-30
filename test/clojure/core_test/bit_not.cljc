(ns clojure.core-test.bit-not
  (:require [clojure.test :as t]))

(t/deftest common
#?(:clj (t/is (thrown? NullPointerException (bit-not nil)))
   :cljs (t/is (bit-not nil)))

  (t/are [ex a] (= ex (bit-not a))
         -2r1000 2r0111
         2r0111 -2r1000))
