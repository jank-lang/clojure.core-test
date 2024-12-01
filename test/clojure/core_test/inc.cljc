(ns clojure.core-test.inc
  (:require [clojure.test :as t]))

(t/deftest common
  (t/are [in ex] (= (inc in) ex)
    1 2
    1465 1466
    -5 -4))

(t/deftest overflow
  #?(:clj (t/is (thrown? ArithmeticException (inc Long/MAX_VALUE)))
     :cljs (t/is (= (inc js/Number.MAX_SAFE_INTEGER) (+ 2 js/Number.MAX_SAFE_INTEGER)))))

(t/deftest inc-nil
  ;; ClojureScript says (= 1 (inc nil)) because JavaScript casts null to 0
  ;; https://clojuredocs.org/clojure.core/inc#example-6156a59ee4b0b1e3652d754f
  #?(:clj (t/is (thrown? NullPointerException (inc #_:clj-kondo/ignore nil)))
     :cljs (t/is (= 1 (inc #_:clj-kondo/ignore nil)))))
