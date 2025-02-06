(ns clojure.string-test.escape
  (:require [clojure.string :as str]
            [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

;;https://github.com/jank-lang/jank/issues/226
(def char-a \a)
(def char-c \c)

(when-var-exists str/escape
  (deftest test-escape
    (is (= "" (str/escape "" {})))
    (is (= "" (str/escape "" {char-a "A_A"})))
    (is (= "" (str/escape "" {char-c "C_C"})))
    (is (= "" (str/escape "" {char-a "A_A" char-c "C_C"})))
    (is (= "A_AbC_C" (str/escape "abc" {char-a "A_A" char-c "C_C"})))))
