package org.imsglobal.caliper.entities;

import org.imsglobal.caliper.entities.schemadotorg.CreativeWork;

@CaliperLearningContext
public class SoftwareApplication extends CaliperEntity implements CaliperAgent, CreativeWork {

    public enum Identifier {
        TYPE("http://purl.imsglobal.org/caliper/v1/SoftwareApplication");

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
     * @param builder apply builder object properties to the SoftwareApplication object.
     */
    protected SoftwareApplication(Builder<?> builder) {
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
            type(SoftwareApplication.Identifier.TYPE.uri());
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