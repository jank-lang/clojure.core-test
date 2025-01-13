(ns clojure.core-test.name
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/name
 (deftest test-name
   (are [expected x] (= expected (name x))
     "abc" "abc"
     "abc" :abc
     "abc" 'abc
     "def" :abc/def
     "def" 'abc/def
     "abc*+!-_'?<>=" :abc/abc*+!-_'?<>=
     "abc*+!-_'?<>=" 'abc/abc*+!-_'?<>=)

   (is (thrown? Exception (name nil)))))
