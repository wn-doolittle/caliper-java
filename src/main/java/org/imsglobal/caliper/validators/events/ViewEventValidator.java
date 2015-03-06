package org.imsglobal.caliper.validators.events;

import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.events.ViewEvent;
import org.imsglobal.caliper.validators.ValidatorResult;

import javax.annotation.Nonnull;

public class ViewEventValidator extends EventValidator<ViewEvent> {

    /**
     * Constructor
     */
     private ViewEventValidator(String actionKey) {
        super(actionKey);
     }

    /**
     * Static factory method that sets the action key for validator comparison checks.
     * @return a new instance of ViewEventValidator.
     */
     public static ViewEventValidator action(String actionKey) {
        return new ViewEventValidator(actionKey);
     }

    /**
     * Convenience method that provides a rollup of ViewEvent property validators.
     * @param event
     * @return
     */
    @Override
    public ValidatorResult validate(@Nonnull ViewEvent event) {
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

        ValidatorResult object = validateObjectIsDigitalResource(context, event.getObject());
        if (!object.isValid()) {
            result.errorMessage().appendViolation(object.errorMessage().toString());
        }

        ValidatorResult target = validateTargetIsDigitalResource(context, event.getTarget());
        if (!target.isValid()) {
            result.errorMessage().appendViolation(target.errorMessage().toString());
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
            result.errorMessage().endMessage("Caliper ViewEvent conformance:");
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
     * Validate that the target is a digital resource.
     * @param context
     * @param target
     * @return Validator result
     */
    public ValidatorResult validateTargetIsDigitalResource(@Nonnull String context, @Nonnull Targetable target) {
        return EventValidatorUtils.context(context).validateType(target, DigitalResource.class);
    }
}