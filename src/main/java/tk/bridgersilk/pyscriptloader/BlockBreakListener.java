package tk.bridgersilk.pyscriptloader;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.python.core.PyObject;
import org.python.core.PyString;

public class BlockBreakListener implements Listener {
    private final PyScriptLoader plugin;

    public BlockBreakListener(PyScriptLoader plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        String worldName = player.getWorld().getName();
        String blockType = event.getBlock().getType().name();
        String blockLoc = event.getBlock().getLocation().toString();

        PyObject pyFunction = plugin.getInterpreter().get("on_block_break");

        if (pyFunction != null && pyFunction.isCallable()) {
            PyObject pyPlayerName = new PyString(playerName);
            PyObject pyWorldName = new PyString(worldName);
            PyObject pyBlockType = new PyString(blockType);
            PyObject pyBlockLoc = new PyString(blockLoc);

            pyFunction.__call__(pyPlayerName, pyWorldName, pyBlockType, pyBlockLoc);
        } else {
            plugin.getLogger().warning("No valid 'on_player_join' function found in the Python script.");
        }
    }
}
