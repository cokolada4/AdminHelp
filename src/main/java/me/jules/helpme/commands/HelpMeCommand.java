package me.jules.helpme.commands;

import me.jules.helpme.HelpMe;
import me.jules.helpme.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelpMeCommand implements CommandExecutor, org.bukkit.command.TabCompleter {
    private final HelpMe plugin;

    public HelpMeCommand(HelpMe plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        if (plugin.getCooldownManager().isOnCooldown(player.getUniqueId())) {
            long remaining = plugin.getCooldownManager().getRemainingCooldown(player.getUniqueId());
            MessageUtil.sendMessage(player, plugin.getConfigManager().getMessage("cooldown")
                    .replace("<seconds>", String.valueOf(remaining)));
            return true;
        }

        MessageUtil.sendMessage(player, plugin.getConfigManager().getMessage("sent"));

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.isOp()) {
                MessageUtil.sendHelpRequest(onlinePlayer, player, plugin);
            }
        }

        plugin.getCooldownManager().setCooldown(player.getUniqueId(), plugin.getConfigManager().getCooldown());
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return Collections.emptyList();
    }
}
