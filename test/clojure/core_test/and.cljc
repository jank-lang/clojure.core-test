(ns clojure.core-test.and
  (:require [clojure.test :as t]))

(t/deftest common
  (t/is (= true (and)))
  (t/are [x] (= x (and x))
    true
    false
    nil
    :example)
  (t/are [ex a b] (= ex (and a b))
    true true true
    false true false
    false false true
    false false false
    true true true
    nil true nil
    nil nil true
    nil nil nil)
  (t/testing "binds values before comparing"
    (let [counter (volatile! 0)]
      (t/is (= nil (and (vswap! counter inc) nil)))
      (t/is (= 1 @counter))))
  (t/testing "early exits"
    (let [counter (volatile! 0)]
      (t/is (= nil (and nil (vswap! counter inc))))
      (t/is (= 0 @counter))))
  (t/testing "handles varargs"
    (t/is (= nil (and nil nil 3)))
    (t/is (= nil (and nil nil nil nil nil nil nil nil nil nil nil nil true)))))

(t/deftest infinite-sequence
  (t/is (some? (and (range)))))
