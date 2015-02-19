package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.profiles.AssessmentItemProfile;
import org.imsglobal.caliper.response.Response;

public class AssessmentItemEventValidator implements EventValidator {

    private String key;

    /**
     * Constructor
     */
    public AssessmentItemEventValidator(String key) {
        this.key = key;
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
     * <dd>required: assessmentItem.*</dd>
     * <dt>object</dt>
     * <dd>required: AssessmentItem</dd>
     * <dt>target</dt>
     * <dd>optional: a target object should not be specified.</dd>
     * <dt>generated</dt>
     * <dd>required: Response</dd>
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
        String context = "AssessmentItemEvent ";
        ValidatorResult result = new ValidatorResult();

        if (!event.getContext().equals(Event.Context.ASSESSMENT_ITEM.uri())) {
            result.errorMessage().appendText(context + Conformance.CONTEXT_ERROR.violation());
        }

        if (!event.getType().equals(Event.Type.ASSESSMENT_ITEM.uri())) {
            result.errorMessage().appendText(context + Conformance.TYPE_ERROR.violation());
        }

        if (event.getTarget() != null) {
            result.errorMessage().appendText(context + Conformance.TARGET_NOT_NULL.violation());
        }

        if (key.equals(AssessmentItemProfile.Actions.COMPLETED.key())) {
            ValidatorResult generatedResult = ResponseValidator.validate((Response) event.getGenerated());
            if (!generatedResult.isValid()) {
                result.errorMessage().appendText(generatedResult.errorMessage().toString());
            }
        } else {
            ValidatorResult generatedResult = AttemptValidator.validate((Attempt) event.getGenerated());
            if (!generatedResult.isValid()) {
                result.errorMessage().appendText(generatedResult.errorMessage().toString());
            }
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