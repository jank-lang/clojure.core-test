(ns clojure.core-test.second
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/second
  (deftest test-second
    (is (= 1 (second (range 0 10))))
    (is (= 1 (second (range))))         ; infinite lazy seq
    (is (= :b (second [:a :b :c])))
    (is (= :b (second '(:a :b :c))))
    (is (nil? (second '())))
    (is (nil? (second [])))
    (is (nil? (second nil)))))
