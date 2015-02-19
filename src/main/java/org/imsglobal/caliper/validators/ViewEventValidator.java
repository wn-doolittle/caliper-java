package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.entities.schemadotorg.CreativeWork;
import org.imsglobal.caliper.events.Event;

import javax.annotation.Nonnull;

public class ViewEventValidator implements EventValidator {

    /**
     * Constructor
     */
    public ViewEventValidator() {

    }

    /**
     * Validate assessment event properties.
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
     * <dd>required: reading.view</dd>
     * <dt>object</dt>
     * <dd>required: CreativeWork</dd>
     * <dt>target</dt>
     * <dd>required: CreativeWork</dd>
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
        String context = "ReadingEvent ";
        ValidatorResult result = new ValidatorResult();

        if (!event.getContext().equals(Event.Context.VIEW.uri())) {
            result.errorMessage().appendText(context + EventValidator.Conformance.CONTEXT_ERROR.violation());
        }

        if (!event.getType().equals(Event.Type.VIEW.uri())) {
            result.errorMessage().appendText(context + EventValidator.Conformance.TYPE_ERROR.violation());
        }

        if (!ValidatorUtils.isOfType(event.getTarget(), CreativeWork.class)) {
            result.errorMessage().appendText(context + EventValidator.Conformance.TARGET_NOT_CREATIVEWORK.violation());
        }

        if (ValidatorUtils.checkStartedAtTime(event.getStartedAtTime())) {
            if (!ValidatorUtils.checkStartEndTimes(event.getStartedAtTime(), event.getEndedAtTime())) {
                result.errorMessage().appendText(context + EventValidator.Conformance.TIME_ERROR.violation());
            }
        } else {
            result.errorMessage().appendText(context + EventValidator.Conformance.STARTEDATTIME_IS_NULL.violation());
        }

        if (!ValidatorUtils.checkDuration(event.getDuration())) {
            result.errorMessage().appendText(context + EventValidator.Conformance.DURATION_INVALID.violation());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper View event conformance:");
        }

        return result;
    }
}