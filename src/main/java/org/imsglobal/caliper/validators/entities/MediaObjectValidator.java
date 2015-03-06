package org.imsglobal.caliper.validators.entities;

import org.imsglobal.caliper.entities.media.MediaObject;
import org.imsglobal.caliper.validators.ValidatorResult;

import javax.annotation.Nonnull;

public class MediaObjectValidator<T extends MediaObject> extends EntityValidator<T> {

    /**
     * Constructor
     */
    public MediaObjectValidator() {
        super();
    }

    /**
     * Convenience method that provides a rollup of Annotation property validators.
     * @param media
     * @return Validation result
     */
    @Override
    public ValidatorResult validate(@Nonnull T media) {
        ValidatorResult result = new ValidatorResult();
        String context = media.getClass().getSimpleName();

        ValidatorResult id = validateId(context, media.getId());
        if (!id.isValid()) {
            result.errorMessage().appendViolation(id.errorMessage().toString());
        }

        ValidatorResult typeURI = validateTypeURI(context, media.getType());
        if (!typeURI.isValid()) {
            result.errorMessage().appendViolation(typeURI.errorMessage().toString());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper MediaObject conformance:");
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
        return EntityValidatorUtils.context(context).validateTypeURI(typeURI, MediaObject.Type.lookupConstantWithTypeURI(typeURI));
    }
}