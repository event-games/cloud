(ns common.alpha.game-test
  (:require
   [clojure.spec.alpha :as s]
   [clojure.spec.gen.alpha :as sgen]
   [clojure.spec.test.alpha :as stest]
   [clojure.test.check :as tc]
   [clojure.test.check.generators :as gen]
   [clojure.test.check.properties :as prop]
   [clojure.test.check.clojure-test :refer [defspec]]
   [clojure.test :as test :refer [is testing run-tests deftest]]
   [common.alpha.game :refer [mk-default-game-state]]))


(deftest mk-default-game-state-tests
  (testing "generates valid :g/game"
    (is (s/valid? :g/game
                  (mk-default-game-state
                   (gen/generate gen/uuid)
                   (gen/generate (s/gen :ev.g.u/create)))))))

(deftest all-specchecks
  (testing "running spec.test/check via stest/enumerate-namespace"
    (let [summary (-> #?(:clj (stest/enumerate-namespace 'common.alpha.game)
                         :cljs 'common.alpha.game)
                      (stest/check {:clojure.spec.test.check/opts {:num-tests 10}})
                      (stest/summarize-results))]
      (is (not (contains? summary :check-failed))))))

(deftest mk-default-game-state-speccheck
  (testing "running spec.test/check"
    (let [summary (-> (stest/check `mk-default-game-state
                                   {:clojure.spec.test.check/opts {:num-tests 10}})
                      (stest/summarize-results))]
      (is (not (contains? summary :check-failed))))))

(comment

  (run-tests)
  (all-specchecks)
  (mk-default-game-state-speccheck)

  (list (reduce #(assoc %1 (keyword (str %2)) %2) {} (range 0 100)))

  ;;
  )