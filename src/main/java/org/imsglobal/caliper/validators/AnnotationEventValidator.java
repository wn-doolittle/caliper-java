package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.events.Event;

import javax.annotation.Nonnull;

public class AnnotationEventValidator implements EventValidator {

    /**
     * Constructor
     */
    public AnnotationEventValidator() {

    }

    /**
     * Validate annotation event properties.
     * <p/>
     * <h3>Properties</h3>
     * <dl>
     * <dt>context</dt>
     * <dd>required</dd>
     * <dt>type</dt>
     * <dd>required</dd>
     * <dt>edApp</dt>
     * <dd>optional</dd>
     * <dt>group</dt>
     * <dd>optional</dd>
     * <dt>actor</dt>
     * <dd>required: Person</dd>
     * <dt>action</dt>
     * <dd>required: annotation.*</dd>
     * <dt>object</dt>
     * <dd>required: Annotation</dd>
     * <dt>target</dt>
     * <dd>required: DigitalResource.</dd>
     * <dt>generated</dt>
     * <dd>optional</dd>
     * <dt>startedAtTime</dt>
     * <dd>required</dd>
     * <dt>endedAtTime</dt>
     * <dd>optional</dd>
     * <dt>duration</dt>
     * <dd>optional</dd>
     * </dl>
     *
     * @param event
     * @return conformance violations message.
     */
    public ValidatorResult validate(@Nonnull Event event) {
        String context = "AnnotationEvent ";
        ValidatorResult result = new ValidatorResult();

        if (!event.getContext().equals(Event.Context.ANNOTATION.uri())) {
            result.errorMessage().appendText(context + Conformance.CONTEXT_ERROR.violation());
        }

        if (!event.getType().equals(Event.Type.ANNOTATION.uri())) {
            result.errorMessage().appendText(context + Conformance.TYPE_ERROR.violation());
        }

        //TODO may need to check specific required fields for each annotation type (like SessionEventValidator).

        if (ValidatorUtils.checkStartedAtTime(event.getStartedAtTime())) {
            if (!ValidatorUtils.checkStartEndTimes(event.getStartedAtTime(), event.getEndedAtTime())) {
                result.errorMessage().appendText(context + Conformance.TIME_ERROR.violation());
            }
        } else {
            result.errorMessage().appendText(context + Conformance.STARTEDATTIME_IS_NULL.violation());
        }

        if (!ValidatorUtils.checkDuration(event.getDuration())) {
            result.errorMessage().appendText(context + Conformance.DURATION_INVALID.violation());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper Annotation profile conformance:");
        }

        return result;
    }
}
