package org.imsglobal.caliper.validators.entities;

import org.imsglobal.caliper.entities.Session;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.lis.Person;
import org.imsglobal.caliper.validators.ValidatorResult;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SessionValidator extends EntityValidator<Session> {

    /**
     * Constructor
     */
   public SessionValidator() {
        super();
    }

    /**
     * Convenience method that provides a rollup of Session property validators.
     * @param session
     * @return Validation result
     */
    @Override
    public ValidatorResult validate(@Nonnull Session session) {
        ValidatorResult result = new ValidatorResult();
        String context = session.getClass().getSimpleName();

        ValidatorResult id = validateId(context, session.getId());
        if (!id.isValid()) {
            result.errorMessage().appendViolation(id.errorMessage().toString());
        }

        ValidatorResult typeURI = validateTypeURI(context, session.getType());
        if (!typeURI.isValid()) {
            result.errorMessage().appendViolation(typeURI.errorMessage().toString());
        }

        ValidatorResult actor = validateActor(context, session.getActor());
        if (!actor.isValid()) {
            result.errorMessage().appendViolation(actor.errorMessage().toString());
        }

        ValidatorResult start = validateStartTime(context, session.getStartedAtTime(), session.getEndedAtTime());
        if (!start.isValid()) {
            result.errorMessage().appendViolation(start.errorMessage().toString());
        }

        ValidatorResult duration = validateDuration(context, session.getStartedAtTime(),
                                                    session.getEndedAtTime(), session.getDuration());
        if (!duration.isValid()) {
            result.errorMessage().appendViolation(duration.errorMessage().toString());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper Session conformance:");
        }

        return result;
    }

    /**
     * Validate actor generating the session is a person.
     * @param context
     * @param actor
     * @return Validation result
     */
    public ValidatorResult validateActor(@Nonnull String context, @Nonnull Agent actor) {
        return EntityValidatorUtils.context(context).validateType(actor, Person.class);
    }

    /**
     * Validate start time.
     * @param context
     * @param start
     * @param end
     * @return Validation result
     */
    public ValidatorResult validateStartTime(@Nonnull String context, @Nonnull DateTime start, @Nullable DateTime end) {
        return EntityValidatorUtils.context(context).validateStartTime(start, end);
    }

    /**
     * Validate duration.
     * @param context
     * @param start
     * @param end
     * @param duration
     * @return Validation result
     */
    public ValidatorResult validateDuration(@Nonnull String context, @Nonnull DateTime start,
                                            @Nullable DateTime end, @Nullable String duration) {
        return EntityValidatorUtils.context(context).validateDuration(start, end, duration);
    }
}