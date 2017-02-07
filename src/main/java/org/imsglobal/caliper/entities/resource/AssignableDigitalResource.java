package org.imsglobal.caliper.entities.resource;

import org.imsglobal.caliper.entities.EntityType;

/**
 * Concrete implementation of a generic AssignableDigitalResource.
 */
public class AssignableDigitalResource extends AbstractAssignableDigitalResource {

    /**
     * @param builder apply builder object properties to the CaliperAssignableDigitalResource object.
     */
    protected AssignableDigitalResource(Builder<?> builder) {
        super(builder);
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends AbstractAssignableDigitalResource.Builder<T>  {

        /**
         * Initialize type with default value.
         */
        public Builder() {
            super.type(EntityType.ASSIGNABLE_DIGITAL_RESOURCE);
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of AssignableDigitalResource.
         */
        public AssignableDigitalResource build() {
            return new AssignableDigitalResource(this);
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