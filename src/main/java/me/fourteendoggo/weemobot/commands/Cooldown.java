package me.fourteendoggo.weemobot.commands;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Cooldown {
    private final Instant end;

    public Cooldown(Duration duration) {
        this.end = Instant.now().plus(duration);
    }

    public boolean isExpired() {
        return end.isBefore(Instant.now());
    }

    public Duration getTimeLeft() {
        return Duration.between(Instant.now(), end).plus(Duration.ofMillis(15));
    }

    public long getTimeLeft(TimeUnit unit) {
        return TimeUnit.MICROSECONDS.convert(getTimeLeft().toMillis(), unit);
    }
}
