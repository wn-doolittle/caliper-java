package org.imsglobal.caliper.validators.events;

import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.annotation.Annotation;
import org.imsglobal.caliper.events.AnnotationEvent;
import org.imsglobal.caliper.validators.ValidatorResult;

import javax.annotation.Nonnull;

public class AnnotationEventValidator extends EventValidator<AnnotationEvent> {

    /**
     * Convenience method that provides a rollup of AnnotationEvent property validators.
     * @param event
     * @return
     */
    @Override
    public ValidatorResult validateEvent(@Nonnull AnnotationEvent event, ValidatorResult result) {
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

        ValidatorResult target = validateObjectIsDigitalResource(context, event.getObject());
        if (!target.isValid()) {
            result.errorMessage().appendViolation(target.errorMessage().toString());
        }

        ValidatorResult generated = validateGeneratedIsAnnotation(context, event.getGenerated());
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
            result.errorMessage().endMessage("Caliper AnnotationEvent conformance:");
        }

        return result;
    }

    /**
     * Validate that the object is a digital resource.
     * @param context
     * @param object
     * @return Validator result
     */
    public ValidatorResult validateObjectIsDigitalResource(@Nonnull String context, @Nonnull Object object) {
        return EventValidatorUtils.context(context).validateType(object, DigitalResource.class);
    }

    /**
     * Validate that the generated object is an annotation.
     * @param context
     * @param generated
     * @return Validation result
     */
    public ValidatorResult validateGeneratedIsAnnotation(@Nonnull String context, @Nonnull Generatable generated) {
        return EventValidatorUtils.context(context).validateType(generated, Annotation.class);
    }
}