package dev._2lstudios.litecombat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev._2lstudios.litecombat.LiteCombat;
import net.md_5.bungee.api.ChatColor;

public class LiteCombatCommandExecutor implements CommandExecutor {
    private final LiteCombat liteCombat;

    public LiteCombatCommandExecutor(LiteCombat liteCombat) {
        this.liteCombat = liteCombat;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label,
            final String[] args) {
        liteCombat.reload();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aLiteCombat was successfully reloaded!"));

        return true;
    }
}
