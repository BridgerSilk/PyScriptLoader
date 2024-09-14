package tk.bridgersilk.pyscriptloader;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.python.core.PyObject;
import org.python.core.PyString;

public class PlayerJoinListener implements Listener {
    private final PyScriptLoader plugin;

    public PlayerJoinListener(PyScriptLoader plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Call the Python function on player join
        PyObject pyFunction = plugin.getInterpreter().get("on_player_join");

        if (pyFunction != null && pyFunction.isCallable()) {
            // Prepare the player name as a Python-compatible argument (PyString)
            PyObject pyPlayerName = new PyString(player.getName());

            // Call the Python function with the player's name
            pyFunction.__call__(pyPlayerName);
        } else {
            plugin.getLogger().warning("No valid 'on_player_join' function found in the Python script.");
        }
    }
}