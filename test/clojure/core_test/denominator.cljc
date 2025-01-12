(ns clojure.core-test.denominator
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]))

(p/when-var-exists 'clojure.core/denominator
 (deftest test-denominator
   (is (= 2 (denominator 1/2)))
   (is (= 3 (denominator 2/3)))
   (is (= 4 (denominator 3/4)))

   (is (thrown? Exception (denominator 1)))
   (is (thrown? Exception (denominator 1.0)))
   (is (thrown? Exception (denominator 1N)))
   (is (thrown? Exception (denominator 1.0M)))
   (is (thrown? Exception (denominator ##Inf)))
   (is (thrown? Exception (denominator ##NaN)))
   (is (thrown? Exception (denominator nil)))))
