package dev._2lstudios.litecombat;

import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.litecombat.commands.LiteCombatCommandExecutor;
import dev._2lstudios.litecombat.listeners.EntityDamageByEntityListener;
import dev._2lstudios.litecombat.listeners.PlayerJoinListener;
import dev._2lstudios.litecombat.listeners.PlayerQuitListener;
import dev._2lstudios.litecombat.modules.KnockbackModule;
import dev._2lstudios.litecombat.modules.WeaponsModule;
import dev._2lstudios.litecombat.utils.ConfigurationUtils;

public class LiteCombat extends JavaPlugin {
    private ConfigurationUtils configurationUtils;
    private KnockbackModule knockbackModule;
    private WeaponsModule weaponsModule;

    public void reload() {
        final Configuration configuration = configurationUtils.get("%datafoler%/config.yml");

        knockbackModule.reload(configuration);
        weaponsModule.reload(configuration);
    }

    @Override
    public void onEnable() {
        configurationUtils = new ConfigurationUtils(this);
        configurationUtils.create("%datafolder%/config.yml", "config.yml");
        knockbackModule = new KnockbackModule();
        weaponsModule = new WeaponsModule();

        reload();

        final PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new EntityDamageByEntityListener(knockbackModule, weaponsModule), this);
        pluginManager.registerEvents(new PlayerJoinListener(weaponsModule), this);
        pluginManager.registerEvents(new PlayerQuitListener(weaponsModule), this);

        if (weaponsModule.isEnabled()) {
            for (final Player player : getServer().getOnlinePlayers()) {
                weaponsModule.applySpeed(player);
            }
        }

        getCommand("litecombat").setExecutor(new LiteCombatCommandExecutor(this));
    }

    @Override
    public void onDisable() {
        if (weaponsModule.isEnabled()) {
            for (final Player player : getServer().getOnlinePlayers()) {
                weaponsModule.resetSpeed(player);
            }
        }
    }
}