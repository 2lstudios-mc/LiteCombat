package dev._2lstudios.litecombat.modules;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class KnockbackModule implements Module {
    private boolean enabled = true;
    private boolean entities = true;
    private boolean players = true;
    private boolean restoreProjectiles = true;

    private float multiplier = 1.0f;

    public void reload(final Configuration configuration) {
        final ConfigurationSection section = configuration.getConfigurationSection("knockback");

        if (section != null) {
            enabled = section.getBoolean("enabled", enabled);
            entities = section.getBoolean("entities", entities);
            players = section.getBoolean("players", players);
            restoreProjectiles = section.getBoolean("restore-projectiles", restoreProjectiles);
        }
    }

    public Vector getVelocity(final Entity damaged, final Entity damager) {
        final Vector velocity = damager.getLocation().getDirection();

        return velocity.multiply(multiplier);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isEntities() {
        return entities;
    }

    public boolean isPlayers() {
        return players;
    }

    public boolean isRestoreProjectiles() {
        return restoreProjectiles;
    }

    public float getMultiplier() {
        return multiplier;
    }
}
