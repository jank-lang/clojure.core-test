(ns clojure.core-test.take
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/take
  (deftest test-take
    (is (= (range 0 5) (take 5 (range 0 10))))
    ;; transducer version
    (is (= (vec (range 0 5)) (into [] (take 5) (range 0 10))))))
