package org.imsglobal.caliper.validators;

public class TimeCheck {

    /**
     * Validate startedAtTime
     * @param startedAtTime
     * @return boolean true/false
     */
    public static boolean checkStartedAtTime(long startedAtTime) {
        //TODO refactor if times are changed to ISO-8601 format.
        return startedAtTime > 0;
    }

    /**
     * Event endedAtTime is an optional setting.  If both a startedAtTime and endedAtTime are specified
     * check that startedAtTime precedes endedAtTime.  If not return false.
     * @param startedAtTime
     * @param endedAtTime
     * @return boolean true/false.
     */
    public static boolean checkEndedAtTime(long startedAtTime, long endedAtTime) {
        if (checkStartedAtTime(startedAtTime)) {
            return endedAtTime <= startedAtTime;
        } else {
            return true;
        }
    }

    /**
     * Validate duration.
     * @param duration
     * @return boolean true/false
     */
    public static boolean checkDuration(String duration) {
        if (duration != null && !(duration.isEmpty())) {
            //TODO add ISO-8601 validation routine to this stubbed out method
            return true;
        } else {
            return true;
        }
    }
}