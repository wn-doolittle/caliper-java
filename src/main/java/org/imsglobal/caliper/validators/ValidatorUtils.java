package org.imsglobal.caliper.validators;

import org.joda.time.DateTime;

public class ValidatorUtils {

    /**
     * Validate object type equality against a provided class.  Determines if the specified
     * Object is assignment-compatible with the object represented by this Class. This method
     * is the dynamic equivalent of the Java language instanceof operator. The method returns
     * true if the specified Object argument is non-null and can be cast to the reference type
     * represented by this Class object without raising a ClassCastException. It returns false
     * otherwise.
     * @param object
     * @param type
     * @return boolean true/false
     */
    public static boolean isOfType(Object object, Class<?> type) {
        return type.isInstance(object);
    }

    /**
     * Event endedAtTime is most cases an optional setting.  However if both a startedAtTime and endedAtTime
     * are specified check that startedAtTime precedes endedAtTime.
     * @param startedAtTime
     * @param endedAtTime
     * @return boolean true/false.
     */
    public static boolean checkStartEndTimes(DateTime startedAtTime, DateTime endedAtTime) {
        return startedAtTime.isBefore(endedAtTime);
    }
}