package org.imsglobal.caliper.profiles;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;

public class ProfileUtils {

    /**
     * Calculate the duration given an event's start and end time
     * @param startedAtTime
     * @param endedAtTime
     * @return
     */
    public static Duration getDuration(DateTime startedAtTime, DateTime endedAtTime) {
        return new Interval(startedAtTime, endedAtTime).toDuration();
    }
}
