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

(defn main [& _]
  ;; A simple primitive to easily test that exporting your game actually does things in clojure-land
  ;; To make this work, using the Arcadia menu: 1) AOT Compile your game 2) Export it 3) Build your game in Unity
  (arc/create-primitive :cube "REPLACE THIS"))

(comment
  ;; Make sure you run this while unity is already running.
  ;; For programmatic stuff like this you might not want to modify your scene.
  ;; To be honest it depends on your coding style =)...
  (create-main)

  (hook-main)

  ;; Eval this if you're having issues with files syncing in the REPL
  (load-file "Assets/game_src/game/core.clj")

  ;; Run this hook once to add your setup function to the Main Camera in your scene,
  ;;   that way your code should launch when exported.
  (arc/hook+ (arc/object-named "Main Camera") :start :main #'main))
