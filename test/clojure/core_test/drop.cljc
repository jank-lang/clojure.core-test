(ns clojure.core-test.drop
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/drop
  (deftest test-drop
    (is (= (range 1 10) (drop 1 (range 0 10))))
    (is (= (range 5 10) (drop 5 (range 0 10))))
    ;; Transducer version
    (is (= (vec (range 5 10)) (into [] (drop 5) (range 0 10))))))
