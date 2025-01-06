(ns clojure.core-test.num
  (:require [clojure.test :as t :refer [deftest testing is are]]))

(deftest test-num
  #?@(:cljs []
      :default
      ;; The compiler should pass `x` as a primitive to `num`.
      [(let [x 1.0]
         (is (instance? java.lang.Double (num x))))
       (let [x 1]
         (is (instance? java.lang.Long (num x))))
       ;; `BigInt` and `BigDecimal` are always boxed and `num` just returns them as-is.
       (is (instance? clojure.lang.BigInt (num 1N)))
       (is (instance? java.math.BigDecimal (num 1.0M)))]))
