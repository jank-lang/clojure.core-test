(ns clojure.core.bits-test
  (:require [clojure.test :refer :all]))
  
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

(deftest test-bit-shift-left
  ; bitwise shift left
  (are [ex a b] (= ex (bit-shift-left a b))
    1024 1 10
    2r110100 2r1101 2))

(deftest test-bit-shift-right
  ; bitwise shift left
  (are [ex a b] (= ex (bit-shift-right a b))
    2r1101 2r1101 0
    2r110 2r1101 1
    2r11 2r1101 2
    2r1 2r1101 3
    2r0 2r1101 4
    2r0 2r1101 63))

(deftest test-bit-test
  ; test bit at index n
  (are [ex a b] (= ex (bit-test a b))
    true 2r1001 0
    false 2r1001 1
    false 2r1001 2
    true 2r1001 3
    false 2r1001 4
    false 2r1001 63))

(deftest test-bit-xor
  ; bitwise exclusive or
  (are [ex a b] (= ex (bit-xor a b))
    2r0101 2r1100 2r1001))

(deftest test-unsigned-bit-shift-right
  (are [ex a b] (= ex (unsigned-bit-shift-right a b))
    18014398509481968 -1 10))