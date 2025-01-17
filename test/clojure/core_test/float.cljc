(ns clojure.core-test.float
  (:require #?(:cljs  [cljs.reader])
            [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.number-range :as r]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/float
 (deftest test-float
   (are [expected x] (= expected (float x))
     (float 1.0) 1
     (float 0.0) 0
     (float -1.0) -1
     (float 1.0) 1N
     (float 0.0) 0N
     (float -1.0) -1N
     (float 1.0) 12/12
     (float 0.0) 0/12
     (float -1.0) -12/12
     (float 1.0) 1.0M
     (float 0.0) 0.0M
     (float -1.0) -1.0M
     (float 0.0) r/min-double)
   (is (NaN? (float ##NaN)))

   #?@(:cljs []
       :default
       [(is (instance? java.lang.Float (float 0)))
        (is (instance? java.lang.Float (float 0.0)))
        (is (instance? java.lang.Float (float 0N)))
        (is (instance? java.lang.Float (float 0.0M)))])

   (is (thrown? IllegalArgumentException (float r/max-double)))
   (is (thrown? IllegalArgumentException (float ##Inf)))
   (is (thrown? IllegalArgumentException (float ##-Inf)))
   (is (thrown? ClassCastException (float "0")))
   (is (thrown? ClassCastException (float :0)))))
