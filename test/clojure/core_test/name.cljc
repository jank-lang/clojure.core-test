(ns clojure.core-test.name
  (:require [clojure.test :as t :refer [deftest testing is are]]))

(deftest test-name
  (are [expected x] (= expected (name x))
  "abc" "abc"
  "abc" :abc
  "abc" 'abc
  "def" :abc/def
  "def" 'abc/def
  "abc*+!-_'?<>=" :abc/abc*+!-_'?<>=
  "abc*+!-_'?<>=" 'abc/abc*+!-_'?<>=)

  (is (thrown? Exception (name nil))))
