(ns clojure.core-test.mod
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]
            [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/mod
 (deftest test-mod
   (are [type-pred expected x y] (let [r (mod x y)]
                                   (and (type-pred r)
                                        (= expected r)))
     int? 1  10  3
     int? 2  -10 3
     int? -1 -10 -3
     int? -2 10  -3

     p/big-int? 1N  10  3N
     p/big-int? 2N  -10 3N
     p/big-int? -1N -10 -3N
     p/big-int? -2N 10  -3N

     p/big-int? 1N  10N  3
     p/big-int? 2N  -10N 3
     p/big-int? -1N -10N -3
     p/big-int? -2N 10N  -3

     p/big-int? 1N  10N  3N
     p/big-int? 2N  -10N 3N
     p/big-int? -1N -10N -3N
     p/big-int? -2N 10N  -3N

     double? 1.0  10    3.0
     double? 2.0  -10   3.0
     double? -1.0 -10   -3.0
     double? -2.0 10    -3.0
     double? 1.0  10.0  3
     double? 2.0  -10.0 3
     double? -1.0 -10.0 -3
     double? -2.0 10.0  -3
     double? 1.0  10.0  3.0
     double? 2.0  -10.0 3.0
     double? -1.0 -10.0 -3.0
     double? -2.0 10.0  -3.0

     decimal? 1.0M  10     3.0M
     decimal? 2.0M  -10    3.0M
     decimal? -1.0M -10    -3.0M
     decimal? -2.0M 10     -3.0M
     decimal? 1.0M  10.0M  3
     decimal? 2.0M  -10.0M 3
     decimal? -1.0M -10.0M -3
     decimal? -2.0M 10.0M  -3
     decimal? 1.0M  10.0M  3.0M
     decimal? 2.0M  -10.0M 3.0M
     decimal? -1.0M -10.0M -3.0M
     decimal? -2.0M 10.0M  -3.0M

     ;; Unexpectedly downconverts result to double, rather than BigDecimal
     double? 1.0  10.0M  3.0
     double? 2.0  -10.0M 3.0
     double? -1.0 -10.0M -3.0
     double? -2.0 10.0M  -3.0
     double? 1.0  10.0   3.0M
     double? 2.0  -10.0  3.0M
     double? -1.0 -10.0  -3.0M
     double? -2.0 10.0   -3.0M

     p/big-int? 0N    3     1/2
     ratio?     1/3   3     4/3
     ratio?     7/2   37/2  15
     p/big-int? 0N    3     -1/2
     p/big-int? -1N   3     -4/3
     ratio?     -23/2 37/2  -15
     p/big-int? 0N    -3    1/2
     p/big-int? 1N    -3    4/3
     ratio?     23/2  -37/2 15
     p/big-int? 0N    -3    -1/2
     ratio?     -1/3  -3    -4/3
     ratio?     -7/2  -37/2 -15)

   (is (thrown? Exception (mod 10 0)))
   (is (thrown? Exception (mod ##Inf 1))) ; surprising since (/ ##Inf 1) = ##Inf
   (is (NaN? (mod 1 ##Inf)))
   (is (thrown? Exception (mod ##-Inf 1))) ; surprising since (/ ##-Inf 1) = ##-Inf
   (is (NaN? (mod 1 ##-Inf)))
   (is (thrown? Exception (mod ##NaN 1)))
   (is (thrown? Exception (mod 1 ##NaN)))
   (is (thrown? Exception (mod ##NaN 1)))))
