(ns clojure.core-test.bit-clear
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/bit-clear
  (deftest test-bit-clear
    #?(:clj (is (thrown? NullPointerException (bit-clear nil 1)))
       :cljs (is (bit-clear nil 1)))
    #?(:clj (is (thrown? NullPointerException (bit-clear 1 nil)))
       :cljs (is (bit-clear 1 nil)))

    (are [ex a b] (= ex (bit-clear a b))
      3 11 3)))
