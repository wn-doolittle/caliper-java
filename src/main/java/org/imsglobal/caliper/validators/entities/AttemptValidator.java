package org.imsglobal.caliper.validators.entities;

import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.lis.Person;
import org.imsglobal.caliper.validators.ValidatorResult;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AttemptValidator extends EntityValidator<Attempt> {

    /**
     * Constructor
     */
    public AttemptValidator() {
        super();
    }

    /**
     * Convenience method that provides a rollup of Attempt property validators.
     * @param attempt
     * @return Validation result
     */
    @Override
    public ValidatorResult validate(@Nonnull Attempt attempt) {
        ValidatorResult result = new ValidatorResult();
        String context = attempt.getClass().getSimpleName();

        ValidatorResult id = validateId(context, attempt.getId());
        if (!id.isValid()) {
            result.errorMessage().appendViolation(id.errorMessage().toString());
        }

        ValidatorResult typeURI = validateTypeURI(context, attempt.getType());
        if (!typeURI.isValid()) {
            result.errorMessage().appendViolation(typeURI.errorMessage().toString());
        }

        ValidatorResult actorId = validateActorId(context, attempt.getActorId());
        if (!actorId.isValid()) {
            result.errorMessage().appendViolation(actorId.errorMessage().toString());
        }

        ValidatorResult assignableId = validateAssignableId(context, attempt.getAssignableId());
        if (!assignableId.isValid()) {
            result.errorMessage().appendViolation(assignableId.errorMessage().toString());
        }

        ValidatorResult count = validateCount(context, attempt.getCount());
        if (!count.isValid()) {
            result.errorMessage().appendViolation(count.errorMessage().toString());
        }

        ValidatorResult start = validateStartTime(context, attempt.getStartedAtTime(), attempt.getEndedAtTime());
        if (!start.isValid()) {
            result.errorMessage().appendViolation(start.errorMessage().toString());
        }

        ValidatorResult duration = validateDuration(context, attempt.getStartedAtTime(),
                                                    attempt.getEndedAtTime(), attempt.getDuration());
        if (!duration.isValid()) {
            result.errorMessage().appendViolation(duration.errorMessage().toString());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper Attempt conformance:");
        }

        return result;
    }

    /**
     * Validate actor type.  TODO refactor if groups are introduced as agents.
     * @param context
     * @param actor
     * @return Validation result
     */
    public ValidatorResult validateActorIsPerson(@Nonnull String context, @Nonnull Agent actor) {
        return EntityValidatorUtils.context(context).validateType(actor, Person.class);
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
     * Validate count.
     * @param context
     * @param count
     * @return Validation result
     */
    public ValidatorResult validateCount(@Nonnull String context, @Nonnull int count) {
        return EntityValidatorUtils.context(context).validateCount(count);
    }

    /**
     * Validate start time.
     * @param context
     * @param start
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