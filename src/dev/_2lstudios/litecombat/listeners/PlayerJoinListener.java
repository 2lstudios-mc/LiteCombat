package dev._2lstudios.litecombat.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dev._2lstudios.litecombat.modules.WeaponsModule;

public class PlayerJoinListener implements Listener {
    private final WeaponsModule weaponsModule;

    public PlayerJoinListener(final WeaponsModule weaponsModule) {
        this.weaponsModule = weaponsModule;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        if (weaponsModule.isEnabled()) {
            weaponsModule.applySpeed(event.getPlayer());
        }
    }
}
