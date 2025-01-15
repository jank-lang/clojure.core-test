(ns clojure.core-test.rand
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/rand
  (deftest test-rand
    ;; Generally, we test that the numbers returned pass `double?` and
    ;; that they are unique. Note that in theory `rand` could return
    ;; the same double in the first 100 attempts, but that is highly
    ;; unlikely.
    (let [length 100]
      (testing "zero-arg case"
        (let [x (repeatedly length rand)]
          (is (every? double? x))
          (is (every? pos? x))
          (is (apply distinct? x))))
      (testing "one-arg case"
        (let [limit 0.01 ; Choose something < 1 to constrain it further
              x (repeatedly length #(rand limit))]
          (is (every? double? x))
          (is (every? pos? x))
          (is (apply distinct? x))
          (is (every? #(< % limit) x)))))))
