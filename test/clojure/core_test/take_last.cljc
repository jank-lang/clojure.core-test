(ns clojure.core-test.take-last
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/take-last
  (deftest test-take-last
    (is (= (range 8 10) (take-last 2 (range 0 10))))))
