package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.events.Event;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface CaliperEventValidator<T extends Event> {

    /**
     * Convenience method that provides a rollup of Event property validators.
     * @param event
     * @return
     */
    ValidatorResult validate(@Nonnull T event);

    /**
     * Validate context URI.
     * @param context
     * @param contextURI
     * @return Validator result
     */
    ValidatorResult validateContextURI(@Nonnull String context, @Nonnull String contextURI);

    /**
     * Validate type URI.
     * @param context
     * @param typeURI
     * @return Validator result
     */
    ValidatorResult validateTypeURI(@Nonnull String context, @Nonnull String typeURI);

    /**
     * Validate start time.
     * @param context
     * @param start
     * @param end
     * @return Validator result
     */
    ValidatorResult validateStartTime(@Nonnull String context, @Nonnull DateTime start, @Nullable DateTime end);
}