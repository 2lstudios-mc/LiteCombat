package dev._2lstudios.litecombat.modules;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class VelocityModule extends Module {
    public Vector getVelocity(final Entity damaged, final Entity damager) {
        final Vector velocity = damager.getLocation().getDirection();

        return velocity.multiply(1.0f);
    }
}
