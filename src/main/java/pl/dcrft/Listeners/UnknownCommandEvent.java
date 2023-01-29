package pl.dcrft.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import pl.dcrft.DragonCraftEvent;
import pl.dcrft.Managers.MessageManager;

public class UnknownCommandEvent implements Listener {

    private static DragonCraftEvent plugin = DragonCraftEvent.getInstance();
    //F***ING M$

    @EventHandler
    public void preProcess(PlayerCommandPreprocessEvent e) {
        String command = e.getMessage().substring(1).split(" ")[0];

        if (commandExists(command)) return;
        
        e.setCancelled(true);
        MessageManager.sendPrefixedMessage(e.getPlayer(), "unknown-command");
    }

    @EventHandler
    public void preProcess(ServerCommandEvent e) {
        String command = e.getCommand().split(" ")[0];

        if (commandExists(command)) return;
        
        e.setCancelled(true);
        MessageManager.sendPrefixedMessage(e.getSender(), "unknown-command");

    }

    public boolean commandExists(String cmd) {
        return Bukkit.getHelpMap().getHelpTopic("/" + cmd) != null;
    }
}
