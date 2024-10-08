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
        String playerName = player.getName();
        String worldName = player.getWorld().getName();

        PyObject pyFunction = plugin.getInterpreter().get("on_player_join");

        if (pyFunction != null && pyFunction.isCallable()) {
            PyObject pyPlayerName = new PyString(playerName);
            PyObject pyWorldName = new PyString(worldName);

            pyFunction.__call__(pyPlayerName, pyWorldName);
        } else {
            plugin.getLogger().warning("No valid 'on_player_join' function found in the Python script.");
        }
    }
}