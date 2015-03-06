package org.imsglobal.caliper.validators.entities;

import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.validators.ValidatorResult;

import javax.annotation.Nonnull;

public class DigitalResourceValidator<T extends DigitalResource> extends EntityValidator<T> {

    /**
     * Constructor
     */
    public DigitalResourceValidator() {
        super();
    }

    /**
     * Convenience method that provides a rollup of Entity property validators.
     * @param resource
     * @return Validation result
     */
    @Override
    public ValidatorResult validate(@Nonnull T resource) {
        ValidatorResult result = new ValidatorResult();
        String context = resource.getClass().getSimpleName();

        ValidatorResult id = validateId(context, resource.getId());
        if (!id.isValid()) {
            result.errorMessage().appendViolation(id.errorMessage().toString());
        }

        ValidatorResult typeURI = validateTypeURI(context, resource.getType());
        if (!typeURI.isValid()) {
            result.errorMessage().appendViolation(typeURI.errorMessage().toString());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper DigitalResource conformance:");
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
        return EntityValidatorUtils.context(context).validateTypeURI(typeURI, DigitalResource.Type.lookupConstantWithTypeURI(typeURI));
    }
}
