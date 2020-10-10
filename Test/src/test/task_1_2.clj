(ns test.task_1_2)

(defn get-pair [sym alph res]
  (if (= 0 (count alph))
    res
    (if (.contains (str sym) (str (first alph)))
      (recur sym (rest alph) res)
      (recur sym (rest alph) (cons (cons sym (list (first alph))) res)))))

(defn mix [alph tmp res]
  (if (= 0 (count tmp))
    res
    (recur alph (rest tmp) (concat (get-pair (first tmp) alph `()) res))))

(defn repeat-n-times [alph n res]
  (if (= 1 n)
    res
    (recur alph (dec n) (mix alph res `()))))

(defn remove-braces-for-element [res n]
  (if (= n 2)
    res
    (recur (concat (first res) (rest res)) (dec n))))

(defn remove-braces [permuts n res]
  (if (= 0 (count permuts))
    res
    (recur (rest permuts) n (concat (list (remove-braces-for-element (first permuts) n)) res))))

(defn my-permut [alph n]
  (remove-braces (repeat-n-times alph n alph) n `()))


(defn -main [& _]
  (prn (my-permut `(:a (:b 1) "c") 3)))