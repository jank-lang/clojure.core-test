(ns clojure.core-test.dec
  (:require [clojure.test :as t]))

(t/deftest common
  (t/are [in ex] (= (dec in) ex)
    1 0
    14412 14411
    -3 -4))

(t/deftest underflow
  #?(:clj (t/is (thrown? ArithmeticException (dec Long/MIN_VALUE)))
     :cljs (t/is (= (dec js/Number.MIN_SAFE_INTEGER) (- js/Number.MIN_SAFE_INTEGER 2)))))

(t/deftest dec-nil
  ;; ClojureScript says (= -1 (dec nil)) because JavaScript casts null to 0
  #?(:clj (t/is (thrown? NullPointerException (dec #_:clj-kondo/ignore nil)))
     :cljs (t/is (= -1 (dec #_:clj-kondo/ignore nil)))))
