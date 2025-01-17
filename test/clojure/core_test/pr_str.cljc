(ns clojure.core-test.pr-str
  (:require #?(:cljs  [cljs.reader])
            [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/pr-str
 (deftest test-pr-str
   (is (= "\"a\" \"string\"" (pr-str "a" "string")))
   (is (= "nil \"a\" \"string\" \\A \\space 1 17.0 [:a :b] {:c :d} #{:e}"
          (pr-str nil "a" "string" \A \space 1 17.0 [:a :b] {:c :d} #{:e})))))
