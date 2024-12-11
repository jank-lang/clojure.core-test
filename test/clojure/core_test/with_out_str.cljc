(ns clojure.core-test.with-out-str
  (:require [clojure.test :as t :refer [deftest testing is are]]))

(deftest test-with-out-str
  (is (= (str "some sample :text here" (println-str)
              "[:a :b] {:c :d} #{:e} (:f)" (prn-str))
         (with-out-str
           (println "some" "sample" :text 'here)
           (prn [:a :b] {:c :d} #{:e} '(:f))))))
