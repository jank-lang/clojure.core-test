(ns clojure.core-test.sort
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/sort
  (deftest test-sort
    (are [expected x] (= expected (sort x))
      '() nil
      '(1 2 3) [3 1 2]
      '(nil 2 3) [3 nil 2]
      '(1 2 3) '(3 1 2)
      '(1 2 3) #{3 1 2}
      '([:a 1] [:b 2]) {:b 2 :a 1}
      '([1] [2] [3]) [[3] [1] [2]]
      '("a" "b" "c") ["b" "a" "c"]
      '(\c \e \j \l \o \r \u) "clojure")
    (is (thrown? #?(:cljs :default, :default Exception) (sort 1)))
    (is (thrown? #?(:cljs :default, :default Exception) (sort [1 []])))
    (is (= '(3 2 1) (sort #(- (compare %1 %2)) [1 2 3])))))
