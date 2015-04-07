package org.imsglobal.caliper.validators;

import com.google.common.base.Strings;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;

import java.util.IllegalFormatException;

import static com.google.common.base.Preconditions.checkArgument;

public class TimeValidator {

    /**
     * Constructor
     */
    public TimeValidator() {

    }

    /**
     * Check duration.
     * @param start
     * @param end
     * @param duration
     * @throws IllegalArgumentException
     */
    protected static void checkDuration(DateTime start, DateTime end, String duration)
            throws IllegalArgumentException {
        if (!(Strings.isNullOrEmpty(duration))) {
            checkArgument(checkDurationFormat(duration), "%s duration format cannot be parsed");
            /**
             checkArgument(duration.equals(new Interval(start, end).toDuration().toString()),
             "Interval between %s and %s does not equal duration %s ", start, end, duration);
             */
        }
    }

    /**
     * Check Event end time if Entity requires it.  If an end time is specified check to ensure that
     * the start time precedes the end time.  ValidatorResult.isValid defaults to false.
     * @param start
     * @param end
     * @throws IllegalArgumentException
     */
    protected static void checkEndTime(DateTime start, DateTime end) throws IllegalArgumentException {
        checkArgument(checkDateTime(end), "end time must be specified");
        if (checkDateTime(start)) {
            checkArgument(isBefore(start, end), "end time %s must follow specified start time %s", end, start);
        }
    }

    /**
     * Check Event start time.  If an end time is specified check to ensure that
     * the start time precedes the end time.  ValidatorResult.isValid defaults to false.
     * @param start
     * @param end
     * @throws IllegalArgumentException
     */
    protected static void checkStartTime(DateTime start, DateTime end) throws IllegalArgumentException {
        checkArgument(checkDateTime(start), "start time must be specified");
        if (checkDateTime(end)) {
            checkArgument(isBefore(start, end), "start time %s must precede specified end time %s", start, end);
        }
    }

    /**
     * Check that the time value is not null.
     * @param time
     * @return boolean true/false.
     */
    private static boolean checkDateTime(DateTime time) {
        return time != null;
    }

    /**
     * Parses the format PTa.bS
     * @param duration
     * @return boolean true/false.
     */
    private static boolean checkDurationFormat(String duration) {
        try {
            Duration.parse(duration);
            return true;
        } catch (IllegalFormatException ex) {
            return false;
        }
    }

    /**
     * Calculate the duration given an event's start and end time
     * @param startedAtTime
     * @param endedAtTime
     * @return
     */
    private static Duration getDuration(DateTime startedAtTime, DateTime endedAtTime) {
        return new Interval(startedAtTime, endedAtTime).toDuration();
    }

    /**
     * Event start times are required; Event end times are optional while Entity start and end times are both
     * typically optional.  However if both start and end times are specified check that the start time
     * precedes the end time.  The isBefore() method evaluates null instants to check against as now.
     * @param start
     * @param end
     * @return boolean true/false
     */
    private static boolean isBefore(DateTime start, DateTime end) {
        return start.isBefore(end);
    }
}