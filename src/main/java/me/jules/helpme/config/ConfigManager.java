package me.jules.helpme.config;

import me.jules.helpme.HelpMe;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    private final HelpMe plugin;
    private FileConfiguration config;

    public ConfigManager(HelpMe plugin) {
        this.plugin = plugin;
        this.reloadConfig();
    }

    public void reloadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        this.config = plugin.getConfig();
    }

    public int getCooldown() {
        return config.getInt("cooldown", 60);
    }

    public String getMessage(String path) {
        return config.getString("messages." + path, "");
    }

    public String getHandleButtonText() {
        return config.getString("handleButton.text", "[Handle]");
    }

    public String getHandleButtonColor() {
        return config.getString("handleButton.color", "GREEN");
    }

    public boolean isHandleButtonUnderlined() {
        return config.getBoolean("handleButton.underlined", true);
    }

    public String getHandleButtonHover() {
        return config.getString("handleButton.hover", "Click to teleport to this player.");
    }
}
