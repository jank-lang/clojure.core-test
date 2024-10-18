(ns clojure.core-test.bit-xor
  (:require [clojure.test :as t]
            [clojure.core-test.number-range :as r]))

(t/deftest common
  (t/is (thrown? NullPointerException (bit-xor nil 1)))
  (t/is (thrown? NullPointerException (bit-xor 1 nil)))

  (t/are [ex a b] (= ex (bit-xor a b))
         2r0101 2r1100 2r1001
         r/ALL-ONES-INT r/ALL-ONES-INT 0
         r/ALL-ONES-INT 0 r/ALL-ONES-INT
         0 r/ALL-ONES-INT r/ALL-ONES-INT
         r/FULL-WIDTH-CHECKER-POS r/FULL-WIDTH-CHECKER-POS 0
         0 r/FULL-WIDTH-CHECKER-POS r/FULL-WIDTH-CHECKER-POS
         r/FULL-WIDTH-CHECKER-NEG r/FULL-WIDTH-CHECKER-POS r/ALL-ONES-INT 
         r/ALL-ONES-INT r/FULL-WIDTH-CHECKER-POS r/FULL-WIDTH-CHECKER-NEG))
