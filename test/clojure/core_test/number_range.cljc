(ns clojure.core-test.number-range)

(def MAX-INT #?(:clj Long/MAX_VALUE
                :cljs js/Number.MAX_SAFE_INTEGER
                :default 0x7FFFFFFFFFFFFFFF))

(def MIN-INT #?(:clj Long/MIN_VALUE
                :cljs js/Number.MIN_SAFE_INTEGER
                :default 0x8000000000000000))

(def ALL-ONES-INT #?(:cljs 0xFFFFFFFF
                     :default -1))

(def ALL-ZEROS 0)

(def FULL-WIDTH-CHECKER-POS #?(:cljs 0x55555555
                               :default 0x5555555555555555))

(def FULL-WIDTH-CHECKER-NEG #?(:cljs 0xAAAAAAAA
                               :default -0x5555555555555556))

(def MAX-DOUBLE #?(:clj Double/MAX_VALUE
                   :cljs js/Number.MAX_VALUE
                   :default 1.7976931348623157e+308))

(def MIN-DOUBLE #?(:clj Double/MIN_VALUE
                   :cljs js/Number.MIN_VALUE
                   :default 4.9e-324))

