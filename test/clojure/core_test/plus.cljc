(ns clojure.core-test.plus
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.number-range :as r]))

(deftest common
  (are [sum x y] (and (= sum (+ x y))
                      (= sum (+ y x))) ; addition should be commutative
    0 0 0
    1 1 0
    2 1 1
    6 1 5
    10 5 5
    0 1 -1
    -2 -1 -1
    -1 -1 0

    0.0 0.0 0.0
    1.0 1.0 0.0
    2.0 1.0 1.0
    6.0 1.0 5.0
    10.0 5.0 5.0
    0.0 1.0 -1.0
    -2.0 -1.0 -1.0
    -1.0 -1.0 0.0

    0.0 0.0 0
    1.0 1.0 0
    1.0 0.0 1
    2.0 1.0 1
    6.0 1.0 5
    6.0 5.0 1
    10.0 5.0 5
    0.0 1.0 -1
    0.0 -1.0 1
    -2.0 -1.0 -1
    -1.0 -1.0 0
    -1.0 0.0 -1

    0.0 0 0.0
    1.0 1 0.0
    1.0 0 1.0
    2.0 1 1.0
    6.0 1 5.0
    6.0 5 1.0
    10.0 5 5.0
    0.0 1 -1.0
    0.0 -1 1.0
    -2.0 -1 -1.0
    -1.0 -1 0.0
    -1.0 0 -1.0

    1N 0 1N
    1N 0N 1
    1N 0N 1N
    2N 1N 1
    2N 1 1N
    2N 1N 1N
    6N 1 5N
    6N 1N 5
    6N 1N 5N)


  (is (thrown? Exception (+ 1 nil)))
  (is (thrown? Exception (+ nil 1)))
  (is (thrown? Exception (+ 1N nil)))
  (is (thrown? Exception (+ nil 1N)))
  (is (thrown? Exception (+ 1.0 nil)))
  (is (thrown? Exception (+ nil 1.0)))

  #?@(:cljs nil
      :default
      [(is (thrown? Exception (+ r/max-int 1)))
       (is (thrown? Exception (+ r/min-int -1)))
       (is (instance? clojure.lang.BigInt (+ 0 1N)))
       (is (instance? clojure.lang.BigInt (+ 0N 1)))
       (is (instance? clojure.lang.BigInt (+ 0N 1N)))
       (is (instance? clojure.lang.BigInt (+ 1N 1)))
       (is (instance? clojure.lang.BigInt (+ 1 1N)))
       (is (instance? clojure.lang.BigInt (+ 1N 1N)))
       (is (instance? clojure.lang.BigInt (+ 1 5N)))
       (is (instance? clojure.lang.BigInt (+ 1N 5)))
       (is (instance? clojure.lang.BigInt (+ 1N 5N)))]))

