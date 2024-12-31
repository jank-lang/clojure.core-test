(ns clojure.core-test.partial
  (:require [clojure.test :as t]))

(defn test-fn [& args]
  (into [] args))

(t/deftest test-partial []
  (let [simple-use (partial inc 2)]
    (t/is (= 3 (simple-use))))
  (let [lazily-evaluated (partial inc 2 3)]
    #?(:clj  (t/is (thrown? Exception (lazily-evaluated)))
       :cljs (t/is (thrown? js/Error  (lazily-evaluated)))))
  (let [variadic (partial test-fn 1 2 3)]
    (t/is (= [1 2 3 4]   (variadic 4)))
    (t/is (= [1 2 3 4 5] (variadic 4 5)))))
