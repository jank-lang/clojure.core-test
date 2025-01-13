(ns clojure.core-test.rationalize
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/rationalize
 (deftest test-rationalize
   (are [expected x] (let [x' (rationalize x)]
                       (and (= expected x')
                            (or (integer? x')
                                (ratio? x'))))
     1                                  1
     0                                  0
     -1                                 -1
     1N                                 1N
     0N                                 0N
     -1N                                -1N
     1                                  1.0
     0                                  0.0
     -1                                 -1.0
     3/2                                1.5
     11/10                              1.1
     3333333333333333/10000000000000000 (/ 1.0 3.0)
     1                                  1.0M
     0                                  0.0M
     3/2                                1.5M
     11/10                              1.1M)))
