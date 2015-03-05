package org.imsglobal.caliper.validators.entities;

import org.imsglobal.caliper.entities.assignable.AssignableDigitalResource;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.lis.Person;
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

        /**
         ValidatorResult assignable = validateAssignable(context, outcomeResult.getAssignable());
         if (!assignable.isValid()) {
         result.errorMessage().appendViolation(assignable.errorMessage().toString());
         }

         ValidatorResult actor = validateActorIsPerson(context, outcomeResult.getActor());
         if (!actor.isValid()) {
         result.errorMessage().appendViolation(actor.errorMessage().toString());
         }
         */

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper Result conformance:");
        }

        return result;
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
     * @return
     */
    public ValidatorResult validateAssignable(@Nonnull String context, @Nonnull AssignableDigitalResource assignable) {
        return new AssignableValidator<>().validate(assignable);
    }
}