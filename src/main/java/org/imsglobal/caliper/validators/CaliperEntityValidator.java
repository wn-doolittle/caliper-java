package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.entities.Entity;

import javax.annotation.Nonnull;

public interface CaliperEntityValidator<T extends Entity> {

    /**
     * Convenience method that provides a rollup of Entity property validators.
     * @param entity
     * @return
     */
    ValidatorResult validate(@Nonnull T entity);

    /**
     * Validate context URI.
     * @param context
     * @param id
     * @return Validation result
     */
    ValidatorResult validateId(@Nonnull String context, @Nonnull String id);

    /**
     * Validate type URI.
     * @param context
     * @param typeURI
     * @return Validation result
     */
    ValidatorResult validateTypeURI(@Nonnull String context, @Nonnull String typeURI);
}