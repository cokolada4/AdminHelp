package me.jules.helpme.commands;

import me.jules.helpme.HelpMe;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HandleCommand implements CommandExecutor {
    private final HelpMe plugin;

    public HandleCommand(HelpMe plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player admin)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        if (!admin.isOp()) {
            // Should be handled by permissions but double check
            return true;
        }

        if (args.length < 1) {
            return true;
        }

        String targetName = args[0];
        plugin.getTeleportManager().handleRequest(admin, targetName);

        return true;
    }
}
