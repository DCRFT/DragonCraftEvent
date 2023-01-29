package pl.dcrft.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.dcrft.DragonCraftEvent;
import pl.dcrft.Managers.DatabaseManager;
import pl.dcrft.Managers.PanelManager;
import pl.dcrft.Managers.SessionManager;

import java.sql.SQLException;
import java.sql.Statement;

public class PlayerJoinListener implements Listener {
    public static DragonCraftEvent plugin = DragonCraftEvent.getInstance();

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!p.isOp()) {

            Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
                DatabaseManager.openConnection();
                try {
                    DatabaseManager.openConnection();

                    Statement statement = DatabaseManager.connection.createStatement();

                    statement.executeUpdate("UPDATE staty_ogolem SET online='teraz' WHERE nick='" + p.getName() + "';");

                    statement.close();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }, 20L);
        }

        SessionManager newSession = new SessionManager(e.getPlayer());
        SessionManager.list.add(newSession);

        //AnimationUtil.playAnimation(p, LanguageManager.getMessageList("welcome.title"), LanguageManager.getMessage("welcome.subtitle"));
        new PanelManager().showRepeatingPanel(p);

    }

}
