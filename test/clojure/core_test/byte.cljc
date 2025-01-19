(ns clojure.core-test.byte
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/byte
 (deftest test-byte
   ;; There is no platform independent predicate to test for a
   ;; byte (e.g., `byte?`). In ClojureJVM, it's an instance of
   ;; `java.lang.Byte`, but there is no predicate for it. Here, we just
   ;; test whether it's a fixed-length integer of some sort.
   (is (int? (byte 0)))
   #?@(:cljs []
       :default
       [(is (instance? java.lang.Byte (byte 0)))])

   ;; Check conversions and rounding from other numeric types
   (are [expected x] (= expected (byte x))
     -128 -128
     0    0
     127  127
     1    1N
     0    0N
     -1   -1N
     1    1.0M
     0    0.0M
     -1   -1.0M
     1    1.1
     -1   -1.1
     1    1.9
     #?@(:cljs []
         :default
         [1    3/2
          -1   -3/2
          0    1/10
          0    -1/10])
     1    1.1M
     -1   -1.1M)

   ;; `byte` throws outside the range of 127 ... -128.
   (is (thrown? #?(:cljs :default :clj Exception) (byte -128.000001)))
   (is (thrown? #?(:cljs :default :clj Exception) (byte -129)))
   (is (thrown? #?(:cljs :default :clj Exception) (byte 128)))
   (is (thrown? #?(:cljs :default :clj Exception) (byte 127.000001)))

   ;; Check handling of other types
   (is (thrown? #?(:cljs :default :clj Exception) (byte "0")))
   (is (thrown? #?(:cljs :default :clj Exception) (byte :0)))
   (is (thrown? #?(:cljs :default :clj Exception) (byte [0])))
   (is (thrown? #?(:cljs :default :clj Exception) (byte nil)))))
