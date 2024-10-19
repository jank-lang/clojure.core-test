(ns clojure.core-test.star-singlequote
  (:require [clojure.test :as t :refer [is are]]
            [clojure.core-test.number-range :as r]))

#?(:cljs nil
   :default
   (t/deftest common
     (are [prod cand er] (= prod (*' cand er))
       0 0 0
       0 1 0
       0 0 1
       1 1 1
       5 1 5
       5 5 1
       25 5 5
       -1 1 -1
       -1 -1 1
       1 -1 -1
       0 -1 0
       0 0 -1
       (inc r/min-int) r/max-int -1
       (inc r/min-int) -1 r/max-int

       0.0 0.0 0.0
       0.0 1.0 0.0
       0.0 0.0 1.0
       1.0 1.0 1.0
       5.0 1.0 5.0
       5.0 5.0 1.0
       25.0 5.0 5.0
       -1.0 1.0 -1.0
       -1.0 -1.0 1.0
       1.0 -1.0 -1.0
       0.0 -1.0 0.0
       0.0 0.0 -1.0

       0.0 0.0 0
       0.0 1.0 0
       0.0 0.0 1
       1.0 1.0 1
       5.0 1.0 5
       5.0 5.0 1
       25.0 5.0 5
       -1.0 1.0 -1
       -1.0 -1.0 1
       1.0 -1.0 -1
       0.0 -1.0 0
       0.0 0.0 -1

       0.0 0 0.0
       0.0 1 0.0
       0.0 0 1.0
       1.0 1 1.0
       5.0 1 5.0
       5.0 5 1.0
       25.0 5 5.0
       -1.0 1 -1.0
       -1.0 -1 1.0
       1.0 -1 -1.0
       0.0 -1 0.0
       0.0 0 -1.0

       0 0 1N
       0 0N 1
       0 0N 1N
       1 1N 1
       1 1 1N
       1 1N 1N
       5 1 5N
       5 1N 5
       5 1N 5N)

     (is (thrown? Exception (*' 1 nil)))
     (is (thrown? Exception (*' nil 1)))

     (is (instance? clojure.lang.BigInt (*' 0 1N)))
     (is (instance? clojure.lang.BigInt (*' 0N 1)))
     (is (instance? clojure.lang.BigInt (*' 0N 1N)))
     (is (instance? clojure.lang.BigInt (*' 1N 1)))
     (is (instance? clojure.lang.BigInt (*' 1 1N)))
     (is (instance? clojure.lang.BigInt (*' 1N 1N)))
     (is (instance? clojure.lang.BigInt (*' 1 5N)))
     (is (instance? clojure.lang.BigInt (*' 1N 5)))
     (is (instance? clojure.lang.BigInt (*' 1N 5N)))

     (is (instance? clojure.lang.BigInt (*' -1 r/min-int)))
     (is (instance? clojure.lang.BigInt (*' r/min-int -1)))
     (is (instance? clojure.lang.BigInt (*' (long (/ r/min-int 2)) 3)))
     (is (instance? clojure.lang.BigInt (*' 3 (long (/ r/min-int 2)))))))

