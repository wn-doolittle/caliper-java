package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.events.Event;

import javax.annotation.Nonnull;

public class OutcomeEventValidator implements EventValidator {

    /**
     * Constructor
     */
    public OutcomeEventValidator() {

    }

    /**
     * Validate outcome event properties.
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
     * <dd>required</dd>
     * <dt>action</dt>
     * <dd>required: outcome.*</dd>
     * <dt>object</dt>
     * <dd>required: Attempt</dd>
     * <dt>target</dt>
     * <dd>optional: a target object should not be specified.</dd>
     * <dt>generated</dt>
     * <dd>required: Result</dd>
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
        String context = "AssessmentEvent ";
        ValidatorResult result = new ValidatorResult();

        if (!event.getContext().equals(Event.Context.OUTCOME.uri())) {
            result.errorMessage().appendText(context + Conformance.CONTEXT_ERROR.violation());
        }

        if (!event.getType().equals(Event.Type.OUTCOME.uri())) {
            result.errorMessage().appendText(context + Conformance.TYPE_ERROR.violation());
        }

        ValidatorResult attemptValidatorResult = AttemptValidator.validate((Attempt) event.getObject());
        if (!attemptValidatorResult.isValid()) {
            result.errorMessage().appendText(attemptValidatorResult.errorMessage().toString());
        }

        if (event.getTarget() != null) {
            result.errorMessage().appendText(context + Conformance.TARGET_NOT_NULL.violation());
        }

        //TODO may need to validate event.getGenerated (Result) properties (e.g., normalScore, totalScore)

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
            result.errorMessage().endMessage("Caliper Outcome profile conformance:");
        }

        return result;
    }
}