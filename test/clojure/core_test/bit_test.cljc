(ns clojure.core-test.bit-test
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/bit-test
  (deftest test-bit-test
    #?(:clj (is (thrown? NullPointerException (bit-test nil 1)))
       :cljs (is (bit-test nil 1)))
    #?(:clj (is (thrown? NullPointerException (bit-test 1 nil)))
       :cljs (is (bit-test 1 nil)))

    (are [ex a b] (= ex (bit-test a b))
      true  2r1001 0
      false 2r1001 1
      false 2r1001 2
      true  2r1001 3
      false 2r1001 4
      false 2r1001 63)))
