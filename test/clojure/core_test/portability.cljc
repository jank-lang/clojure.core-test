(ns clojure.core-test.portability)

(defmacro when-var-exists [var-sym & body]
  `(let [s# '~var-sym
         v# (resolve s#)]
     (if v#
       (do
         ~@body)
       (println "SKIP -" s#))))

(defn big-int? [n]
  (and (integer? n)
       (not (int? n))))
