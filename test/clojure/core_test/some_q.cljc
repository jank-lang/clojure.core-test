(ns clojure.core-test.some-q
  (:require
   [clojure.test :as t]))

(t/deftest common
  (t/are [given expected] (= expected (some? given))
    nil false
    true true
    false true
    #?(:clj (Object.)
       :cljs #js {}
       :default :anything) true))

(t/deftest infinite-sequence
  (t/is (= true (some? (range)))))
