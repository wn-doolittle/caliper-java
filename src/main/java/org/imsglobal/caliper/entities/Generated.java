package org.imsglobal.caliper.entities;

public class Generated extends CaliperEntity {
    private final String type;

    /**
     * @param builder apply builder object properties to the Generated object.
     */
    protected Generated(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperEntity.Builder<T>  {
        private static final String GENERATED_TYPE = "http://purl.imsglobal.org/caliper/v1/Generated";
        private String type;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(GENERATED_TYPE);
        }

        /**
         * @param type
         * @return the IMS Global type reference URI.
         */
        @Override
        public T type(String type) {
            if (type.equals(GENERATED_TYPE)) {
                this.type = type;
            } else {
                this.type = GENERATED_TYPE;
            }
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of Generated.
         */
        public Generated build() {
            return new Generated(this);
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