(ns clojure.core-test.rand-int
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/rand-int
  (deftest test-rand-int
    ;; Generally, we test that the numbers returned pass `double?` and
    ;; that they are unique. Note that in theory `rand` could return
    ;; the same double in the first 100 attempts, but this is highly
    ;; unlikely.
    (let [length 100
          limit Integer/MAX_VALUE          ; Note Long/MAX_VALUE overflows
          x (repeatedly length #(rand-int limit))]
      (is (every? int? x))
      (is (every? pos? x))
      (is (apply distinct? x))
      (is (every? #(< % limit) x)))))
