package dev._2lstudios.litecombat.modules;

import java.util.Collection;
import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WeaponsModule extends Module {
    private Collection<Material> axes = new HashSet<>();
    private Collection<Material> spades = new HashSet<>();

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
}
