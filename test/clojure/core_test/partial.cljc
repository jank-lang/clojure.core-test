(ns clojure.core-test.partial
  (:require [clojure.test :as t]))

(defn test-fn [& args]
  (into [] args))

(t/deftest test-partial
  (let [simple-use (partial inc 2)]
    (t/is (= 3 (simple-use))))
  (let [lazily-evaluated (partial inc 2 3)]
    #?(:clj  (t/is (thrown? Exception (lazily-evaluated)))
       :cljs (t/is (thrown? js/Error  (lazily-evaluated)))))
  (let [variadic (partial test-fn 1 2 3)]
    (t/is (= [1 2 3 4]   (variadic 4)))
    (t/is (= [1 2 3 4 5] (variadic 4 5))))
  (let [infinite-sequence (partial #(take %2 %1) (range))]
    (t/is (= '(0 1 2 3 4) (infinite-sequence 5)))
    (t/is (= '(0 1 2) (infinite-sequence 3))))
  (let [partial-partial ((partial partial) test-fn)
        pppartial (partial partial-partial :inner)]
    (t/is (= [:inner :outer] (partial-partial :inner :outer)))
    (t/is (= [:inner :outer] (pppartial :outer))))
  (let [seq-of-partials (map #(partial * %1 %2) (range) (range))]
    (t/is (= (map #(* % % %) (range 5))
             (map #(%1 %2) seq-of-partials (range 5))))))
