package org.imsglobal.caliper.validators.entities;

import org.imsglobal.caliper.entities.outcome.Result;
import org.imsglobal.caliper.validators.ValidatorResult;

import javax.annotation.Nonnull;

public class ResultValidator extends EntityValidator<Result> {

    /**
     * Constructor
     */
    public ResultValidator() {
        super();
    }

    /**
     * Convenience method that provides a rollup of Result property validators.
     * @param outcomeResult
     * @return Validation result
     */
    @Override
    public ValidatorResult validate(@Nonnull Result outcomeResult) {
        ValidatorResult result = new ValidatorResult();
        String context = outcomeResult.getClass().getSimpleName();

        ValidatorResult id = validateId(context, outcomeResult.getId());
        if (!id.isValid()) {
            result.errorMessage().appendViolation(id.errorMessage().toString());
        }

        ValidatorResult typeURI = validateTypeURI(context, outcomeResult.getType());
        if (!typeURI.isValid()) {
            result.errorMessage().appendViolation(typeURI.errorMessage().toString());
        }

        ValidatorResult actorId = validateActorId(context, outcomeResult.getActorId());
        if (!actorId.isValid()) {
            result.errorMessage().appendViolation(actorId.errorMessage().toString());
        }

        ValidatorResult assignableId = validateAssignableId(context, outcomeResult.getAssignableId());
        if (!assignableId.isValid()) {
            result.errorMessage().appendViolation(assignableId.errorMessage().toString());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper Result conformance:");
        }

        return result;
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
}