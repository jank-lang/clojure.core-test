(ns clojure.core-test.pr-str
  (:require [clojure.test :as t :refer [deftest testing is are]]))

(deftest test-pr-str
  (is (= "\"a\" \"string\"" (pr-str "a" "string")))
  (is (= "nil \"a\" \"string\" \\A \\space 1 17.0 [:a :b] {:c :d} #{:e}"
         (pr-str nil "a" "string" \A \space 1 17.0 [:a :b] {:c :d} #{:e}))))
