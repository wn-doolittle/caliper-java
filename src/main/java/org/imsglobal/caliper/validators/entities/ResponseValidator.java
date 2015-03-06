package org.imsglobal.caliper.validators.entities;

import org.imsglobal.caliper.entities.assignable.AssignableDigitalResource;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.lis.Person;
import org.imsglobal.caliper.response.Response;
import org.imsglobal.caliper.validators.ValidatorResult;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ResponseValidator<T extends Response> extends EntityValidator<T> {

    /**
     * Constructor
     */
    public ResponseValidator() {
        super();
    }

    /**
     * Convenience method that provides a rollup of Response property validators.
     * @param response
     * @return Validation result
     */
    @Override
    public ValidatorResult validate(@Nonnull T response) {
        ValidatorResult result = new ValidatorResult();
        String context = response.getClass().getSimpleName();

        ValidatorResult id = validateId(context, response.getId());
        if (!id.isValid()) {
            result.errorMessage().appendViolation(id.errorMessage().toString());
        }

        ValidatorResult typeURI = validateTypeURI(context, response.getType());
        if (!typeURI.isValid()) {
            result.errorMessage().appendViolation(typeURI.errorMessage().toString());
        }

        /**
         ValidatorResult assignable = validateAssignable(context, response.getAssignable());
         if (!assignable.isValid()) {
         result.errorMessage().appendViolation(assignable.errorMessage().toString());
         }

         ValidatorResult actor = validateActorIsPerson(context, response.getActor());
         if (!actor.isValid()) {
         result.errorMessage().appendViolation(actor.errorMessage().toString());
         }
         */

        ValidatorResult attempt = validateAttempt(context, response.getAttempt());
        if (!attempt.isValid()) {
            result.errorMessage().appendViolation(attempt.errorMessage().toString());
        }

        ValidatorResult start = validateStartTime(context, response.getStartedAtTime(), response.getEndedAtTime());
        if (!start.isValid()) {
            result.errorMessage().appendViolation(start.errorMessage().toString());
        }

        ValidatorResult duration = validateDuration(context, response.getStartedAtTime(),
                                                    response.getEndedAtTime(), response.getDuration());
        if (!duration.isValid()) {
            result.errorMessage().appendViolation(duration.errorMessage().toString());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper Response conformance:");
        }

        return result;
    }

    /**
     * Check the type URI.
     * @param context
     * @param typeURI
     * @return Validation result
     */
    @Override
    public ValidatorResult validateTypeURI(@Nonnull String context, @Nonnull String typeURI) {
        return EntityValidatorUtils.context(context).validateTypeURI(typeURI, Response.Type.lookupConstantWithTypeURI(typeURI));
    }

    /**
     * Validate actor type.
     * @param context
     * @param actor
     * @return Validation result
     */
    public ValidatorResult validateActorIsPerson(@Nonnull String context, @Nonnull Agent actor) {
        return EntityValidatorUtils.context(context).validateType(actor, Person.class);
    }

    /**
     * Validate assignable type.
     * @param context
     * @param assignable
     * @return Validation result
     */
    public ValidatorResult validateAssignable(@Nonnull String context, @Nonnull AssignableDigitalResource assignable) {
        return new AssignableValidator<>().validate(assignable);
    }

    /**
     * Validate attempt.
     * @param context
     * @param attempt
     * @return Validation result
     */
    public ValidatorResult validateAttempt(@Nonnull String context, @Nonnull Attempt attempt) {
        return new AttemptValidator().validate(attempt);
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