(ns clojure.core.aset-test
  (:require [clojure.test :refer :all]))

;; all aset-* functions take a the array, index and an update
; index can be repeated for use with multi-dimensional arrays



;; (deftest test-aset (aset))
;; ; Sets the value at the index/indices. Works on Java arrays of reference types. Returns val.

;  - [x] edge: intininput is an infinte sequence
(deftest test-aset-boolean
  (let [arr (boolean-array 3) update true]
    (is (= [false false false] (vec arr)) "boolean-array should be false by default")
    (aset-boolean arr 1 update)
    (is (= [false true false] (vec arr)) "failed to update boolean-array")
    (aset-boolean arr 0 update)
    (is (= [true true false] (vec arr)) "failed to update first element of boolean-array")
    (aset-boolean arr 2 update)
    (is (= [true true true] (vec arr)) "failed to update last element of boolean-array")
    ))

(deftest test-aset-boolean-return-val
  (let [arr (boolean-array 3) update true]
    (is (= update (aset-boolean arr 0 update)))))

(deftest test-aset-boolean-2dimensional-array
  (let [arr (to-array [(boolean-array [true true]) (boolean-array [true true])])
        update false]
    (is (= '([true true] [true true]) (map vec arr)))
    (aset-boolean arr 0 1 update)
    (is (= `([true ~update] [ true true]) (map vec arr)))))

(deftest test-aset-boolean-fail-with-invalid-args
  (is (thrown? NullPointerException (aset-boolean nil 0 2)))
  (is (thrown? NullPointerException (aset-boolean (boolean-array [true]) nil 2)))
  (is (thrown? ArrayIndexOutOfBoundsException (aset-boolean (boolean-array [true]) 2 nil)))
  ; flipped arguments
  (is (thrown? ClassCastException (aset-boolean 2 (boolean-array [true]) 2)))
  (is (thrown? IllegalArgumentException (aset-boolean (repeat true) 0 2)))
  ; out of bounds
  (is (thrown? ArrayIndexOutOfBoundsException (aset-boolean (boolean-array [true]) 99 0))))

;; (deftest test-aset-byte (aset-byte))
;; ; Sets the value at the index/indices. Works on arrays of byte. Returns val.

;; (deftest test-aset-char (aset-char))
;; ; Sets the value at the index/indices. Works on arrays of char. Returns val.

;; (deftest test-aset-double (aset-double))
;; ; Sets the value at the index/indices. Works on arrays of double. Returns val.

;; (deftest test-aset-float (aset-float))
;; ; Sets the value at the index/indices. Works on arrays of float. Returns val.


; checklist:
;  - [x] happy path, all valid parameters
;  - [x] special cases - it also supports two dimensional arrays
;  - [x] transducer arity - not a transducer
;  - [x] edge: nil as value
;  - [x] edge: incorrect shape,: flipped arguments
;  - [x] edge: intininput is an infinte sequence
(deftest test-aset-int
  (let [arr (int-array 3) update 951]
    (is (= [0 0 0] (vec arr)) "int-array should be zero filled")
    (aset-int arr 1 update)
    (is (= [0 update 0] (vec arr)) "failed to update int-array")))

(deftest test-aset-int-return-val
  (let [arr (int-array 3) update 562]
    (is (= update (aset-int arr 0 update)))))

(deftest test-aset-int-2dimensional-array
  (let [arr (to-array [(int-array [1 2 3]) (int-array [4 5 6])])
        update 757]
    (is (= '([1 2 3] [4 5 6]) (map vec arr)))
    (aset-int arr 0 1 update)
    (is (= `([1 ~update 3] [4 5 6]) (map vec arr)))))

(deftest test-aset-int-fail-with-invalid-args
  (is (thrown? NullPointerException (aset-int nil 0 2)))
  (is (thrown? NullPointerException (aset-int (int-array [1]) nil 2)))
  (is (thrown? NullPointerException (aset-int (int-array [1]) 2 nil)))
  ; flipped arguments
  (is (thrown? ClassCastException (aset-int 2 (int-array [1]) 2)))
  (is (thrown? IllegalArgumentException (aset-int (repeat 1) 0 2)))
  (is (thrown? ArrayIndexOutOfBoundsException (aset-int (int-array [1]) 99 0))))


;; (deftest test-aset-int-to-big
;;   (let [arr (int-array 3)]
;;     (aset-int arr 1 :keyword)
;;     ))
; Sets the value at the index/indices. Works on arrays of int. Returns val.

;; (deftest test-aset-long (aset-long))
;; ; Sets the value at the index/indices. Works on arrays of long. Returns val.

;; (deftest test-aset-short (aset-short))
;; ; Sets the value at the index/indices. Works on arrays of short. Returns val