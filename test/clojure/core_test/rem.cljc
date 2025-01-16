(ns clojure.core-test.rem
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]
            [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/rem
 (deftest test-rem
   (are [type-pred expected x y] (let [r (rem x y)]
                                   (and (type-pred r)
                                        (= expected r)))
     int? 1  10  3
     int? -1 -10 3
     int? -1 -10 -3
     int? 1  10  -3

     p/big-int? 1N  10   3N
     p/big-int? -1N -10  3N
     p/big-int? -1N -10  -3N
     p/big-int? 1N  10   -3N
     p/big-int? 1N  10N  3
     p/big-int? -1N -10N 3
     p/big-int? -1N -10N -3
     p/big-int? 1N  10N  -3
     p/big-int? 1N  10N  3N
     p/big-int? -1N -10N 3N
     p/big-int? -1N -10N -3N
     p/big-int? 1N  10N  -3N

     double? 1.0  10    3.0
     double? -1.0 -10   3.0
     double? -1.0 -10   -3.0
     double? 1.0  10    -3.0
     double? 1.0  10.0  3
     double? -1.0 -10.0 3
     double? -1.0 -10.0 -3
     double? 1.0  10.0  -3
     double? 1.0  10.0  3.0
     double? -1.0 -10.0 3.0
     double? -1.0 -10.0 -3.0
     double? 1.0  10.0  -3.0

     decimal? 1.0M  10     3.0M
     decimal? -1.0M -10    3.0M
     decimal? -1.0M -10    -3.0M
     decimal? 1.0M  10     -3.0M
     decimal? 1.0M  10.0M  3
     decimal? -1.0M -10.0M 3
     decimal? -1.0M -10.0M -3
     decimal? 1.0M  10.0M  -3
     decimal? 1.0M  10.0M  3.0M
     decimal? -1.0M -10.0M 3.0M
     decimal? -1.0M -10.0M -3.0M
     decimal? 1.0M  10.0M  -3.0M

     ;; Unexpectedly downconverts result to double, rather than BigDecimal
     double? 1.0  10.0M  3.0
     double? -1.0 -10.0M 3.0
     double? -1.0 -10.0M -3.0
     double? 1.0  10.0M  -3.0
     double? 1.0  10.0   3.0M
     double? -1.0 -10.0  3.0M
     double? -1.0 -10.0  -3.0M
     double? 1.0  10.0   -3.0M

    #?@(:cljs []
        :default
        [p/big-int?   0N   3     1/2
         ratio?     1/3  3     4/3
         ratio?     7/2  37/2  15
         p/big-int?   0N   3     -1/2
         ratio?     1/3  3     -4/3
         ratio?     7/2  37/2  -15
         p/big-int?   0N   -3    1/2
         ratio?     -1/3 -3    4/3
         ratio?     -7/2 -37/2 15
         p/big-int?   0N   -3    -1/2
         ratio?     -1/3 -3    -4/3
         ratio?     -7/2 -37/2 -15]))

   (is (thrown? Exception (rem 10 0)))
   (is (thrown? Exception (rem ##Inf 1))) ; surprising since (/ ##Inf 1) = ##Inf
   (is (NaN? (rem 1 ##Inf)))
   (is (thrown? Exception (rem ##-Inf 1))) ; surprising since (/ ##-Inf 1) = ##-Inf
   (is (NaN? (rem 1 ##-Inf)))
   (is (thrown? Exception (rem ##NaN 1)))
   (is (thrown? Exception (rem 1 ##NaN)))
   (is (thrown? Exception (rem ##NaN 1)))))
