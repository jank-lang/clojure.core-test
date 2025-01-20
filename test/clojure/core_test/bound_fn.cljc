(ns clojure.core-test.bound-fn
  (:require [clojure.test :as t]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(def ^:dynamic *x* :unset)

(when-var-exists clojure.core/bound-fn
 (t/deftest test-bound-fn
   (t/testing "base case"
     (let [f (bound-fn [] *x*)]
       (t/is (= (f) :unset) "picks up dynamic vars")
       (binding [*x* :set]
         (t/is (= (f) :set) "And tracks their changes"))))

   (t/testing "Common cases"
     (binding [*x* :set]
       (let [f (bound-fn [] *x*)]
         (binding [*x* :set-again]
           (t/is (= (f) :set) "bound-fn stores values")))))

   (t/testing "Infinite seqs"
     (binding [*x* (range)]
       (let [f (bound-fn [] *x*)]
         (binding [*x* (drop 50 (range))]
           (t/is (= (range 10)
                    (take 10 (f))
                    (take 10 (f))) "infinite seqs work")))))

   (t/testing "Nested cases"
     (binding [*x* :first!]
       (let [f (bound-fn [] *x*)]
         (binding [*x* :second!]
           (let [f (bound-fn [] [(f) *x*])]
             (t/is (= (f) [:first! :second!]) "Nested bound functions work as expected.")))))
     (let [f (fn [] (binding [*x* :inside-f]
                      (bound-fn [] *x*)))]
       (binding [*x* :outside-f]
         (t/is (= ((f)) :inside-f) "bound-fn as result preserves initial bindings"))))

   (t/testing "Threaded/future cases"
     (let [f (bound-fn [] *x*)]
       (let [fut (future (f))]
         (binding [*x* :here]
           (t/is (= @fut :unset) "bound-fn stays bound even in other thread"))))
     (binding [*x* :caller]
       (let [f (future
                 (binding [*x* :callee]
                   (future (bound-fn [] *x*))))]
         (binding [*x* :derefer]
           (let [derefed-f @f]
             (t/is (= :callee (@derefed-f)) "Binding in futures preserved."))))))))
