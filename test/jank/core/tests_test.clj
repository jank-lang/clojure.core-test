(ns jank.core.tests-test
  (:require [clojure.test :refer :all]))

(deftest test-abs
  (are [in ex] (= ex (abs in))
    -1 1
    1 1
    Long/MIN_VALUE Long/MIN_VALUE ;; special case!
    -1.0 1.0
    -0.0 0.0
    ##-Inf ##Inf
    ##Inf ##Inf
    -123.456M 123.456M
    -123N 123N
    -1/5 1/5)
  (is (NaN? (abs ##NaN))))

(deftest test-and
  ; (and) should return true
  (is true (and))
  ; if left hand side is truthy (not nil or false) return right hand side
  (are [ex a b] (= ex (and a b))
    true true true
    false true false
    nil true nil))

(deftest test-any?
  ; return true for any argument
  (are [x] (= (any? x) true)
    nil
    true
    false
    ""
    0
    1))

(deftest test-bit-and
  ; bitwise and
  (are [ex a b] (= ex (bit-and a b))
    8 12 9
    8 8 0xff))


(deftest test-bit-and-not
  ; equivalent to (bit-and x (bit-not y))
  (are [ex a b] (= ex (bit-and-not a b))
    0 0 0
    8 12 4
    0xff 0xff 0
    0x80 0xff 0x7f))

(deftest test-bit-clear
  ; clear bit at index n
  (are [ex a b] (= ex (bit-clear a b))
    3 11 3))

(deftest test-bit-flip
  ; flip bit at index n
  (are [ex a b] (= ex (bit-flip a b))
    2r1111 2r1011 2
    2r1011 2r1111 2))

(deftest test-bit-not
  ; bitwise complement
  (are [ex a] (= ex (bit-not a))
    -2r1000 2r0111
    2r0111 -2r1000))

(deftest test-bit-or
  ; bitwise or
  (are [ex a b] (= ex (bit-or a b))
    2r1101 2r1100 2r1001
    1 1 0))

(deftest test-bit-set
  ; Set bit at index b
  (are [ex a b] (= ex (bit-set a b))
    2r1111 2r1011 2
    -9223372036854775808 0 63
    4294967296 0 32
    65536 0 16
    256 0 8
    16 0 4))