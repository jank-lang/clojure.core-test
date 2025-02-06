(ns clojure.string-test.upper-case
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists str/upper-case
  (deftest test-upper-case
    ))
