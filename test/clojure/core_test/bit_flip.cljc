(ns clojure.core-test.bit-flip
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/bit-flip
  (deftest test-bit-flip
    #?(:clj (is (thrown? NullPointerException (bit-flip nil 1)))
       :cljs (is (bit-flip nil 1)))
    #?(:clj (is (thrown? NullPointerException (bit-flip 1 nil)))
       :cljs (is (bit-flip 1 nil)))

    (are [ex a b] (= ex (bit-flip a b))
      2r1111 2r1011 2
      2r1011 2r1111 2)))
