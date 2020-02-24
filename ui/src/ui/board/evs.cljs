(ns ui.board.evs
  (:require [re-frame.core :as rf]
            [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]
            [goog.dom]
            [ui.board.core]))


(rf/reg-event-fx
 ::toggle-key
 (fn-traced [{:keys [db]} [_ ea]]
            (let []
              {:db (update db :ui.db.board/selected-key (fn [k]
                                                          (if (= k ea)
                                                            nil
                                                            ea)
                                                          ))
               :dispatch [::toggle-selected-cell nil]
               })))