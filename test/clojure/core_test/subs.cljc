(ns clojure.core-test.subs
  (:require [clojure.test :as t :refer [deftest testing is are]]))

(deftest test-subs
  (is (= "bcde" (subs "abcde" 1)))
  (is (= "bcd" (subs "abcde" 1 4)))
  (is (= "abc" (subs "abcde" 0 3)))
  (is (= "" (subs "abcde" 0 0)))
  (is (= "" (subs "abcde" 5)))
  (is (= "" (subs "abcde" 5 5)))
  (is (thrown? StringIndexOutOfBoundsException (subs "abcde" 2 1)))
  (is (thrown? StringIndexOutOfBoundsException (subs "abcde" 1 6)))
  (is (thrown? StringIndexOutOfBoundsException (subs "abcde" 1 200)))
  (is (thrown? StringIndexOutOfBoundsException (subs "abcde" -1)))
  (is (thrown? StringIndexOutOfBoundsException (subs "abcde" -1 3)))
  (is (thrown? StringIndexOutOfBoundsException (subs "abcde" -1 -3)))
  (is (thrown? Exception (subs nil 1 2)))
  (is (thrown? Exception (subs "abcde" nil 2)))
  (is (thrown? Exception (subs "abcde" 1 nil))))
