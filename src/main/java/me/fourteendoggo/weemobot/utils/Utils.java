package me.fourteendoggo.weemobot.utils;

import java.time.Duration;

public class Utils {

    private Utils() {}

    public static String millisToReadable(long millis) {
        Duration duration = Duration.ofMillis(millis);
        String output = "";
        long time = duration.toDays();
        if (time > 0) { // days
            output += getSingularOrMultiple(time, "day");
        }
        time = duration.toHoursPart();
        if (time > 0) { // hours
            output += getSingularOrMultiple(time, "hour");
        }
        time = duration.toMinutesPart();
        if (time > 0) { // minutes
            output += getSingularOrMultiple(time, "minute");
        }
        time = duration.toSecondsPart();
        if (time > 0) { // seconds
            output += getSingularOrMultiple(time, "second");
        }
        return output;
    }

    private static String getSingularOrMultiple(long amount, String ofWhat) {
        return amount > 1 ? String.format("%d %s", amount, ofWhat + "s") : String.format("%d %s", amount, ofWhat);
    }
}
