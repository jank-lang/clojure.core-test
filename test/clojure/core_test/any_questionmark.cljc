(ns clojure.core-test.any-questionmark
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/any?
  (deftest test-any?
    (testing "common"
      (are [x] (= true (any? x))
        nil
        true
        false
        ""
        0
        1))

   (testing "infinite-sequence"
     (is (= true (any? (range)))))))
