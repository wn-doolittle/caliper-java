package org.imsglobal.caliper.entities;

public class ActivityContext extends CaliperEntity {

    /**
     * @param builder apply builder object properties to the ActivityContext object.
     */
    protected ActivityContext(Builder<?> builder) {
        super(builder);
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperEntity.Builder<T>  {

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(CaliperEntity.Type.ACTIVITY_CONTEXT.uri());
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of ActivityContext.
         */
        public ActivityContext build() {
            return new ActivityContext(this);
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