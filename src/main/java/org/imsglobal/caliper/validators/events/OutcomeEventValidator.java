package org.imsglobal.caliper.validators.events;

import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.outcome.Result;
import org.imsglobal.caliper.events.OutcomeEvent;
import org.imsglobal.caliper.validators.ValidatorResult;

import javax.annotation.Nonnull;

public class OutcomeEventValidator extends EventValidator<OutcomeEvent> {

    /**
     * Convenience method that provides a rollup of OutcomeEvent property validators.
     * @param event
     * @return
     */
    @Override
    public ValidatorResult validateEvent(@Nonnull OutcomeEvent event, ValidatorResult result) {
        String context = event.getClass().getSimpleName();

        ValidatorResult contextURI = validateContextURI(context, event.getContext());
        if (!contextURI.isValid()) {
            result.errorMessage().appendViolation(contextURI.errorMessage().toString());
        }

        ValidatorResult typeURI = validateTypeURI(context, event.getType());
        if (!typeURI.isValid()) {
            result.errorMessage().appendViolation(typeURI.errorMessage().toString());
        }

        ValidatorResult object = validateObjectIsAttempt(context, event.getObject());
        if (!object.isValid()) {
            result.errorMessage().appendViolation(object.errorMessage().toString());
        }

        ValidatorResult generated = validateGeneratedIsResult(context, event.getGenerated());
        if (!generated.isValid()) {
            result.errorMessage().appendViolation(generated.errorMessage().toString());
        }

        ValidatorResult start = validateStartTime(context, event.getStartedAtTime(), event.getEndedAtTime());
        if (!start.isValid()) {
            result.errorMessage().appendViolation(start.errorMessage().toString());
        }

        ValidatorResult duration = validateDuration(context, event.getStartedAtTime(), event.getEndedAtTime(), event.getDuration());
        if (!duration.isValid()) {
            result.errorMessage().appendViolation(duration.errorMessage().toString());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper OutcomeEvent conformance:");
        }

        return result;
    }

    /**
     * Validate that the object is an attempt.
     * @param context
     * @param object
     * @return Validator result
     */
    public ValidatorResult validateObjectIsAttempt(@Nonnull String context, @Nonnull Object object) {
        return EventValidatorUtils.context(context).validateType(object, Attempt.class);
    }

    /**
     * Validate that the generated object is a result.
     * @param context
     * @param generated
     * @return Validator result
     */
    public ValidatorResult validateGeneratedIsResult(@Nonnull String context, @Nonnull Generatable generated) {
        return EventValidatorUtils.context(context).validateType(generated, Result.class);
    }
}