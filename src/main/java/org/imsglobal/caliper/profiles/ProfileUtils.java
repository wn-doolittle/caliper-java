package org.imsglobal.caliper.profiles;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;

import javax.annotation.Nonnull;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ProfileUtils {

    /**
     * Get action localized value from resource bundle.
     * @param key
     * @return action string
     */
    public static String getLocalizedAction(@Nonnull String key) throws MissingResourceException {
        return ResourceBundle.getBundle("actions").getString(key);
    }

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
