package org.imsglobal.caliper.validators.events;

import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.lis.Person;
import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.events.SupportedActions;
import org.imsglobal.caliper.profiles.Profile.Action;
import org.imsglobal.caliper.validators.CaliperEventValidator;
import org.imsglobal.caliper.validators.ValidatorResult;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class EventValidator<T extends Event> implements CaliperEventValidator<T> {

    /**
     * Convenience method that provides a rollup of Event property validators.
     * @param event
     * @return
     */
    public ValidatorResult validate(@Nonnull T event) {
        ValidatorResult result = new ValidatorResult();
        String context = event.getClass().getSimpleName();

        result = validateActions(event, result);

        ValidatorResult contextURI = validateContextURI(context, event.getContext());
        if (!contextURI.isValid()) {
            result.errorMessage().appendViolation(contextURI.errorMessage().toString());
        }

        ValidatorResult typeURI = validateTypeURI(context, event.getType());
        if (!typeURI.isValid()) {
            result.errorMessage().appendViolation(typeURI.errorMessage().toString());
        }

        ValidatorResult start = validateStartTime(context, event.getStartedAtTime(), event.getEndedAtTime());
        if (!start.isValid()) {
            result.errorMessage().appendViolation(start.errorMessage().toString());
        }

        ValidatorResult duration = validateDuration(context, event.getStartedAtTime(),
                                                    event.getEndedAtTime(), event.getDuration());
        if (!duration.isValid()) {
            result.errorMessage().appendViolation(duration.errorMessage().toString());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper Event conformance:");
        }

        return validateEvent(event, result);
    }

    public abstract ValidatorResult validateEvent(@Nonnull T event, ValidatorResult result);

    /**
     * Check the context.
     * @param context
     * @param contextURI
     * @return Validator result
     */
    public ValidatorResult validateContextURI(@Nonnull String context, @Nonnull String contextURI) {
        return EventValidatorUtils.context(context).validateContextURI(contextURI, Event.Context.lookupConstantWithContextURI(contextURI));
    }

    /**
     * Check the type URI
     * @param context
     * @param typeURI
     * @return Validator result
     */
    public ValidatorResult validateTypeURI(@Nonnull String context, @Nonnull String typeURI) {
        return EventValidatorUtils.context(context).validateTypeURI(typeURI, Event.Type.lookupConstantWithTypeURI(typeURI));
    }

    /**
     * Validate actor is a person.
     * @param context
     * @param actor
     * @return
     */
    public ValidatorResult validateActorIsPerson(@Nonnull String context, @Nonnull Agent actor) {
        return EventValidatorUtils.context(context).validateType(actor, Person.class);
    }

    /**
     * Validate start time
     * @param context
     * @param start
     * @param end
     * @return Validation result
     */
    public ValidatorResult validateStartTime(@Nonnull String context, @Nonnull DateTime start, @Nullable DateTime end) {
        return EventValidatorUtils.context(context).validateStartTime(start, end);
    }

    /**
     * Validate duration.
     * @param context
     * @param start
     * @param end
     * @param duration
     * @return Validation result
     */
    public ValidatorResult validateDuration(@Nonnull String context, @Nonnull DateTime start, @Nullable DateTime end, @Nullable String duration) {
        return EventValidatorUtils.context(context).validateDuration(start, end, duration);
    }

    /**
     * Validate submitted action against event's controlled vocabulary.
     * @param event
     * @param result
     * @return Validation result
     */
    private ValidatorResult validateActions(T event, ValidatorResult result) {
        if (event.getAction() == null) {
            result.errorMessage().appendViolation("action cannot be null");
            return result;
        }

        SupportedActions actions = event.getClass().getAnnotation(SupportedActions.class);
        if (actions == null) {
            return result;
        }

        Boolean actionIsSupported = false;
        for (Action action : actions.value()) {
            if(action.equals(event.getAction())) {
                actionIsSupported = true;
                break;
            }
        }

        if (!actionIsSupported) {
            result.errorMessage().appendViolation("Action for instance of " + event.getClass().getName() + " does not support action: " + event.getAction().getValue());
        }
        return result;
    }
}