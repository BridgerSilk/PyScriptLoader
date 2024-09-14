package tk.bridgersilk.pyscriptloader;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.python.core.Py;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class PyScriptLoader extends JavaPlugin {

    private PythonInterpreter interpreter;

    @Override
    public void onEnable() {
        interpreter = new PythonInterpreter();

        // Expose the BroadcastUtils class methods to Python scripts
        exposeBroadcastUtils();

        // Register event listener
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);

        // Register command for reloading the script
        this.getCommand("pyscriptloader").setExecutor(new ReloadPythonCommand(this));

        // Load all Python scripts in the directory
        loadPythonScripts(null);

        getLogger().info("PyScriptLoader has been enabled!");
    }

    @Override
    public void onDisable() {
        if (interpreter != null) {
            interpreter.close();
        }
        getLogger().info("PyScriptLoader has been disabled!");
    }

    // Method to expose BroadcastUtils methods to Python
    private void exposeBroadcastUtils() {
        PyObject broadcastFunction = new PyObject() {
            @Override
            public PyObject __call__(PyObject[] args, String[] keywords) {
                if (args.length == 1) {
                    String message = args[0].toString();
                    BroadcastUtils.broadcastMessage(message);
                }
                return Py.None; // Use Py.None to indicate no return value
            }
        };
        interpreter.set("broadcastMessage", broadcastFunction);
    }

    // Method to get the PythonInterpreter instance
    public PythonInterpreter getInterpreter() {
        return interpreter;
    }

    // Method to load all Python scripts and handle errors
    public void loadPythonScripts(CommandSender sender) {
        File pluginDir = getDataFolder();
        if (!pluginDir.exists()) {
            pluginDir.mkdirs();
        }
        File[] pythonScripts = pluginDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".py"));
        if (pythonScripts != null && pythonScripts.length > 0) {
            for (File script : pythonScripts) {
                try {
                    getLogger().info("Loading Python script: " + script.getName());
                    executePythonScript(script);
                    sendFeedback(sender, "Successfully loaded script: " + script.getName());
                } catch (Exception e) {
                    sendFeedback(sender, "Failed to load Python script " + script.getName() + ": " + e.getMessage());
                }
            }
        } else {
            sendFeedback(sender, "No Python scripts found in the plugin directory.");
        }
    }

    // Execute a single Python script with error handling
    private void executePythonScript(File script) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(script))) {
            String scriptContent = readScript(reader);
            interpreter.exec(scriptContent);
        }
    }

    // Read the entire script from BufferedReader
    private String readScript(BufferedReader reader) throws IOException {
        StringBuilder script = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            script.append(line).append("\n");
        }
        return script.toString();
    }

    // Send feedback to the player or console
    private void sendFeedback(CommandSender sender, String message) {
        if (sender instanceof Player) {
            ((Player) sender).sendMessage(message);
        } else {
            getLogger().info(message);
        }
    }
}