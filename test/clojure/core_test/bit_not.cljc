(ns clojure.core-test.bit-not
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/bit-not
  (deftest test-bit-not
    #?(:clj (is (thrown? NullPointerException (bit-not nil)))
       :cljs (is (bit-not nil)))

    (are [ex a] (= ex (bit-not a))
      -2r1000 2r0111
      2r0111  -2r1000)))
