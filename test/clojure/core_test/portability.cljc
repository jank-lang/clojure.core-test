(ns clojure.core-test.portability)

(defmacro when-var-exists [var-sym & body]
  (let [cljs? (some? (:ns &env))
        exists? (boolean (if cljs?
                           ((resolve 'cljs.analyzer.api/resolve) &env var-sym)
                           (resolve var-sym)))]
    `(if ~exists?
       (do
         ~@body)
       (println "SKIP -" '~var-sym))))

(defn big-int? [n]
  (and (integer? n)
       (not (int? n))))
