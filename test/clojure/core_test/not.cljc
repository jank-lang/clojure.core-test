(ns clojure.core-test.not
  (:require
   #?(:cljs  [cljs.reader])
   [clojure.test :as t :refer [deftest testing is are]]
   [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/not
  (deftest test-not
    (testing "common"
      (are [given expected] (= expected (not given))
        nil                    true
        true                   false
        false                  true
        #?(:clj (Object.)
           :cljs #js {}
           :default :anything) false))

    (testing "infinite-sequence"
      (is (= false (not (range)))))))
