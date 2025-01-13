(ns clojure.core-test.namespace
  (:require [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability :as p]))

(p/when-var-exists clojure.core/namespace
 (deftest test-namespace
   (are [expected sym-or-kw] (= expected (namespace sym-or-kw))
     "clojure.core" 'clojure.core/+
     "abc"          :abc/def
     "abc"          'abc/def
     nil            :abc
     nil            'abc)
  
   (is (thrown? Exception (namespace nil)))))
