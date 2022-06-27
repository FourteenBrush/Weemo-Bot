package me.fourteendoggo.weemobot.commands;

import java.util.HashMap;
import java.util.Map;

public class CooldownManager {
    private final Map<Long, Cooldown> cooldownMap = new HashMap<>();

    public void addCooldown(long user, Cooldown cooldown) {
        cooldownMap.put(user, cooldown);
    }

    public void removeCooldown(long user) {
        cooldownMap.remove(user);
    }

    public boolean hasCooldown(long user) {
        return cooldownMap.containsKey(user);
    }

    public Cooldown getCooldown(long user) {
        return cooldownMap.get(user);
    }
}
