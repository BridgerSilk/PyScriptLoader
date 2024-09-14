package tk.bridgersilk.pyscriptloader;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadPythonCommand implements CommandExecutor {

    private final PyScriptLoader plugin;

    public ReloadPythonCommand(PyScriptLoader plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("pyscriptloader")) {
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                plugin.loadPythonScripts(sender);
                return true;
            }
        }
        return false;
    }
}