# arcadia-example-project
An example project to help someone kickstart their exploration into Arcadia.

Uses some small adjustments to make it work, these will hopefully be merged in to the main repos of their respected projects:
- Arcadia:
  - Original Project: https://github.com/arcadia-unity/Arcadia
  - Fork: https://github.com/Folcon/Arcadia
- tools.cursive-arcadia-repl:
  - Original Project: https://github.com/eponai/tools.cursive-arcadia-repl
  - Fork: https://github.com/Folcon/tools.cursive-arcadia-repl

# Getting Started
1. Clone the project using `git clone --recursive https://github.com/Folcon/arcadia-example-project.git your-game-name`
   1. Use Unity Hub to add your game folder and open it in Unity.
   2. In Intellij with Cursive, go to File->Open and then open the game folder.
   3. File->Project Structure, select Modules. You might want to rename `arcadia-example-project` to your game name.
   4. Remove `tools.cursive-arcadia-repl` from modules then add it and select import, then select the `tools.cursive-arcadia-repl` folder. Import it as a leiningen project.
   5. Create a new run configuration based on leiningen and name it `Start Arcadia REPL`. Select `tools.cursive-arcadia-repl` as the module and then put `run` into parameters and `+dev` into profiles.
   6. Create a new run configuration based on the clojure remote repl and name it `RREPL`. Select `tools.cursive-arcadia-repl` as the module, `nrepl` as the connection type and host is `localhost` with port `7888`.
2. Start Unity and Play the scene.
3. Boot run configuration `Start Arcadia REPL`
4. Boot run configuration `RREPL`
   1. If you're on windows and are using wsl2 then make sure you have your ports open. A [script](https://gist.github.com/daehahn/497fa04c0156b1a762c70ff3f9f7edae) if you need some help =)... Just add the appropriate ports you want open into it.
5. Navigate to `Assets/game_src/game/core.clj`
6. Use SHIFT-ALT-L or `Load File in REPL`, accessible from the repl menu by right-clicking and looking for the REPL option.
   This should display `Loading Assets/game_src/game/core.clj...`
7. Bring your cursor to `(create-main)` within the comment block, specifically it should be where the pipe is:
   ```clojure
   (comment
     ;; Make sure you run this while unity is already running.
     ;; For programmatic stuff like this you might not want to modify your scene.
     ;; To be honest it depends on your coding style =)...
     (create-main)|

     (hook-main))
   ```
8. Use SHIFT-ALT-P or `Send '(create-main)' to REPL`, accessible from the repl menu by right-clicking and looking for the REPL option.
   This should display in your REPL:
   ```clojure
   (create-main)
   => #<Main (UnityEngine.GameObject)>
   ```
   In Unity you should see a new GameObject called `Main` in your hierarchy. Selecting it will show it's a simple GameObject.
9. Do it again with `(hook-main)` which should display in your REPL:
   ```clojure
   (hook-main)
   => #'game-src.game.core/log-name
   ```
   In Unity you should see the `Main` GameObject now has two new components:
   `Arcadia State (Script)` and `Start Hook (Script)`, with the `Start Hook (Script)` containing a reference to `:log-name` pointing to `#'game-src.game.core/log-name`, which is indicating which clojure function within what namespace it's pointing to.
10. Stopping Play within Unity should make the `Main` GameObject disappear, so you'll need to reload and invoke your code in the repl to create it again.
11. Happy Hacking!
