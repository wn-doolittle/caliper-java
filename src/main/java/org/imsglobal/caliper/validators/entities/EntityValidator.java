package org.imsglobal.caliper.validators.entities;

import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.validators.CaliperEntityValidator;
import org.imsglobal.caliper.validators.ValidatorResult;

import javax.annotation.Nonnull;

public class EntityValidator<T extends Entity> implements CaliperEntityValidator<T> {

    /**
     * Constructor
     */
    public EntityValidator () {

    }

    /**
     * Convenience method that provides a rollup of Entity property validators.
     * @param entity
     * @return
     */
    public ValidatorResult validate(@Nonnull T entity) {
        ValidatorResult result = new ValidatorResult();
        String context = entity.getClass().getSimpleName();

        ValidatorResult id = validateId(context, entity.getId());
        if (!id.isValid()) {
            result.errorMessage().appendViolation(id.errorMessage().toString());
        }

        ValidatorResult typeURI = validateTypeURI(context, entity.getType());
        if (!typeURI.isValid()) {
            result.errorMessage().appendViolation(typeURI.errorMessage().toString());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper Entity conformance:");
        }

        return result;
    }

    /**
     * Check the identifier.
     * @param context
     * @param id
     * @return Validation result
     */
    public ValidatorResult validateId(@Nonnull String context, @Nonnull String id) {
        return EntityValidatorUtils.context(context).validateId(id);
    }

    /**
     * Check the type URI.
     * @param context
     * @param typeURI
     * @return Validation result
     */
    public ValidatorResult validateTypeURI(@Nonnull String context, @Nonnull String typeURI) {
        return EntityValidatorUtils.context(context).validateTypeURI(typeURI, Entity.Type.lookupConstantWithTypeURI(typeURI));
    }
}