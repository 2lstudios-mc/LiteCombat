package dev._2lstudios.litecombat.modules;

import java.util.Collection;
import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WeaponsModule implements Module {
    private final Collection<Material> axes = new HashSet<>();
    private final Collection<Material> spades = new HashSet<>();

    private boolean enabled = true;
    private boolean oldDamage = true;
    private boolean disableSweep = true;

    private final float speed = 256;

    public WeaponsModule() {
        this.axes.add(Material.getMaterial("DIAMOND_AXE"));
        this.axes.add(Material.getMaterial("IRON_AXE"));
        this.axes.add(Material.getMaterial("STONE_AXE"));
        this.axes.add(Material.getMaterial("GOLD_AXE"));
        this.axes.add(Material.getMaterial("WOOD_AXE"));

        this.spades.add(Material.getMaterial("DIAMOND_SPADE"));
        this.spades.add(Material.getMaterial("IRON_SPADE"));
        this.spades.add(Material.getMaterial("STONE_SPADE"));
        this.spades.add(Material.getMaterial("GOLD_SPADE"));
        this.spades.add(Material.getMaterial("WOOD_SPADE"));
    }

    public void reload(final Configuration configuration) {
        final ConfigurationSection section = configuration.getConfigurationSection("weapons");

        if (section != null) {
            enabled = section.getBoolean("enabled", enabled);
            oldDamage = section.getBoolean("old-damage", oldDamage);
            disableSweep = section.getBoolean("disable-sweep", disableSweep);
        }
    }

    public double getOldDamage(final ItemStack itemStack, final double damage) {
        if (itemStack != null) {
            final Material material = itemStack.getType();

            if (this.axes.contains(material)) {
                return damage - 3.0D;
            } else if (this.spades.contains(material)) {
                return damage - 0.5D;
            }
        }

        return damage;
    }

    public double getOldArrowDamage(final double damage) {
        return damage / 2;
    }

    public void applySpeed(final Player player) {
        final AttributeInstance attributeInstance = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);

        if (attributeInstance.getBaseValue() != speed) {
            attributeInstance.setBaseValue(speed);
        }
    }

    public void resetSpeed(final Player player) {
        final AttributeInstance attributeInstance = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        final double defaultValue = attributeInstance.getDefaultValue();

        if (attributeInstance.getBaseValue() != defaultValue) {
            attributeInstance.setBaseValue(defaultValue);
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isOldDamage() {
        return oldDamage;
    }

    public boolean isDisableSweep() {
        return disableSweep;
    }
}
