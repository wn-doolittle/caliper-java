package org.imsglobal.caliper.validators.entities;

import org.imsglobal.caliper.entities.assignable.Attempt;
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

        ValidatorResult actorId = validateActorId(context, response.getActorId());
        if (!actorId.isValid()) {
            result.errorMessage().appendViolation(actorId.errorMessage().toString());
        }

        ValidatorResult assignableId = validateAssignableId(context, response.getAssignableId());
        if (!assignableId.isValid()) {
            result.errorMessage().appendViolation(assignableId.errorMessage().toString());
        }

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
     * Validate actor id.
     * @param context
     * @param id
     * @return
     */
    public ValidatorResult validateActorId(String context, String id) {
        return validateId(context, id);
    }

    /**
     * Validate assignable id.
     * @param context
     * @param id
     * @return
     */
    public ValidatorResult validateAssignableId(String context, String id) {
        return validateId(context, id);
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