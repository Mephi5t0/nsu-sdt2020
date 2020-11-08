(ns lab_3.task_3_2)

(defn lazy-filter [pred chankSize batches]
  (lazy-cat
    (->> (take chankSize batches)
         (map #(future (doall (filter pred %))))
         (doall)
         (mapcat deref))
    (lazy-filter pred chankSize (drop chankSize batches))))

(defn get-bathes [size coll]
  (take-while #(boolean (seq %))
              (map first
                   (iterate (fn [[_ rest]] (split-at size rest)) (split-at size coll)))))

(defn heavy-calc [sleep f x]
  (Thread/sleep sleep)
  (f x))

(defn -main [& _]
  (time (nth (filter #(heavy-calc 1 even? %) (range)) 1000))
  (time (nth (lazy-filter #(heavy-calc 1 even? %) 4 (get-bathes 200 (range))) 1000)))