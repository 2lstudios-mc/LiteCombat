package dev._2lstudios.litecombat;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.litecombat.listeners.EntityDamageByEntityListener;
import dev._2lstudios.litecombat.listeners.PlayerJoinListener;
import dev._2lstudios.litecombat.listeners.PlayerQuitListener;
import dev._2lstudios.litecombat.modules.KnockbackModule;
import dev._2lstudios.litecombat.modules.WeaponsModule;
import dev._2lstudios.litecombat.utils.ConfigurationUtils;

public class LiteCombat extends JavaPlugin {
    @Override
    public void onEnable() {
        final ConfigurationUtils configurationUtils = new ConfigurationUtils(this);

        configurationUtils.create("%datafolder%/%config.yml", "config.yml");

        final Configuration configuration = configurationUtils.get("%datafoler%/config.yml");
        final KnockbackModule knockbackModule = new KnockbackModule();
        final WeaponsModule weaponsModule = new WeaponsModule();

        knockbackModule.reload(configuration);
        weaponsModule.reload(configuration);

        final PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new EntityDamageByEntityListener(knockbackModule, weaponsModule), this);
        pluginManager.registerEvents(new PlayerJoinListener(weaponsModule), this);
        pluginManager.registerEvents(new PlayerQuitListener(weaponsModule), this);
    }
}