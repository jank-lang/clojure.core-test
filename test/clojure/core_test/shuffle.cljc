(ns clojure.core-test.shuffle
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/shuffle
  (deftest test-shuffle
    (testing "basic shuffle behaviour"
      ;; yes, technically, this could produce a false positive,
      ;; but it's *extremely* unlikely
      (let [x (range 50)
            xset (set x)
            reps 100
            shuffles (repeatedly reps #(shuffle x))]
        (is (every? #(not (= % x)) shuffles))
        (is (every? #(= (set %) xset) shuffles))
        (is (= reps (-> shuffles distinct count)))))
    (testing "different collection types"
      (let [x [1 2 3]
            actual (shuffle x)]
        (is (vector? actual))
        (is (= (count x) (count actual))))
      (let [x #{1 2 3}
            actual (shuffle x)]
        (is (vector? actual))
        (is (= (count x) (count actual))))
      (let [x '(1 2 3)
            actual (shuffle x)]
        (is (vector? actual))
        (is (= (count x) (count actual)))))
    (testing "negative cases"
      (is (thrown? #?(:cljs :default, :default Exception) (shuffle nil)))
      (is (thrown? #?(:cljs :default, :default Exception) (shuffle 1)))
      (is (thrown? #?(:cljs :default, :default Exception) (shuffle "abc")))
      (is (thrown? #?(:cljs :default, :default Exception) (shuffle {}))))))
