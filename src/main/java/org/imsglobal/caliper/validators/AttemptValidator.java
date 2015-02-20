package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.validators.EventValidator.Conformance;

import javax.annotation.Nonnull;

public class AttemptValidator {

    /**
     * Constructor
     */
    public AttemptValidator() {

    }

    /**
     * Validate attempt properties.
     * <p/>
     * <h3>Properties</h3>
     * <dl>
     * <dt>id</dt>
     * <dd>required</dd>
     * <dt>type</dt>
     * <dd>required</dd>
     * <dt>name</dt>
     * <dd>optional</dd>
     * <dt>lastModifiedDate</dt>
     * <dd>optional</dd>
     * <dt>assignableId</dt>
     * <dd>required</dd>
     * <dt>actorId</dt>
     * <dd>required</dd>
     * <dt>count</dt>
     * <dd>required</dd>
     * <dt>startedAtTime</dt>
     * <dd>required</dd>
     * <dt>endedAtTime</dt>
     * <dd>optional</dd>
     * <dt>duration</dt>
     * <dd>optional</dd>
     * </dl>
     *
     * @param attempt
     * @return conformance violations message.
     */
    public static ValidatorResult validate(@Nonnull Attempt attempt) {
        String context = "Attempt ";
        ValidatorResult result = new ValidatorResult();

        if (!attempt.getType().equals(Entity.Type.ATTEMPT.uri())) {
            result.errorMessage().appendText(context + Conformance.TYPE_ERROR.violation());
        }

        if (attempt.getCount() < 1) {
            result.errorMessage().appendText(context + Conformance.COUNT_NOT_ZERO.violation());
        }

        ValidatorResult startTimeValidator;
        startTimeValidator = StartTimeValidator.validate(attempt.getStartedAtTime(), attempt.getEndedAtTime(), context);
        if (!startTimeValidator.isValid()) {
            result.errorMessage().appendText(startTimeValidator.errorMessage().toString());
        }

        ValidatorResult durationValidator = DurationValidator.validate(attempt.getStartedAtTime(),
                attempt.getEndedAtTime(), attempt.getDuration(), context);
        if (!durationValidator.isValid()) {
            result.errorMessage().appendText(durationValidator.errorMessage().toString());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        }

        return result;
    }
}