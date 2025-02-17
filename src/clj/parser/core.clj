(ns parser.core
  (:import me.ivan.Parser)
  (:use criterium.core)
  (:require
   [jsonista.core :as json]
   [clojure.java.io :as io]))


(set! *warn-on-reflection* true)


#_
(defn parse [src]
  (with-open [in (-> src io/reader)]
    (-> (new Parser in)
        (.parse))))

(defn parse2 [^String content]
  (-> (new Parser content)
      (.parse)))

(defn parse3 [^java.io.File file len]
  (-> (new Parser file)
      (.parse)))

(comment

  ;; file
  (quick-bench
      (parse3 (io/file "100mb.json") 8192))

  ;; file
  (quick-bench
      (json/read-value (io/file "100mb.json")))

  (def content
    (slurp "data2.json"))

  (def content100mb
    (slurp "100mb.json"))

  ;; string
  (quick-bench
      (parse2 content))

  (quick-bench
      (json/read-value content))


  (json/write-value (io/file "data2.json")
                    (vec
                     (for [x (range 1000)]
                       [true false "hello"])))




  )
