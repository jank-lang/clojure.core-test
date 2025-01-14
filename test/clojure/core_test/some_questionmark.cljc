(ns clojure.core-test.some-questionmark
  (:require
   [clojure.test :as t :refer [deftest testing is are]]
   [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/some?
  (deftest test-some?
    (testing "common"
      (are [given expected] (= expected (some? given))
        nil false
        true true
        false true
        #?(:clj (Object.)
           :cljs #js {}
           :default :anything) true))

    (testing "infinite-sequence"
      (is (= true (some? (range)))))))
