(ns clojure.core-test.not
  (:require
   [clojure.test :as t :refer [deftest testing is are]]
   [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/not
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
