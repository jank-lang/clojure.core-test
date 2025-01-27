(ns clojure.core-test.drop-while
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/drop-while
  (deftest test-drop-while
    (is (= (range 5 10) (drop-while #(< % 5) (range 0 10))))
    (is (= '(1 2 3) (drop-while keyword? [:a :b :c 1 2 3])))))
