package me.jules.helpme.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public void setCooldown(UUID uuid, int seconds) {
        cooldowns.put(uuid, System.currentTimeMillis() + (seconds * 1000L));
    }

    public long getRemainingCooldown(UUID uuid) {
        if (!cooldowns.containsKey(uuid)) {
            return 0;
        }
        long remaining = cooldowns.get(uuid) - System.currentTimeMillis();
        return Math.max(0, remaining / 1000);
    }

    public boolean isOnCooldown(UUID uuid) {
        return getRemainingCooldown(uuid) > 0;
    }
}
