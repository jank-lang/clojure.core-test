(ns clojure.core-test.portability)

(defn big-int? [n]
  (and (integer? n)
       (not (int? n))))
