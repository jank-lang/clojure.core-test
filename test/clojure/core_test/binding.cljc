(ns clojure.core-test.binding
  (:require [clojure.test :as t]))

(def  ^:dynamic *x* :unset)
(def  ^:dynamic *y* nil)
(defn ^:dynamic *f* [x] (inc x))

(defn test-fn [] *x*)

(t/deftest test-binding
  ;; base-case with no overrides
  (t/is (= *x* :unset) "Unset is :unset")
  (t/is (= (*f* 1) 2)  "fn call")

  ;; common cases
  (t/is (binding [*x* :set] (= *x*       :set)) "Can bind dynamic var.")
  (t/is (binding [*x* :set] (= (test-fn) :set)) "Binding for indirect reference.")
  (t/is (binding [*x* nil]  (= (test-fn) nil))  "Dynamic vars are nullable.")
  (t/is (binding [*f* dec]  (= (*f* 1)   0))    "Can bind functions.")

  ;; infinite seqs
  (binding [*x* (range)]
    (t/is (= '(0 1 2 3) (take 4 (test-fn))) "Infinite range")
    (t/is (= '(0 1 2 3) (take 4 (test-fn))) "Immutability"))

  ;; Nested cases
  (binding [*x* :first!]
    (let [layer-1 (fn [] (test-fn))]
      (binding [*x* :second!]
        (t/is (= :second! (layer-1) (test-fn)) "Value is determined at call-site"))))
  (binding [*y* *x*]
    (t/is (= *y* :unset) "Dynamic reference is by value at binding.")
    (binding [*x* :layer-2]
      (t/is (= *y* :unset) "Dynamic reference does not update."))
    (binding [*y* *x*
              *x* :set-later]
      (t/is (= *y* :unset) "Bind vars are applied in sequence.")))
  (let [f (fn [] (binding [*x* :inside-f] (test-fn)))]
    (binding [*x* :outside-f]
      (t/is (= (test-fn) :outside-f))
      (t/is (= (f)       :inside-f) "Nested in func-call"))))
