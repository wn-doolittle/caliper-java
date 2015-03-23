package org.imsglobal.caliper.validators.events;

import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.assessment.Assessment;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.events.AssessmentEvent;
import org.imsglobal.caliper.validators.ValidatorResult;

import javax.annotation.Nonnull;

public class AssessmentEventValidator extends EventValidator<AssessmentEvent> {

    /**
     * Convenience method that provides a rollup of AssessmentEvent property validators.
     * @param event
     * @return
     */
    @Override
    public ValidatorResult validateEvent(@Nonnull AssessmentEvent event, ValidatorResult result) {
        String context = event.getClass().getSimpleName();

        ValidatorResult contextURI = validateContextURI(context, event.getContext());
        if (!contextURI.isValid()) {
            result.errorMessage().appendViolation(contextURI.errorMessage().toString());
        }

        ValidatorResult typeURI = validateTypeURI(context, event.getType());
        if (!typeURI.isValid()) {
            result.errorMessage().appendViolation(typeURI.errorMessage().toString());
        }

        ValidatorResult actor = validateActorIsPerson(context, event.getActor());
        if (!actor.isValid()) {
            result.errorMessage().appendViolation(actor.errorMessage().toString());
        }

        ValidatorResult object = validateObjectIsAssessment(context, event.getObject());
        if (!object.isValid()) {
            result.errorMessage().appendViolation(object.errorMessage().toString());
        }

        ValidatorResult generated = validateGeneratedIsAttempt(context, event.getGenerated());
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
            result.errorMessage().endMessage("Caliper AssessmentEvent conformance:");
        }

        return result;
    }

    /**
     * Validate that the object is an assessment.
     * @param context
     * @param object
     * @return Validator result
     */
    public ValidatorResult validateObjectIsAssessment(@Nonnull String context, @Nonnull Object object) {
        return EventValidatorUtils.context(context).validateType(object, Assessment.class);
    }

    /**
     * Validate that the generated object is an attempt.
     * @param context
     * @param generated
     * @return Validator result
     */
    public ValidatorResult validateGeneratedIsAttempt(@Nonnull String context, @Nonnull Generatable generated) {
        return EventValidatorUtils.context(context).validateType(generated, Attempt.class);
    }
}