package org.imsglobal.caliper.entities.resource;

import org.imsglobal.caliper.entities.EntityType;

/**
 * A Generic DigitalResource.
 */
public class DigitalResource extends AbstractDigitalResource {

    /**
     * @param builder apply builder object properties to the object.
     */
    protected DigitalResource(Builder<?> builder) {
        super(builder);
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> extends AbstractDigitalResource.Builder<T> {

        /**
         * Constructor
         */
        public Builder() {
            super.type(EntityType.DIGITAL_RESOURCE);
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the DigitalResource.
         */
        public DigitalResource build() {
            return new DigitalResource(this);
        }
    }

    /**
     *
     */
    private static class Builder2 extends Builder<Builder2> {
        @Override
        protected Builder2 self() {
            return this;
        }
    }

    /**
     * Static factory method.
     * @return a new instance of the builder.
     */
    public static Builder<?> builder() {
        return new Builder2();
    }
}