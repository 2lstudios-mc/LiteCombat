package dev._2lstudios.litecombat.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import dev._2lstudios.litecombat.modules.WeaponsModule;

public class PlayerQuitListener implements Listener {
  private final WeaponsModule weaponsModule;

  public PlayerQuitListener(final WeaponsModule weaponsModule) {
    this.weaponsModule = weaponsModule;
  }

  @EventHandler
  public void onPlayerJoin(final PlayerQuitEvent event) {
    weaponsModule.applySpeed(event.getPlayer());
  }
}
