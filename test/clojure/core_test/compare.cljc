(ns clojure.core-test.compare
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/compare
 (deftest test-compare
   (testing "numeric-types"
     (are [r args] (= r (compare (first args) (second args)))
       -1 [0  10]
       0  [0  0]
       1  [0 -100N]
       0  [1  1.0]
       #?@(:cljs []
           :default
           [-1 [1  100/3]])
       -1 [0  0x01]
       -1 [0  2r01]
       1  [1  nil])

     (is (thrown? #?(:cljs :default :clj Exception) (compare 1 []))))

  (testing "lexical-types"
    (are [r args] (= r (compare (first args) (second args)))
      -1 [\a    \b]
      0  [\0    \0]
      25 [\z    \a]
      -1 ["cat" "dog"]
      -1 ['cat  'dog]
      -1 [:cat  :dog]
      0  [:dog  :dog]
      -1 [:cat  :animal/cat]
      1  ['a    nil])

    (is (thrown? #?(:cljs :default :clj Exception) (compare "a" [])))
    (is (thrown? #?(:cljs :default :clj Exception) (compare "cat" '(\c \a \t)))))

  (testing "collection-types"
    (are [r args] (= r (compare (first args) (second args)))
      0  [[]          []]
      1  [[3]         [1]]
      -1 [[]          [1 2]]
      -1 [[]          [[]]]
      0  [#{}         #{}]
      0  [{}          {}]
      0  [(array-map) (array-map)]
      0  [(hash-map)  (hash-map)]
      0  [{}          (hash-map)]
      0  [{}          (array-map)]
      0  ['()         '()]
      1  [[]          nil])

    (is (thrown? #?(:cljs :default :clj Exception) (compare []  '())))
    (is (thrown? #?(:cljs :default :clj Exception) (compare [1] [[]])))
    (is (thrown? #?(:cljs :default :clj Exception) (compare []  {})))
    (is (thrown? #?(:cljs :default :clj Exception) (compare []  #{})))
    (is (thrown? #?(:cljs :default :clj Exception) (compare #{} (sorted-set))))
    (is (thrown? #?(:cljs :default :clj Exception) (compare #{1} #{1})))
    (is (thrown? #?(:cljs :default :clj Exception) (compare {1 2} {1 2})))
    (is (thrown? #?(:cljs :default :clj Exception) (compare (range 5) (range 5))))
    (is (thrown? #?(:cljs :default :clj Exception) (compare (range 5) (range)))))))
