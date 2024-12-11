(ns clojure.core-test.println-str
  (:require [clojure.test :as t :refer [deftest testing is are]]))

(deftest test-println-str
  (let [nl (println-str)]
    ;; `nl` grabs the platform specific newline without calling out to
    ;; platform-specific code (e.g., Java) since we want this to run
    ;; in all environments. Note that we're not actually testing
    ;; whether the newline sequence itself is correct, only that
    ;; `println-str` adds it to the end of the string.
    (is (= (str "a string" nl) (println-str "a" "string")))
    (is (= (str "nil a string A   1 17.0 [:a :b] {:c :d} #{:e}" nl)
           (println-str nil "a" "string" \A \space 1 17.0 [:a :b] {:c :d} #{:e})))))
