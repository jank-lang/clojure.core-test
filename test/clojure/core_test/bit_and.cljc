(ns clojure.core-test.bit-and
  (:require #?(:cljs  [cljs.reader])
            [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.number-range :as r]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/bit-and
  (deftest test-bit-and
    #?(:clj (is (thrown? NullPointerException (bit-and nil 1)))
       :cljs (is (bit-and nil 1)))
    #?(:clj (is (thrown? NullPointerException (bit-and 1 nil)))
       :cljs (is (bit-and 1 nil)))

    (are [ex a b] (= ex (bit-and a b))
      8                        12                       9
      8                        8                        0xff
      0                        r/all-ones-int           0
      0                        0                        r/all-ones-int
      r/all-ones-int           r/all-ones-int           r/all-ones-int
      0                        r/full-width-checker-pos 0
      r/full-width-checker-pos r/full-width-checker-pos r/full-width-checker-pos
      r/full-width-checker-pos r/full-width-checker-pos r/all-ones-int
      0                        r/full-width-checker-pos r/full-width-checker-neg)))
