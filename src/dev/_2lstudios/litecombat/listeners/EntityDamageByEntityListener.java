package dev._2lstudios.litecombat.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import dev._2lstudios.litecombat.modules.KnockbackModule;
import dev._2lstudios.litecombat.modules.WeaponsModule;

public class EntityDamageByEntityListener implements Listener {
    private final KnockbackModule knockbackModule;
    private final WeaponsModule weaponsModule;

    public EntityDamageByEntityListener(final KnockbackModule knockbackModule, final WeaponsModule weaponsModule) {
        this.knockbackModule = knockbackModule;
        this.weaponsModule = weaponsModule;
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityDamageByEntityLow(final EntityDamageByEntityEvent event) {
        final EntityDamageEvent.DamageCause damageCause = event.getCause();

        if (weaponsModule.isEnabled() && weaponsModule.isDisableSweep()
                && damageCause == EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onEntityDamageByEntityHigh(final EntityDamageByEntityEvent event) {
        final double damage = event.getDamage();

        if (damage <= 0.0D) {
            return;
        }

        final Entity hitEntity = event.getEntity();

        if (!(hitEntity instanceof LivingEntity)) {
            return;
        }

        final LivingEntity hitLivingEntity = (LivingEntity) hitEntity;
        final Entity damager = event.getDamager();

        if (weaponsModule.isEnabled()) {
            if (weaponsModule.isOldDamage()) {
                if (damager instanceof Arrow) {
                    event.setDamage(weaponsModule.getOldArrowDamage(damage));
                } else if (damager instanceof HumanEntity) {
                    final PlayerInventory inventory = ((HumanEntity) damager).getInventory();
                    final ItemStack heldItem = inventory.getItem(inventory.getHeldItemSlot());

                    if (heldItem != null) {
                        event.setDamage(weaponsModule.getOldDamage(heldItem, damage));
                    }
                }
            }
        }

        if (knockbackModule.isEnabled()) {
            if (damager instanceof Projectile && !(damager instanceof Arrow)) {
                if (knockbackModule.isEnabled() && knockbackModule.isRestoreProjectiles()) {
                    hitLivingEntity.damage(0.01D);
                    hitLivingEntity.setVelocity(knockbackModule.getVelocity(hitLivingEntity, damager));
                }
            } else if (hitLivingEntity instanceof Player) {
                if (knockbackModule.isPlayers()) {
                    hitLivingEntity.setVelocity(knockbackModule.getVelocity(hitLivingEntity, damager));
                }
            } else if (knockbackModule.isEntities()) {
                hitLivingEntity.setVelocity(knockbackModule.getVelocity(hitLivingEntity, damager));
            }
        }
    }
}