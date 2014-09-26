package org.imsglobal.caliper.entities;

import org.imsglobal.caliper.entities.schemadotorg.CreativeWork;

@CaliperLearningContext
public class SoftwareApplication extends CaliperEntity implements CaliperAgent, CreativeWork {

    private final String type;

    /**
     * @param builder apply builder object properties to the SoftwareApplication object.
     */
    protected SoftwareApplication(Builder<?> builder) {
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
        private final String SOFTWAREAPP_TYPE = "http://purl.imsglobal.org/caliper/v1/SoftwareApplication";
        private String type;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(SOFTWAREAPP_TYPE);
        }

        /**
         * @param type
         * @return builder
         */
        @Override
        public T type(String type) {
            if (type.equals(SOFTWAREAPP_TYPE)) {
                this.type = type;
            } else {
                this.type = SOFTWAREAPP_TYPE;
            }
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the SoftwareApplication.
         */
        public SoftwareApplication build() {
            return new SoftwareApplication(this);
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