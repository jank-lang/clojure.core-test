(ns clojure.core-test.any
  (:require [clojure.test :as t]))

(t/deftest common
  (t/are [x] (= true (any? x))
         nil
         true
         false
         ""
         0
         1))

(t/deftest infinite-sequence
  (t/is (= true (any? (range)))))
