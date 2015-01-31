package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.events.Event;

public interface EventValidator {

    public enum Conformance {
        ACTION_IS_NULL("an action must be specified."),
        ACTION_UNRECOGNIZED("unrecognized action."),
        ACTOR_NOT_PERSON("actor must be of type Person"),
        ACTOR_NOT_SOFTWAREAPP("actor must be of type SoftwareApplication"),
        ASSIGNABLE_NOT_ASSESSMENT("assignable must be of type Assessment"),
        CONTEXT_ERROR("context URI must be specified"),
        COUNT_NOT_ZERO("count must be specified"),
        DURATION_INVALID("duration format is invalid"),
        ENDEDATTIME_IS_NULL("endedAtTime must be specified"),
        ENDEDATTIME_SET("endedAtTime must not be specified"),
        EVENT_ILLEGAL_STATE("event constructed in an illegal state."),
        GENERATED_NOT_ATTEMPT("generated object must be of type Attempt"),
        GENERATED_NOT_NULL("a generated object is not required"),
        GENERATED_NOT_SESSION("generated object must be of type Session"),
        OBJECT_NOT_ASSESSMENT("object must be of type Assessment"),
        OBJECT_NOT_SOFTWAREAPP("object must be of type SoftwareApplication"),
        STARTEDATTIME_IS_NULL("startedAtTime must be specified"),
        TARGET_NOT_CREATIVEWORK("target must be of type CreativeWork"),
        TARGET_NOT_DIGITALRESOURCE("target must be of type DigitalResource"),
        TARGET_NOT_NULL("target is not required."),
        TARGET_NOT_SESSION("target must be of type Session"),
        TARGET_NOT_SET("target must be specified"),
        TARGET_NOT_THING("target must be of type Thing"),
        TIME_ERROR("end time must be greater than start time."),
        TYPE_ERROR("type URI must be specified");

        private final String violation;

        /**
         * Private constructor
         * @param violation
         */
        private Conformance(final String violation) {
            this.violation = violation;
        }

        /**
         * @return violation string
         */
        public String violation() {
            return violation;
        }
    }

    /**
     * Validate Event.  Per Bloch throw an IllegalStateException if
     * programing errors are encountered during object construction
     * (Bloch, Effective Java, 2nd ed., items 2, 39, 60, 63).
     * @param event
     */
    ValidatorResult validate(Event event);
}