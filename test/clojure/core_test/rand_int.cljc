(ns clojure.core-test.rand-int
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/rand-int
  (deftest test-rand-int
    ;; Generally, we test that the numbers returned pass `int?` and
    ;; that they are not constant.
    (let [length 100
          limit 2000000000              ; 2 billion
          x (repeatedly length #(rand-int limit))]
      (is (every? int? x))
      (is (every? pos? x))
      (is (> (count (set x)) 1))        ; Shouldn't be constant
      (is (every? #(< % limit) x)))))
