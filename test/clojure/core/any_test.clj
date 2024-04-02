(ns clojure.core.any-test
  (:require [clojure.test :refer :all]))

; question mark is reserved in filenames in NTFS, FAT and other fileystems.

(deftest test-any?
  ; return true for any argument
  (are [x] (= (any? x) true)
    nil
    true
    false
    ""
    0
    1))

