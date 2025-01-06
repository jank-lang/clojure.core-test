(ns clojure.core-test.abs
  (:require [clojure.test :as t]))

(t/deftest common
  (t/is (thrown? NullPointerException (abs nil)))
  (t/are [in ex] (= ex (abs in))
         -1 1
         1 1
         Long/MIN_VALUE Long/MIN_VALUE ; Special case!
         -1.0 1.0
         -0.0 0.0
         ##-Inf ##Inf
         ##Inf ##Inf
         -123.456M 123.456M
         -123N 123N
         #?@(:cljs []
             :default [-1/5 1/5]))
  (t/is (NaN? (abs ##NaN))))

(t/deftest unboxed
  (let [a 42
        b -42
        a' (abs a)
        b' (abs b)]
    (t/is (= 42 a'))
    (t/is (= 42 b'))))
