(ns clojure.core-test.rest
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/rest
  (deftest test-rest
    (is (= '(1 2 3 4 5 6 7 8 9) (rest (range 0 10))))
    (is (= '(2 3 4 5 6 7 8 9) (rest (rest (range 0 10)))))
    (is (= 1 (first (rest (range)))))   ; Infinite lazy seq
    (is (= '(2 3) (rest [1 2 3])))
    (is (= '() (rest nil)))
    (is (= '() (rest '())))
    (is (= '() (rest [])))))
