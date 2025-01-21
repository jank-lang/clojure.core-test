(ns clojure.core-test.{{ns-name}}
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/{{sym-name}}
  (deftest test-{{sym-name}}
    (testing "section name"
      ;; assertions
      ;; (is/are ... )
      )
    (testing "section name"
      ;; more assertions
      ;; (is/are ... )
      )))
