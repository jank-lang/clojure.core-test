(ns clojure.core-test.bit-and-not
  (:require [clojure.test :as t]
            [clojure.core-test.number-range :as r]))

(t/deftest common
  (t/is (thrown? NullPointerException (bit-and-not nil 1)))
  (t/is (thrown? NullPointerException (bit-and-not 1 nil)))

  (t/are [ex a b] (= ex (bit-and-not a b))
         0 0 0
         8 12 4
         0xff 0xff 0
         0x80 0xff 0x7f
         r/ALL-ONES-INT r/ALL-ONES-INT 0
         0 0 r/ALL-ONES-INT
         0 r/ALL-ONES-INT r/ALL-ONES-INT
         r/FULL-WIDTH-CHECKER-POS r/FULL-WIDTH-CHECKER-POS 0
         0 r/FULL-WIDTH-CHECKER-POS r/FULL-WIDTH-CHECKER-POS
         0 r/FULL-WIDTH-CHECKER-POS r/ALL-ONES-INT 
         r/FULL-WIDTH-CHECKER-POS r/FULL-WIDTH-CHECKER-POS r/FULL-WIDTH-CHECKER-NEG))
