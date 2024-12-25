(ns clojure.core-test.fnil
  (:require [clojure.test :as t]))

(defn test-fn [& x]
  (into [] x))

(def arg 'not-nil)

(t/deftest fnil-test
  (let [arity-1 (fnil test-fn 100)]
    (t/is (= [100]             (arity-1 nil)))
    (t/is (= [arg]             (arity-1 arg))))
  (let [arity-2 (fnil test-fn 100 200)]
    (t/is (= [100 200]         (arity-2 nil nil)))
    (t/is (= [arg 200]         (arity-2 arg nil)))
    (t/is (= [100 arg]         (arity-2 nil arg)))
    (t/is (= [arg arg]         (arity-2 arg arg))))
  (let [arity-3 (fnil test-fn 100 200 300)]
    (t/is (= [100 200 300]     (arity-3 nil nil nil)))
    (t/is (= [arg 200 300]     (arity-3 arg nil nil)))
    (t/is (= [100 arg 300]     (arity-3 nil arg nil)))
    (t/is (= [100 200 arg]     (arity-3 nil nil arg)))
    (t/is (= [arg arg 300]     (arity-3 arg arg nil)))
    (t/is (= [100 arg arg]     (arity-3 nil arg arg)))
    (t/is (= [arg 200 arg]     (arity-3 arg nil arg)))
    (t/is (= [arg arg arg]     (arity-3 arg arg arg))))
  (let [arity-+ (fnil test-fn 100 200 300)]
    (t/is (= [100 200 300 arg] (arity-+ nil nil nil arg)))
    (t/is (= [arg 200 300 arg] (arity-+ arg nil nil arg)))
    (t/is (= [100 arg 300 arg] (arity-+ nil arg nil arg)))
    (t/is (= [100 200 arg arg] (arity-+ nil nil arg arg)))))
