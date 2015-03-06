package org.imsglobal.caliper.validators.events;

import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.validators.ValidatorResult;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;

import java.util.IllegalFormatException;

public class EventValidatorUtils {
    private static String context;

    // Constructor
    private EventValidatorUtils(String context) {
        this.context = context;
    }

    /**
     * Static factory method that sets a "simple name" event context for static EventValidatorUtils methods.
     * @return a new instance of EventValidatorUtils.
     */
    protected static EventValidatorUtils context(String context) {
        return new EventValidatorUtils(context);
    }

    /**
     * Check Id.
     * @param id
     * @return Validation result
     */
    protected static ValidatorResult validateId(String id) {
        ValidatorResult result = new ValidatorResult();

        if (id != null) {
            result.setIsValid(true);
        } else {
            String violation = buildMessage(context, "id must be specified");
            result.errorMessage().appendViolation(violation);
        }

        return result;
    }

    /**
     * Check the Event context URI.
     * @param uri
     * @param constant
     * @return Validation result
     */
    protected static ValidatorResult validateContextURI(String uri, Event.Context constant) {
        ValidatorResult result = new ValidatorResult();

        if (isEqual(uri, constant.uri())) {
            result.setIsValid(true);
        } else {
            String violation = buildMessage(context, "context URI must be specified");
            result.errorMessage().appendViolation(violation);
        }

        return result;
    }

    /**
     * Check the Event type URI.
     * @param uri
     * @param constant
     * @return Validation result
     */
    protected static ValidatorResult validateTypeURI(String uri, Event.Type constant) {
        ValidatorResult result = new ValidatorResult();

        if (isEqual(uri, constant.uri())) {
            result.setIsValid(true);
        } else {
            String violation = buildMessage(context, "type URI must be specified");
            result.errorMessage().appendViolation(violation);
        }

        return result;
    }

    /**
     * Check actor
     * @param agent
     * @param type
     * @return Validation result
     */
    protected static ValidatorResult validateType(Agent agent, Class<?> type) {
        ValidatorResult result = new ValidatorResult();

        if (isOfType(agent, type)) {
            result.setIsValid(true);
        } else {
            String violation = buildMessage(context, "actor must be of type " + type.getSimpleName());
            result.errorMessage().appendViolation(violation);
        }

        return result;
    }

    /**
     * Check object.
     * @param object
     * @param type
     * @return Validation result
     */
    protected static ValidatorResult validateType(Object object, Class<?> type) {
        ValidatorResult result = new ValidatorResult();

        if (isOfType(object, type)) {
            result.setIsValid(true);
        } else {
            String violation = buildMessage(context, "object must be of type " + type.getSimpleName());
            result.errorMessage().appendViolation(violation);
        }

        return result;
    }

    /**
     * Check target.
     * @param target
     * @param type
     * @return Validation result
     */
    protected static ValidatorResult validateType(Targetable target, Class<?> type) {
        ValidatorResult result = new ValidatorResult();

        if (isOfType(target, type)) {
            result.setIsValid(true);
        } else {
            String violation = buildMessage(context, "target must be of type " + type.getSimpleName());
            result.errorMessage().appendViolation(violation);
        }

        return result;
    }

    /**
     * Check generated.
     * @param generated
     * @param type
     * @return Validation result
     */
    protected static ValidatorResult validateType(Generatable generated, Class<?> type) {
        ValidatorResult result = new ValidatorResult();

        if (isOfType(generated, type)) {
            result.setIsValid(true);
        } else {
            String violation = buildMessage(context, "generated object must be of type " + type.getSimpleName());
            result.errorMessage().appendViolation(violation);
        }

        return result;
    }

    /**
     * Check Event start time.  If an end time is specified check to ensure that
     * the start time precedes the end time.  ValidatorResult.isValid defaults to false.
     * @param start
     * @param end
     * @return Validation result
     */
    protected static ValidatorResult validateStartTime(DateTime start, DateTime end) {
        ValidatorResult result = new ValidatorResult();
        String violation = null;

        if (checkDateTime(start)) {
            result.setIsValid(true);

            if (checkDateTime(end) && !checkDateTime(start, end)) {
                result.setIsValid(false);
                violation = buildMessage(context, "startedAtTime must precede endedAtTime");
            }
        } else {
            violation = buildMessage(context, "startedAtTime must be specified");
        }

        if (!result.isValid()) {
            result.errorMessage().appendViolation(violation);
        }

        return result;
    }

    /**
     * Check Event end time if Entity requires it.  If an end time is specified check to ensure that
     * the start time precedes the end time.  ValidatorResult.isValid defaults to false.
     * @param start
     * @param end
     * @return Validation result
     */
    protected static ValidatorResult validateEndTime(DateTime start, DateTime end) {
        ValidatorResult result = new ValidatorResult();
        String violation = null;

        if (checkDateTime(end)) {
            result.setIsValid(true);

            if (checkDateTime(start) && !checkDateTime(start, end)) {
                result.setIsValid(false);
                violation = buildMessage(context, "startedAtTime must precede endedAtTime");
            }
        } else {
            violation = buildMessage(context, "endedAtTime must be specified");
        }

        if (!result.isValid()) {
            result.errorMessage().appendViolation(violation);
        }

        return result;
    }

    /**
     * Joda-Time defines a null duration as zero length.
     * If a duration is not specified set result.setIsValid(true).  Do not provide a calculated duration.
     * If a duration is specified but not start and/or end times set result.setIsValid(true).
     * If a duration together with start and end times are specified check the specified duration
     * against the calculated duration. If equal set result.setIsValid(true).
     * ValidatorResult.isValid defaults to false.
     * @param start
     * @param end
     * @param duration
     * @return Validation result
     */
    protected static ValidatorResult validateDuration(DateTime start, DateTime end, String duration) {
        ValidatorResult result = new ValidatorResult();
        String violation = null;

        if (duration != null && checkDateTime(start) && checkDateTime(end)) {
            if (checkDuration(start, end, duration)) {
                result.setIsValid(true);
            } else {
                violation = buildMessage(context, "specified duration does not equal calculated duration");
            }
        } else {
            result.setIsValid(true);
        }

        if (!result.isValid()) {
            result.errorMessage().appendViolation(violation);
        }

        return result;
    }

    /**
     * Check that the time value is not null.
     * @param time
     * @return boolean true/false.
     */
    protected static boolean checkDateTime(DateTime time) {
        return time != null;
    }

    /**
     * Event start times are required; Event end times are optional while Entity start and end times are both
     * typically optional.  However if both start and end times are specified check that the start time
     * precedes the end time.  The isBefore() method evaluates null instants to check against as now.
     * @param start
     * @param end
     * @return boolean true/false
     */
    protected static boolean checkDateTime(DateTime start, DateTime end) {
        return start.isBefore(end);

        /**
         if (checkDateTime(start)) {
         if (checkDateTime(end)) {
         return start.isBefore(end);
         } else {
         return true;
         }
         } else {
         return false;
         }
         */
    }

    /**
     * Check duration against start and end times.
     * @param start
     * @param end
     * @param duration
     * @return boolean true/false.
     */
    protected static boolean checkDuration(DateTime start, DateTime end, String duration) {
        return checkDurationFormat(duration) &&
                duration.equals(new Interval(start, end).toDuration().toString());

        /**
         return duration != null &&
         checkDurationFormat(duration) &&
         checkDateTime(start) &&
         checkDateTime(end) &&
         checkDateTime(start, end) &&
         duration.equals(new Interval(start, end).toDuration().toString());
         **/
    }

    /**
     * Parses the format PTa.bS
     * @param duration
     * @return boolean true/false.
     */
    protected static boolean checkDurationFormat(String duration) {
        try {
            Duration.parse(duration);
            return true;
        } catch (IllegalFormatException ex) {
            return false;
        }
    }

    /**
     * Check for equality
     * @param value
     * @param constant
     * @return boolean true/false
     */
    protected static boolean isEqual(String value, String constant) {
        return value.equals(constant);
    }

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
    protected static boolean isOfType(Object object, Class<?> type) {
        return type.isInstance(object);
    }

    /**
     * Build error message.
     * @param context
     * @param violation
     * @return
     */
    protected static String buildMessage(String context, String violation) {
        StringBuilder message = new StringBuilder();
        message.append(context);
        message.append(" ");
        message.append(violation);

        return violation.toString();
    }
}