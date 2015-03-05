package org.imsglobal.caliper.validators.entities;

import org.imsglobal.caliper.entities.annotation.Annotation;
import org.imsglobal.caliper.validators.ValidatorResult;

import javax.annotation.Nonnull;

public class AnnotationValidator<T extends Annotation> extends EntityValidator<T> {

    /**
     * Constructor
     */
    public AnnotationValidator() {
        super();
    }

    /**
     * Convenience method that provides a rollup of Annotation property validators.
     * @param annotation
     * @return Validation result
     */
    @Override
    public ValidatorResult validate(@Nonnull T annotation) {
        ValidatorResult result = new ValidatorResult();
        String context = annotation.getClass().getSimpleName();

        ValidatorResult id = validateId(context, annotation.getId());
        if (!id.isValid()) {
            result.errorMessage().appendViolation(id.errorMessage().toString());
        }

        ValidatorResult typeURI = validateTypeURI(context, annotation.getType());
        if (!typeURI.isValid()) {
            result.errorMessage().appendViolation(typeURI.errorMessage().toString());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper Annotation conformance:");
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
        return EntityValidatorUtils.context(context).validateTypeURI(typeURI, Annotation.Type.lookupConstantWithTypeURI(typeURI));
    }

    //TODO check annotations that specify a selector
}