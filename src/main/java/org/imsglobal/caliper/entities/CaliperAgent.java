package org.imsglobal.caliper.entities;

import org.imsglobal.caliper.entities.foaf.Agent;

public class CaliperAgent extends CaliperEntity implements Agent {

    public enum Type {
        LIS_PERSON("http://purl.imsglobal.org/caliper/v1/LISPerson"),
        LIS_ORGANIZATION("http://purl.imsglobal.org/caliper/v1/LISOrganization"),
        SOFTWARE_APPLICATION("http://purl.imsglobal.org/caliper/v1/SoftwareApplication");

        private final String uri;

        /**
         * Private constructor
         * @param uri
         */
        private Type(final String uri) {
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
    protected CaliperAgent(Builder<?> builder) {
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
            type(CaliperEntity.Type.CALIPER_AGENT.uri());
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the SoftwareApplication.
         */
        public CaliperAgent build() {
            return new CaliperAgent(this);
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