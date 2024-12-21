(ns clojure.core-test.seq
  (:require [clojure.test :as t :refer [deftest testing is are]]))

(deftest test-seq
  ;; Sourced via canSeq https://github.com/clojure/clojure/blob/master/src/jvm/clojure/lang/RT.java#L581
  (are [in expected] (= expected (seq in))
    "test" '(\t \e \s \t)
    [1 2 3 4] '(1 2 3 4)
    '(:a :b :c :d) '(:a :b :c :d)
    '() nil
    nil nil
    (range 5 10) '(5 6 7 8 9)
    (int-array 3) '(0 0 0))
  (testing "sets and maps"
    (let [input #{440M 55000M 80000}
          input-map {:a {:b "4"}
                     :c 800
                     nil 40}]
      (is (= input (into #{} (seq input))))
      (is (= input-map (into {} (seq input-map))))))
  (testing "nonseqables"
    (is (thrown? IllegalArgumentException (seq 1)))
    (is (thrown? IllegalArgumentException (seq (fn []))))))
