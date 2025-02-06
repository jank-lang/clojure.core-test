(ns clojure.string-test.capitalize
  (:require [clojure.string :as str]
            [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists str/capitalize
  (deftest test-capitalize
    (is (= "" (str/capitalize "")))
    (is (= "A" (str/capitalize "a")))
    (is (= "A thing" (str/capitalize "a Thing")))))
