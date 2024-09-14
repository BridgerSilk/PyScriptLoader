package tk.bridgersilk.pyscriptloader;

import org.bukkit.Bukkit;

public class BroadcastUtils {

    // Method to broadcast a message to all players
    public static void broadcastMessage(String message) {
        Bukkit.broadcastMessage(message);
    }
}