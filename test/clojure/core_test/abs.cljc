(ns clojure.core-test.abs
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/abs
  (deftest test-abs
    (testing "common"
     (are [in ex] (= ex (abs in))
       -1             1
       1              1
       Long/MIN_VALUE Long/MIN_VALUE    ; Special case!
       -1.0           1.0
       -0.0           0.0
       ##-Inf         ##Inf
       ##Inf          ##Inf
       -123.456M      123.456M
       -123N          123N
       #?@(:cljs []
           :default
           [-1/5           1/5]))
     (is (NaN? (abs ##NaN)))
     (is (thrown? #?(:cljs :default :clj Exception) (abs nil))))

    (testing "unboxed"
      (let [a  42
            b  -42
            a' (abs a)
            b' (abs b)]
        (is (= 42 a'))
        (is (= 42 b'))))))
