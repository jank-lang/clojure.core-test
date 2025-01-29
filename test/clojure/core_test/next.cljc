(ns clojure.core-test.next
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/next
  (deftest test-next
    (is (= '(1 2 3 4 5 6 7 8 9) (next (range 0 10))))
    (is (= '(2 3 4 5 6 7 8 9) (next (next (range 0 10)))))
    (is (= 1 (first (next (range)))))        ; infinite lazy seq
    (is (= '(2 3) (next [1 2 3])))
    (is (nil? (next nil)))
    (is (nil? (next '())))
    (is (nil? (next [])))))
