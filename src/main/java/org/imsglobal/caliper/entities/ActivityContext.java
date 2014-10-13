package org.imsglobal.caliper.entities;

public class ActivityContext extends CaliperEntity {

    public enum Identifier {
        TYPE("http://purl.imsglobal.org/caliper/v1/ActivityContext");

        private final String uri;

        /**
         * Private constructor
         * @param uri
         */
        private Identifier(final String uri) {
            this.uri = uri;
        }

        /**
         * @return URI string
         */
        public String uri() {
            return uri;
        }
    }

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
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(ActivityContext.Identifier.TYPE.uri());
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