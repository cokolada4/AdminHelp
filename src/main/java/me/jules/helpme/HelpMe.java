package me.jules.helpme;

import me.jules.helpme.commands.HandleCommand;
import me.jules.helpme.commands.HelpMeCommand;
import me.jules.helpme.config.ConfigManager;
import me.jules.helpme.manager.CooldownManager;
import me.jules.helpme.manager.TeleportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HelpMe extends JavaPlugin {
    private ConfigManager configManager;
    private CooldownManager cooldownManager;
    private TeleportManager teleportManager;

    @Override
    public void onEnable() {
        this.configManager = new ConfigManager(this);
        this.cooldownManager = new CooldownManager();
        this.teleportManager = new TeleportManager(this);

        // Register commands programmatically as required by Paper plugins in 1.21.4
        HelpMeCommand helpMeExecutor = new HelpMeCommand(this);
        getServer().getCommandMap().register("helpme", new Command("helpme") {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                return helpMeExecutor.onCommand(sender, this, commandLabel, args);
            }

            @Override
            public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
                return helpMeExecutor.onTabComplete(sender, this, alias, args);
            }
        });

        HandleCommand handleExecutor = new HandleCommand(this);
        getServer().getCommandMap().register("helpme", new Command("helpmehandle") {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                if (!testPermission(sender)) return true;
                return handleExecutor.onCommand(sender, this, commandLabel, args);
            }

            @Override
            public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
                return handleExecutor.onTabComplete(sender, this, alias, args);
            }

            @Override
            public String getPermission() {
                return "helpme.handle";
            }
        });

        getLogger().info("HelpMe plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("HelpMe plugin disabled!");
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }

    public TeleportManager getTeleportManager() {
        return teleportManager;
    }
}
