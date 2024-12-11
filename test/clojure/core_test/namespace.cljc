(ns clojure.core-test.namespace
  (:require [clojure.test :as t :refer [deftest testing is are]]))

(deftest test-namespace
  (are [expected sym-or-kw] (= expected (namespace sym-or-kw))
    "clojure.core" 'clojure.core/+
    "abc"          :abc/def
    "abc"          'abc/def
    nil            :abc
    nil            'abc)
  
  (is (thrown? Exception (namespace nil))))
