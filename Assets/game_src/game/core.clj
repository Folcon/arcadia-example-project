(ns game-src.game.core
  (:require [arcadia.core :as arc])
  (:import (UnityEngine GameObject)))

(defn log-name [obj role-key]
  (arc/log (.name obj)))

(def main-object (atom nil))

(defn create-main []
  (swap! main-object (fn [n] (new UnityEngine.GameObject "Main"))))

(defn hook-main []
  (arc/hook+
    @main-object
    :start
    :log-name
    ;; in log-name `obj` will be the `the-object`, `role-key` will be `:log-name`
    #'log-name))

(comment
  ;; Make sure you run this while unity is already running.
  ;; For programmatic stuff like this you might not want to modify your scene.
  ;; To be honest it depends on your coding style =)...
  (create-main)

  (hook-main))