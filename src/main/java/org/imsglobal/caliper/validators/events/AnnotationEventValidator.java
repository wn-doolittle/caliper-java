package org.imsglobal.caliper.validators.events;

import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.annotation.Annotation;
import org.imsglobal.caliper.events.AnnotationEvent;
import org.imsglobal.caliper.validators.ValidatorResult;

import javax.annotation.Nonnull;

public class AnnotationEventValidator extends EventValidator<AnnotationEvent> {

    /**
     * Constructor
     */
    private AnnotationEventValidator(String actionKey) {
        super(actionKey);
    }

    /**
     * Static factory method that sets the action key for validator comparison checks.
     * @return a new instance of AnnotationEventValidator.
     */
    public static AnnotationEventValidator action(String actionKey) {
        return new AnnotationEventValidator(actionKey);
    }

    /**
     * Convenience method that provides a rollup of AnnotationEvent property validators.
     * @param event
     * @return
     */
    @Override
    public ValidatorResult validate(@Nonnull AnnotationEvent event) {
        ValidatorResult result = new ValidatorResult();
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

        ValidatorResult object = validateObjectIsAnnotation(context, event.getObject());
        if (!object.isValid()) {
            result.errorMessage().appendViolation(object.errorMessage().toString());
        }

        // TODO drop when annotation is switched to generatable
        ValidatorResult target = validateTargetIsDigitalResource(context, event.getTarget());
        if (!target.isValid()) {
            result.errorMessage().appendViolation(target.errorMessage().toString());
        }

        // TODO activate when annotation is switched to generatable
        /**
        ValidatorResult generated = validateGeneratedIsAnnotation(context, event.getGenerated());
        if (!generated.isValid()) {
            result.errorMessage().appendViolation(generated.errorMessage().toString());
        }
         */

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

    // TODO switch object to generatable

    /**
     * Validate that the object is an annotation
     * @param context
     * @param object
     * @return Validator result
     */
    public ValidatorResult validateObjectIsAnnotation(@Nonnull String context, @Nonnull Object object) {
       return EventValidatorUtils.context(context).validateType(object, Annotation.class);
    }

    // TODO switch target to object; don't validate target

    /**
     * Validate that the target is a digital resource.
     * @param context
     * @param target
     * @return Validation result
     */
    public ValidatorResult validateTargetIsDigitalResource(@Nonnull String context, @Nonnull Targetable target) {
        return EventValidatorUtils.context(context).validateType(target, DigitalResource.class);
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