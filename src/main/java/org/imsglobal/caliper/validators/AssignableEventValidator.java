package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.entities.assignable.AssignableDigitalResource;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.events.Event;

import javax.annotation.Nonnull;

public class AssignableEventValidator implements EventValidator {

    /**
     * Constructor
     */
    public AssignableEventValidator() {

    }

    /**
     * Validate assignable event properties.
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
     * <dd>required: assignable.*</dd>
     * <dt>object</dt>
     * <dd>required: AssignableDigitalResource</dd>
     * <dt>target</dt>
     * <dd>optional</dd>
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
        String context = "AssessmentEvent ";
        ValidatorResult result = new ValidatorResult();

        if (!event.getContext().equals(Event.Context.ASSIGNABLE.uri())) {
            result.errorMessage().appendText(context + Conformance.CONTEXT_ERROR.violation());
        }

        if (!event.getType().equals(Event.Type.ASSIGNABLE.uri())) {
            result.errorMessage().appendText(context + Conformance.TYPE_ERROR.violation());
        }

        if (!ValidatorUtils.isOfType(event.getObject(), AssignableDigitalResource.class)) {
            result.errorMessage().appendText(context + Conformance.OBJECT_NOT_ASSIGNABLE.violation());
        }

        if (!ValidatorUtils.isOfType(event.getGenerated(), Attempt.class)) {
            result.errorMessage().appendText(context + Conformance.GENERATED_NOT_ATTEMPT.violation());
        } else {
            ValidatorResult attemptValidatorResult = AttemptValidator.validate((Attempt) event.getGenerated());
            if (!attemptValidatorResult.isValid()) {
                result.errorMessage().appendText(attemptValidatorResult.errorMessage().toString());
            }
        }

        ValidatorResult startTimeValidator;
        startTimeValidator = StartTimeValidator.validate(event.getStartedAtTime(), event.getEndedAtTime(), context);
        if (!startTimeValidator.isValid()) {
            result.errorMessage().appendText(startTimeValidator.errorMessage().toString());
        }

        ValidatorResult durationValidator = DurationValidator.validate(event.getStartedAtTime(),
                event.getEndedAtTime(), event.getDuration(), context);
        if (!durationValidator.isValid()) {
            result.errorMessage().appendText(durationValidator.errorMessage().toString());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper Assignable profile conformance:");
        }

        return result;
    }
}