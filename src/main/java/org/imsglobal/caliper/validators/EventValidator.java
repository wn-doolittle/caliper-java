package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.events.EventContext;
import org.imsglobal.caliper.events.EventType;
import org.imsglobal.caliper.events.SupportedActions;
import org.joda.time.DateTime;

import static com.google.common.base.Preconditions.checkArgument;

public class EventValidator {

    /**
     * Constructor
     */
    public EventValidator() {

    }

    /**
     * Check Event context.
     * @param context
     * @param expected
     * @throws IllegalArgumentException
     */
    public static void checkContext(EventContext context, EventContext expected) throws IllegalArgumentException {
        checkArgument(context == expected, "expected context %s but was %s", expected.getValue(), context.getValue());
    }

    /**
     * Check Event type.
     * @param type
     * @param expected
     * @throws IllegalArgumentException
     */
    public static void checkType(EventType type, EventType expected) throws IllegalArgumentException {
        checkArgument(type == expected, "expected type %s but was %s", expected.getValue(), type.getValue());
    }

    /**
     * Check actor type
     * @param actor
     * @param type
     * @throws IllegalArgumentException
     */
    public static void checkActorType(Agent actor, Class<?> type) throws IllegalArgumentException {
        TypeValidator.checkActorType(actor, type);
    }

    /**
     * Check action
     * @param action
     * @throws IllegalArgumentException
     */
    public static void checkAction(Action action, Class<? extends Event> clazz) throws IllegalArgumentException {
        checkArgument(action != null, "an action must be specified");

        SupportedActions actions = clazz.getAnnotation(SupportedActions.class);
        checkArgument(actions != null, "supported actions must be specified");

        boolean isSupported = false;
        for (Action supportedAction : actions.value()) {
            if (supportedAction.equals(action)) {
                isSupported = true;
                break;
            }
        }
        checkArgument(isSupported, "%s action is not supported", action.getValue());
    }

    /**
     * Check generated type.
     * @param generated
     * @param type
     * @return Validation result
     */
    public static void checkGeneratedType(Generatable generated, Class<?> type) throws IllegalArgumentException {
        TypeValidator.checkGeneratedType(generated, type);
    }

    /**
     * Check target type.
     * @param target
     * @param type
     * @return Validation result
     */
    public static void checkTargetType(Targetable target, Class<?> type) throws IllegalArgumentException {
        TypeValidator.checkTargetType(target, type);
    }

    /**
     * Check object type.
     * @param object
     * @param type
     * @return Validation result
     */
    public static void checkObjectType(Object object, Class<?> type) throws IllegalArgumentException {
        TypeValidator.checkObjectType(object, type);
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
     * @param duration
     * @throws IllegalArgumentException
     */
    public static void checkDuration(String duration) throws IllegalArgumentException {
        TimeValidator.checkDuration(duration);
    }
}