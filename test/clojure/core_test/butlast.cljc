(ns clojure.core-test.butlast
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/butlast
  (deftest test-butlast
    (are [expected x] (= expected (butlast x))
      nil (range 1)
      (range 1) (range 2)
      (range 2) (range 3)
      (range 3) (range 4)
      nil '(0)
      nil [0]
      nil nil)))
