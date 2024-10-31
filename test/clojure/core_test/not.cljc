(ns clojure.core-test.not
  (:require
   [clojure.test :as t]))

(t/deftest common
  (t/are [given expected] (= expected (not given))
    nil true
    true false
    false true
    #?(:clj (Object.)
       :cljs #js {}
       :default :anything) false))

(t/deftest infinite-sequence
  (t/is (= false (not (range)))))
