(ns clojure.core.and-test
  (:require [clojure.test :as t]))

(t/deftest common
  (t/is (= true (and)))
  (t/are [ex a b] (= ex (and a b))
         true true true
         false true false
         nil true nil))

(t/deftest infinite-sequence
  (t/is (some? (and (range)))))
