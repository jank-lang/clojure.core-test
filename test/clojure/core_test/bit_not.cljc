(ns clojure.core-test.bit-not
  (:require #?(:cljs  [cljs.reader])
            [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/bit-not
  (deftest test-bit-not
    #?(:clj (is (thrown? NullPointerException (bit-not nil)))
       :cljs (is (bit-not nil)))

    (are [ex a] (= ex (bit-not a))
      -2r1000 2r0111
      2r0111  -2r1000)))
