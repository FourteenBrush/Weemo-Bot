package me.fourteendoggo.weemobot.commands.cooldown;

import java.util.HashMap;
import java.util.Map;

public class CooldownManager {
    private final Map<String, Cooldown> cooldownMap;

    public CooldownManager() {
        cooldownMap = new HashMap<>();
    }

    public void addCooldown(String userId, Cooldown cooldown) {
        cooldownMap.put(userId, cooldown);
    }

    public void removeCooldown(String userId) {
        cooldownMap.remove(userId);
    }

    public Cooldown getCooldown(String userId) {
        return cooldownMap.get(userId);
    }

    public boolean hasCooldown(String userId) {
        Cooldown cooldown = cooldownMap.get(userId);
        return cooldown != null && !cooldown.hasExpired();
    }
}
