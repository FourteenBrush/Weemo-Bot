package me.fourteendoggo.weemobot.commands.cooldown;

import java.util.concurrent.TimeUnit;

public class Cooldown {
    private final long end;

    public Cooldown(long duration, TimeUnit unit) {
        end = System.currentTimeMillis() + unit.toMillis(duration);
    }

    public long getTimeLeftMillis() {
        return end - System.currentTimeMillis();
    }

    public long getTimeLeft(TimeUnit unit) {
        return TimeUnit.MILLISECONDS.convert(getTimeLeftMillis(), unit);
    }

    public boolean hasExpired() {
        return getTimeLeft(TimeUnit.SECONDS) <= 0;
    }
}
