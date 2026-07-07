package me.jules.helpme.manager;

import me.jules.helpme.HelpMe;
import me.jules.helpme.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TeleportManager {
    private final HelpMe plugin;

    public TeleportManager(HelpMe plugin) {
        this.plugin = plugin;
    }

    public void handleRequest(Player admin, String targetPlayerName) {
        Player target = Bukkit.getPlayerExact(targetPlayerName);

        if (target == null) {
            MessageUtil.sendMessage(admin, plugin.getConfigManager().getMessage("offline"));
            return;
        }

        admin.teleport(target.getLocation());

        MessageUtil.sendMessage(admin, plugin.getConfigManager().getMessage("adminTeleport")
                .replace("<player>", target.getName()));
        MessageUtil.sendMessage(target, plugin.getConfigManager().getMessage("handled"));
    }
}
