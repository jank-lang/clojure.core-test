(ns clojure.core-test.take-while
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/take-while
  (deftest test-take-while
    (is (= (range 0 5) (take-while #(< % 5) (range 0 10))))
    ;; transducer version
    (is (= (vec (range 0 5)) (into [] (take-while #(< % 5)) (range 0 10))))
    (is (= [:a :b :c] (into [] (take-while keyword?) [:a :b :c 1 2 3])))))
