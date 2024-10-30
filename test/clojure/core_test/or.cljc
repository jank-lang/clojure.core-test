(ns clojure.core-test.or
  (:require [clojure.test :as t]))

(t/deftest common
  (t/is (= nil (or)))
  (t/are [x] (= x (or x))
    true
    false
    nil
    :example)
  (t/are [ex a b] (= ex (or a b))
    true true true
    true true false
    true false true
    false false false
    true true true
    true true nil
    true nil true
    nil nil nil)
  (t/testing "binds values before comparing"
    (let [counter (volatile! 0)]
      (t/is (= 1 (or nil (vswap! counter inc))))
      (t/is (= 1 @counter))))
  (t/testing "early exits"
    (let [counter (volatile! 0)]
      (t/is (= true (or true (vswap! counter inc))))
      (t/is (= 0 @counter))))
  (t/testing "handles varargs"
    (t/is (= 3 (or nil nil 3)))
    (t/is (= true (or nil nil nil nil nil nil nil nil nil nil nil nil true)))))

(t/deftest infinite-sequence
  (t/is (some? (or (range)))))
