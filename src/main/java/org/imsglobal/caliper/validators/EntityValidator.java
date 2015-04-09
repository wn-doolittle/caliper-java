package org.imsglobal.caliper.validators;

import com.google.common.base.Strings;
import org.imsglobal.caliper.entities.Type;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.lis.Membership;
import org.imsglobal.caliper.entities.lis.Status;
import org.imsglobal.caliper.entities.lis.SupportedStatuses;
import org.joda.time.DateTime;

import static com.google.common.base.Preconditions.checkArgument;

public class EntityValidator {

    /**
     * Constructor
     */
    public EntityValidator() {

    }

    /**
     * Check if identifier is null or empty.
     * @param id
     * @throws IllegalArgumentException
     */
    public static void checkId(String name, String id) throws IllegalArgumentException {
        checkArgument(!(Strings.isNullOrEmpty(id)), "%s identifier must be specified", name);
    }

    /**
     * Check Entity type URI equality.
     * @param type
     * @param expected
     * @throws IllegalArgumentException
     */
    public static void checkType(Type type, Type expected) throws IllegalArgumentException {
        checkArgument(type == expected, "expected type %s but was %s", expected.getValue(), type.getValue());
    }

    /**
     * Check object Type
     * @param actor
     * @param type
     * @throws IllegalArgumentException
     */
    public static void checkActorType(Agent actor, Class<?> type) throws IllegalArgumentException {
        TypeValidator.checkActorType(actor, type);
    }

    /**
     * Check attempt
     * @param attempt
     * @throws IllegalArgumentException
     */
    public static void checkAttempt(Attempt attempt) throws IllegalArgumentException {
        checkArgument(attempt != null, "attempt must be specified");
    }

    /**
     * Check count
     * @param count
     * @throws IllegalArgumentException
     */
    public static void checkCount(int count) throws IllegalArgumentException {
        checkArgument(count > 0, "count >= 1 must be specified");
    }

    /**
     * Check if Membership status is supported.
     * @param status
     * @throws IllegalArgumentException
     */
    public static void checkMembershipStatus(org.imsglobal.caliper.entities.w3c.Status status) throws IllegalArgumentException {
        checkArgument(status != null, "membership status must be specified");

        SupportedStatuses statuses = Membership.class.getAnnotation(SupportedStatuses.class);
        boolean isSupported = false;

        for (Status supportedStatus : statuses.value()) {
            if (supportedStatus.equals(status)) {
                isSupported = true;
                break;
            }
        }
        checkArgument(isSupported, "membership status %s is not supported", status);
    }

    /**
     * Check start time
     * @param start
     * @param end
     * @throws IllegalArgumentException
     */
    public static void checkStartTime(DateTime start, DateTime end) throws IllegalArgumentException {
        TimeValidator.checkStartTime(start, end);
    }

    /**
     * Check end time
     * @param start
     * @param end
     * @throws IllegalArgumentException
     */
    public static void checkEndTime(DateTime start, DateTime end) throws IllegalArgumentException {
        TimeValidator.checkEndTime(start, end);
    }

    /**
     * Check duration
     * @param start
     * @param end
     * @param duration
     * @throws IllegalArgumentException
     */
    public static void checkDuration(DateTime start, DateTime end, String duration) throws IllegalArgumentException {
        TimeValidator.checkDuration(start, end, duration);
    }
}