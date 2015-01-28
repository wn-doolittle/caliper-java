package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.events.Event;

public class AssessmentEventValidator implements EventValidator {

    /**
     * Constructor
     */
    public AssessmentEventValidator() {

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
     * <dd>required: assessment.*</dd>
     * <dt>object</dt>
     * <dd>required: Assessment</dd>
     * <dt>target</dt>
     * <dd>optional: a target object should not be specified.</dd>
     * <dt>generated</dt>
     * <dd>required: Attempt</dd>
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
    public ValidatorResult validate(Event event) {
        String context = "AssessmentEvent ";
        ValidatorResult result = new ValidatorResult();

        if (!event.getContext().equals(Event.Context.ASSESSMENT.uri())) {
            result.errorMessage().appendText(context + Conformance.CONTEXT_ERROR.violation());
        }

        if (!event.getType().equals(Event.Type.ASSESSMENT.uri())) {
            result.errorMessage().appendText(context + Conformance.TYPE_ERROR.violation());
        }

        if (event.getTarget() != null) {
            result.errorMessage().appendText(context + Conformance.TARGET_NOT_NULL.violation());
        }

        ValidatorResult attemptValidatorResult = AttemptValidator.validate((Attempt) event.getGenerated());
        if (!attemptValidatorResult.isValid()) {
            result.errorMessage().appendText(attemptValidatorResult.errorMessage().toString());
        }

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
            result.errorMessage().endMessage("Caliper Assessment profile conformance:");
        }

        return result;
    }
}