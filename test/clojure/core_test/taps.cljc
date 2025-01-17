(ns clojure.core-test.taps
  (:require #?(:cljs  [cljs.reader])
            [clojure.test :as t :refer [deftest testing is are]]
            [clojure.core-test.portability #?(:cljs :refer-macros :default :refer)  [when-var-exists]]))

(when-var-exists clojure.core/add-tap
 ;; testsing multiple tap functions
 (defn sleep [ms]
   #?(:clj (Thread/sleep ms)
      :cljs nil)) ;; can test in the next iteration of the event loop, but this won't show up in the testing results

 ;; These tests may need promises in ClojureScript

 (deftest tapping
   (let [counter1 (volatile! 0)
         counter2 (volatile! 0)
         tester1 (fn [a] (vswap! counter1 + a))
         tester2 (fn [a] (vswap! counter2 + a))
         err (fn [x] (throw (ex-info (str x) {:x x})))]
     (is (nil? (remove-tap tester1)))
     (is (nil? (add-tap tester1)))
     (is (nil? (add-tap nil)))
     (is (nil? (add-tap err)))
     (tap> 2)
     (is (nil? (add-tap tester2)))
     (tap> 3)
     (tap> 101)
     (sleep 50) ;; the tap queue always races
     (is (= 106 @counter1))
     (is (= 104 @counter2))))


 (deftest removals
   (let [counter1 (volatile! 0)
         counter2 (volatile! 0)
         tester1 (fn [a] (vswap! counter1 + a))
         tester2 (fn [a] (vswap! counter2 + a))
         err (fn [x] (throw (ex-info (str x) {:x x})))]
     (is (nil? (remove-tap tester1)))
     (is (nil? (add-tap tester1)))
     (is (nil? (add-tap tester2)))
     (tap> 3)
     (sleep 100)
     (is (nil? (remove-tap tester1)))
     (tap> 5)
     (is (nil? (remove-tap tester2)))
     (sleep 50) ;; if we tap now, we can still see it in the testers
     (tap> 100)
     (sleep 50)
     (is (nil? (remove-tap nil)))
     (is (nil? (remove-tap tester2)))
     (is (= 3 @counter1))
     (is (= 8 @counter2)))))
