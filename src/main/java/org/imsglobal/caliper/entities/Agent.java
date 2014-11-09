package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Agent extends Entity implements org.imsglobal.caliper.entities.foaf.Agent {

    public enum Type {
        LIS_PERSON("http://purl.imsglobal.org/caliper/v1/lis/Person"),
        LIS_ORGANIZATION("http://purl.imsglobal.org/caliper/v1/lis/Organization"),
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

    @JsonProperty("@type")
    private final String type;

    /**
     * @param builder apply builder object properties to the SoftwareApplication object.
     */
    protected Agent(Builder<?> builder) {
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
    public static abstract class Builder<T extends Builder<T>> extends Entity.Builder<T>  {
        private String type;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(Entity.Type.AGENT.uri());
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(String type) {
            this.type = type;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the SoftwareApplication.
         */
        public Agent build() {
            return new Agent(this);
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