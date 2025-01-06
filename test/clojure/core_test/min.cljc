(ns clojure.core-test.min
  (:require [clojure.test :as t :refer [deftest testing is are]]))

(deftest test-min
  (are [expected x y] (= expected (min x y) (min y x))
    1      1   2
    1N     1N  2N
    1N     1N  2
    1      1   2N
    1.0    1.0 2.0
    1      1   2.0
    1.0    1.0 2
    #?@(:cljs []
        :default [1/2    1/2 1])
    1      1   ##Inf
    ##-Inf 1   ##-Inf
    ##-Inf ##-Inf ##Inf)

;; Single arg just returns argument
  (is (= 1 (min 1)))
  (is (= 2 (min 2)))
  (is (= "x" (min "x")))                ; doesn't check single arg for Number

  ;; Multi-arg
  (is (= 1 (min 1 2 3 4 5)))
  (is (= 1 (min 5 4 3 2 1)))
  (is (= ##-Inf (min 1 2 3 4 5 ##-Inf)))
  (is (= 1 (min 1 2 3 4 5 ##Inf)))

  (is (NaN? (min ##NaN 1)))
  (is (NaN? (min 1 ##NaN)))
  (is (NaN? (min 1 2 3 4 ##NaN)))
  (is (NaN? (min ##-Inf ##NaN ##Inf)))
  (is (NaN? (min ##NaN)))

  (is (thrown? Exception (min "x" "y")))
  (is (thrown? Exception (min nil 1)))
  (is (thrown? Exception (min 1 nil))))
