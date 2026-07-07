package me.jules.helpme;

import me.jules.helpme.commands.HandleCommand;
import me.jules.helpme.commands.HelpMeCommand;
import me.jules.helpme.config.ConfigManager;
import me.jules.helpme.manager.CooldownManager;
import me.jules.helpme.manager.TeleportManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class HelpMe extends JavaPlugin {
    private ConfigManager configManager;
    private CooldownManager cooldownManager;
    private TeleportManager teleportManager;

    @Override
    public void onEnable() {
        this.configManager = new ConfigManager(this);
        this.cooldownManager = new CooldownManager();
        this.teleportManager = new TeleportManager(this);

        Objects.requireNonNull(getCommand("helpme")).setExecutor(new HelpMeCommand(this));
        Objects.requireNonNull(getCommand("helpmehandle")).setExecutor(new HandleCommand(this));

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
