package org.imsglobal.caliper.validators;

import com.google.common.base.Strings;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.annotation.Annotation;
import org.imsglobal.caliper.entities.assignable.AssignableDigitalResource;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.lis.Membership;
import org.imsglobal.caliper.entities.lis.Status;
import org.imsglobal.caliper.entities.lis.SupportedStatuses;
import org.imsglobal.caliper.entities.media.MediaObject;
import org.imsglobal.caliper.entities.response.Response;
import org.joda.time.DateTime;

import static com.google.common.base.Preconditions.checkArgument;

public class EntityValidator {

    /**
     * Constructor
     */
    public EntityValidator() {

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
     * Check duration
     * @param start
     * @param end
     * @param duration
     * @throws IllegalArgumentException
     */
    public static void checkDuration(DateTime start, DateTime end, String duration) throws IllegalArgumentException {
        TimeValidator.checkDuration(start, end, duration);
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
     * Check if identifier is null or empty.
     * @param id
     * @throws IllegalArgumentException
     */
    public static void checkId(String name, String id) throws IllegalArgumentException {
        checkArgument(!(Strings.isNullOrEmpty(id)), "%s identifier must be specified", name);
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
     * Check Annotation type URI equality.
     * @param value
     * @param type
     * @throws IllegalArgumentException
     */
    public static void checkTypeUri(String value, Annotation.Type type) throws IllegalArgumentException {
        checkArgument(value.equals(type.uri()), "expected type %s but was %s", type.uri(), type);
    }

    /**
     * Check AssignableDigitalResource type URI equality.
     * @param value
     * @param type
     * @throws IllegalArgumentException
     */
    public static void checkTypeUri(String value, AssignableDigitalResource.Type type) throws IllegalArgumentException {
        checkArgument(value.equals(type.uri()), "expected type %s but was %s", type.uri(), type);
    }

    /**
     * Check DigitalResource type URI equality.
     * @param value
     * @param type
     * @throws IllegalArgumentException
     */
    public static void checkTypeUri(String value, DigitalResource.Type type) throws IllegalArgumentException {
        checkArgument(value.equals(type.uri()), "expected type %s but was %s", type.uri(), type);
    }

    /**
     * Check Entity type URI equality.
     * @param value
     * @param type
     * @throws IllegalArgumentException
     */
    public static void checkTypeUri(String value, Entity.Type type) throws IllegalArgumentException {
        checkArgument(value.equals(type.uri()), "expected type %s but was %s", type.uri(), type);
    }

    /**
     * Check MediaObject type URI equality.
     * @param value
     * @param type
     * @throws IllegalArgumentException
     */
    public static void checkTypeUri(String value, MediaObject.Type type) throws IllegalArgumentException {
        checkArgument(value.equals(type.uri()), "expected type %s but was %s", type.uri(), type);
    }

    /**
     * Check Response type URI equality.
     * @param value
     * @param type
     * @throws IllegalArgumentException
     */
    public static void checkTypeUri(String value, Response.Type type) throws IllegalArgumentException {
        checkArgument(value.equals(type.uri()), "expected type %s but was %s", type.uri(), type);
    }

    /**
     * Check for equality between String value and a constant.
     * @param value
     * @param constant
     * @return boolean true/false
     */
    private static boolean isEqual(String value, String constant) {
        return value.equals(constant);
    }

    /**
     * Check if String is null or empty.
     * @param value
     * @return
     */
    private static boolean isNullorEmpty(String value) {
        return Strings.isNullOrEmpty(value);
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
    private static boolean isOfType(Object object, Class<?> type) {
        return type.isInstance(object);
    }
}