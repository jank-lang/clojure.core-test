(ns clojure.core-test.nil-questionmark
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/nil?
  (deftest test-nil?
    (testing "common"
      (are [in ex] (= (nil? in) ex)
        nil   true
        0     false
        false false
        ""    false
        "nil" false
        :nil  false
        ##Inf false
        ##NaN false
        0.0   false
        {}    false
        '()   false
        []    false))

    (testing "infinite-sequence"
      (is (= false (nil? (range)))))))
