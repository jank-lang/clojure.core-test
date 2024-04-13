(ns clojure.core.aset-test
  (:require [clojure.test :refer :all]))

;; all aset-* functions take a the array, index and an update
; index can be repeated for use with multi-dimensional arrays



;; (deftest test-aset (aset))
;; ; Sets the value at the index/indices. Works on Java arrays of reference types. Returns val.

;; (deftest test-aset-boolean (aset-boolean))
;; ; Sets the value at the index/indices. Works on arrays of boolean. Returns val.

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
  (is (thrown? IllegalArgumentException (aset-int (repeat 1) 0 2))))


;; (deftest test-aset-int-to-big
;;   (let [arr (int-array 3)]
;;     (aset-int arr 1 :keyword)
;;     ))
; Sets the value at the index/indices. Works on arrays of int. Returns val.

;; (deftest test-aset-long (aset-long))
;; ; Sets the value at the index/indices. Works on arrays of long. Returns val.

;; (deftest test-aset-short (aset-short))
;; ; Sets the value at the index/indices. Works on arrays of short. Returns val