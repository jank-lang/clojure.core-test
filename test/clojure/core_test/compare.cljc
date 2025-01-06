(ns clojure.core-test.compare
  (:require [clojure.test :as t]))

(t/deftest numeric-types
  (t/are [r args] (= r (compare (first args) (second args)))
    -1 [0  10]
    0  [0  0]
    1  [0 -100N]
    0  [1  1.0]
    #?@(:cljs []
        :default [-1 [1 100/3]])
    -1 [0  0x01]
    -1 [0  2r01]
    1  [1  nil])

  (t/is (thrown? Exception (compare 1 []))))

(t/deftest lexical-types
  (t/are [r args] (= r (compare (first args) (second args)))
    -1 [\a    \b]
    0  [\0    \0]
    25 [\z    \a]
    -1 ["cat" "dog"]
    -1 ['cat  'dog]
    -1 [:cat  :dog]
    0  [:dog  :dog]
    -1 [:cat  :animal/cat]
    1  ['a    nil])

  (t/is (thrown? Exception (compare "a" [])))
  (t/is (thrown? Exception (compare "cat" '(\c \a \t)))))

(t/deftest collection-types
  (t/are [r args] (= r (compare (first args) (second args)))
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

  (t/is (thrown? Exception (compare []  '())))
  (t/is (thrown? Exception (compare [1] [[]])))
  (t/is (thrown? Exception (compare []  {})))
  (t/is (thrown? Exception (compare []  #{})))
  (t/is (thrown? Exception (compare #{} (sorted-set))))
  (t/is (thrown? Exception (compare #{1} #{1})))
  (t/is (thrown? Exception (compare {1 2} {1 2})))
  (t/is (thrown? Exception (compare (range 5) (range 5))))
  (t/is (thrown? Exception (compare (range 5) (range)))))
