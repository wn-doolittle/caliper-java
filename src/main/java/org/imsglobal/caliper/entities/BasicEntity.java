package org.imsglobal.caliper.entities;

/**
 * Concrete implementation of a generic Entity.
 */
public class BasicEntity extends AbstractEntity {

    /**
     * @param builder apply builder object properties to the object.
     */
    protected BasicEntity(Builder<?> builder) {
        super(builder);
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> extends AbstractEntity.Builder<T> {

        /**
         * Constructor
         */
        public Builder() {
            super.type(EntityType.ENTITY);
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the BasicEntity.
         */
        public BasicEntity build() {
            return new BasicEntity(this);
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