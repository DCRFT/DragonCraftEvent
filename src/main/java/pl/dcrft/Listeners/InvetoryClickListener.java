package pl.dcrft.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pl.dcrft.DragonCraftEvent;
import pl.dcrft.Managers.LanguageManager;
import pl.dcrft.Managers.Profile.ProfileManager;
import pl.dcrft.Managers.Profile.ProfileType;

public class InvetoryClickListener implements Listener {
    public static DragonCraftEvent plugin = DragonCraftEvent.getInstance();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        String title = e.getView().getTitle();

        Player p = (Player) e.getWhoClicked();

       if (title.contains(LanguageManager.getMessage("profile.title"))) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
                switch (e.getCurrentItem().getType()) {
                    case IRON_PICKAXE:
                        ProfileManager.showProfile(p, title.replace(LanguageManager.getMessage("profile.title"), ""), ProfileType.SURVIVAL);
                        return;
                    case GRASS_BLOCK:
                        ProfileManager.showProfile(p, title.replace(LanguageManager.getMessage("profile.title"), ""), ProfileType.SKYBLOCK);
                }
            }

        }
    }
}
