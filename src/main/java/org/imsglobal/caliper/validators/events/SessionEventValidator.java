package org.imsglobal.caliper.validators.events;

import org.imsglobal.caliper.entities.*;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.events.SessionEvent;
import org.imsglobal.caliper.profiles.SessionProfile;
import org.imsglobal.caliper.validators.ValidatorResult;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;

public class SessionEventValidator extends EventValidator<SessionEvent> {

    /**
     * Constructor
     */
     private SessionEventValidator(String actionKey) {
        super(actionKey);
     }

    /**
     * Static factory method that sets the action key for validator comparison checks.
     * @return a new instance of SessionEventValidator.
     */
     public static SessionEventValidator action(String actionKey) {
        return new SessionEventValidator(actionKey);
     }

    /**
     * Convenience method that provides a rollup of SessionEvent property validators.
     * @param event
     * @return
     */
    @Override
    public ValidatorResult validate(@Nonnull SessionEvent event) {
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

        if (actionKey.equals(SessionProfile.Actions.TIMEDOUT.key())) {
            ValidatorResult actor = validateActorIsSoftwareApplication(context, event.getActor());
            if (!actor.isValid()) {
                result.errorMessage().appendViolation(actor.errorMessage().toString());
            }
        } else {
            ValidatorResult actor = validateActorIsPerson(context, event.getActor());
            if (!actor.isValid()) {
                result.errorMessage().appendViolation(actor.errorMessage().toString());
            }
        }

        ValidatorResult object = validateObjectIsSoftwareApplication(context, event.getObject());
        if (!object.isValid()) {
            result.errorMessage().appendViolation(object.errorMessage().toString());
        }

        if (actionKey.equals(SessionProfile.Actions.LOGGEDIN.key())) {
            ValidatorResult target = validateTargetIsDigitalResource(context, event.getTarget());
            if (!target.isValid()) {
                result.errorMessage().appendViolation(target.errorMessage().toString());
            }

            ValidatorResult generated = validateGeneratedIsSession(context, event.getGenerated());
            if (!generated.isValid()) {
                result.errorMessage().appendViolation(generated.errorMessage().toString());
            }
        } else {
            ValidatorResult target = validateTargetIsSession(context, event.getTarget());
            if (!target.isValid()) {
                result.errorMessage().appendViolation(target.errorMessage().toString());
            }
        }

        ValidatorResult start = validateStartTime(context, event.getStartedAtTime(), event.getEndedAtTime());
        if (!start.isValid()) {
            result.errorMessage().appendViolation(start.errorMessage().toString());
        }

        if (actionKey.equals(SessionProfile.Actions.LOGGEDOUT.key()) ||
            actionKey.equals(SessionProfile.Actions.TIMEDOUT.key())) {
            ValidatorResult end = validateEndTime(context, event.getStartedAtTime(), event.getEndedAtTime());
            if (!end.isValid()) {
                result.errorMessage().appendViolation(end.errorMessage().toString());
            }

            ValidatorResult duration = validateDuration(context, event.getStartedAtTime(),
                                                        event.getEndedAtTime(), event.getDuration());
            if (!duration.isValid()) {
                result.errorMessage().appendViolation(duration.errorMessage().toString());
            }
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper SessionEvent conformance:");
        }

        return result;
    }

    /**
     * Validate actor is a software application.
     * @param context
     * @param actor
     * @return Validator result
     */
    public ValidatorResult validateActorIsSoftwareApplication(@Nonnull String context, @Nonnull Agent actor) {
        return EventValidatorUtils.context(context).validateType(actor, SoftwareApplication.class);
    }

    /**
     * Validate that the object is a software application.
     * @param context
     * @param object
     * @return Validator result
     */
    public ValidatorResult validateObjectIsSoftwareApplication(@Nonnull String context, @Nonnull Object object) {
        return EventValidatorUtils.context(context).validateType(object, SoftwareApplication.class);
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

    /**
     * Validate that the target is a session.
     * @param context
     * @param target
     * @return Validator result
     */
    public ValidatorResult validateTargetIsSession(@Nonnull String context, @Nonnull Targetable target) {
        return EventValidatorUtils.context(context).validateType(target, Session.class);
    }

    /**
     * Validate that the generated object is a session.
     * @param context
     * @param generated
     * @return Validator result
     */
    public ValidatorResult validateGeneratedIsSession(@Nonnull String context, @Nonnull Generatable generated) {
        return EventValidatorUtils.context(context).validateType(generated, Session.class);
    }

    /**
     * Validate the end time.
     * @param context
     * @param start
     * @param end
     * @return Validator result
     */
    public ValidatorResult validateEndTime(@Nonnull String context, @Nonnull DateTime start, @Nonnull DateTime end) {
        return EventValidatorUtils.context(context).validateEndTime(start, end);
    }
}