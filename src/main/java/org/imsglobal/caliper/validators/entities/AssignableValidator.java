package org.imsglobal.caliper.validators.entities;

import org.imsglobal.caliper.entities.assignable.AssignableDigitalResource;
import org.imsglobal.caliper.validators.ValidatorResult;

import javax.annotation.Nonnull;

public class AssignableValidator<T extends AssignableDigitalResource> extends EntityValidator<T> {

    /**
     * Constructor
     */
    public AssignableValidator() {
        super();
    }

    /**
     * Convenience method that provides a rollup of Entity property validators.
     * @param assignable
     * @return Validation result
     */
    @Override
    public ValidatorResult validate(@Nonnull T assignable) {
        ValidatorResult result = new ValidatorResult();
        String context = assignable.getClass().getSimpleName();

        ValidatorResult id = validateId(context, assignable.getId());
        if (!id.isValid()) {
            result.errorMessage().appendViolation(id.errorMessage().toString());
        }

        ValidatorResult typeURI = validateTypeURI(context, assignable.getType());
        if (!typeURI.isValid()) {
            result.errorMessage().appendViolation(typeURI.errorMessage().toString());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper Assignable conformance:");
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
        return EntityValidatorUtils.context(context).validateTypeURI(typeURI, AssignableDigitalResource.Type.lookupConstantWithTypeURI(typeURI));
    }
}