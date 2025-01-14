(ns clojure.core-test.unsigned-bit-shift-right
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/unsigned-bit-shift-right
 (deftest test-unsigned-bit-shift-right
   #?(:clj (is (thrown? NullPointerException (unsigned-bit-shift-right nil 1)))
      :cljs (is (unsigned-bit-shift-right nil 1)))
   #?(:clj (is (thrown? NullPointerException (unsigned-bit-shift-right 1 nil)))
      :cljs (is (unsigned-bit-shift-right 1 nil)))

   (are [ex a b] (= ex (unsigned-bit-shift-right a b))
     18014398509481983 -1 10)))
