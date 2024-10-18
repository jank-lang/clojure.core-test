(ns clojure.core-test.bit-and
  (:require [clojure.test :as t]
            [clojure.core-test.number-range :as r]))

(t/deftest common
  (t/is (thrown? NullPointerException (bit-and nil 1)))
  (t/is (thrown? NullPointerException (bit-and 1 nil)))

  (t/are [ex a b] (= ex (bit-and a b))
         8 12 9
         8 8 0xff
         0 r/ALL-ONES-INT 0
         0 0 r/ALL-ONES-INT
         r/ALL-ONES-INT r/ALL-ONES-INT r/ALL-ONES-INT
         0 r/FULL-WIDTH-CHECKER-POS 0
         r/FULL-WIDTH-CHECKER-POS r/FULL-WIDTH-CHECKER-POS r/FULL-WIDTH-CHECKER-POS
         r/FULL-WIDTH-CHECKER-POS r/FULL-WIDTH-CHECKER-POS r/ALL-ONES-INT 
         0 r/FULL-WIDTH-CHECKER-POS r/FULL-WIDTH-CHECKER-NEG))
