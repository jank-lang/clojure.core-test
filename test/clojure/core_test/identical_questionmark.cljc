(ns clojure.core-test.identical-questionmark
  (:require [clojure.test :as t :refer [deftest testing is are]]))

(deftest test-identical?
  ;; objects that are the same object are identical
  (let [x (hash-map)
        ;; this forces y to be a different object than x
        y (-> (hash-map :a-key :a-val)
              (dissoc :a-key))]
    ;; x and y are equal, but they are not identical
    (is (= x y))
    (is (not (identical? x y)))
    ;; but each is identical with itself
    (is (identical? x x))
    (is (identical? y y))))
