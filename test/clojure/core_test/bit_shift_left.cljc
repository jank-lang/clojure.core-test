(ns clojure.core-test.bit-shift-left
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/bit-shift-left
  (deftest test-bit-shift-left
    #?(:clj (is (thrown? NullPointerException (bit-shift-left nil 1)))
       :cljs (is (bit-shift-left nil 1)))
    #?(:clj (is (thrown? NullPointerException (bit-shift-left 1 nil)))
       :cljs (is (bit-shift-left 1 nil)))

    (are [ex a b] (= ex (bit-shift-left a b))
      1024     1      10
      2r110100 2r1101 2)))
