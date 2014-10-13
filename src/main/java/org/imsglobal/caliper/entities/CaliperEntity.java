package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.Maps;
import java.util.Map;

/**
 * The base Caliper Entity.  Analogous to a schema.org Thing.
 */
@JsonPropertyOrder({ "@id", "@type", "lastModifiedTime" })
public class CaliperEntity {

    public enum Type {
        ACTIVITY_CONTEXT("http://purl.imsglobal.org/caliper/v1/ActivityContext"),
        ATTEMPT("http://purl.imsglobal.org/caliper/v1/Attempt"),
        CALIPER_AGENT("http://purl.imsglobal.org/caliper/v1/CaliperAgent"),
        CALIPER_DIGITAL_RESOURCE("http://purl.imsglobal.org/caliper/v1/CaliperDigitalResource"),
        CALIPER_ENTITY("http://purl.imsglobal.org/caliper/v1/CaliperEntity"),
        LEARNING_OBJECTIVE("http://purl.imsglobal.org/caliper/v1/LearningObjective"),
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

    @CaliperReference
    @JsonProperty("@id")
    protected final String id;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("lastModifiedTime")
    private long lastModifiedAt;

    @JsonIgnore
    @JsonProperty("properties")
    private Map<String, Object> properties = Maps.newHashMap();

    /**
     * @param builder apply builder object properties to the CaliperEntity object.
     */
    protected CaliperEntity(Builder<?> builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.lastModifiedAt = builder.lastModifiedAt;
        this.properties = builder.properties;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the lastModifiedAt
     */
    public long getLastModifiedAt() {
        return lastModifiedAt;
    }

    /**
     * @return the properties
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> {
        private String id;
        private String type;
        private long lastModifiedAt;
        private Map<String, Object> properties = Maps.newHashMap();

        protected abstract T self();

        /**
         * Initialize type with default value.
         */
        public Builder() {
            this.type(Type.CALIPER_ENTITY.uri());
        }

        /**
         * @param id
         * @return builder.
         */
        public T id(String id) {
            this.id = id;
            return self();
        }

        /**
         * Private scope
         * @param type
         * @return builder.
         */
        public T type(String type) {
            this.type = type;
            return self();
        }

        /**
         * @param lastModifiedAt
         * @return builder.
         */
        public T lastModifiedAt(long lastModifiedAt) {
            this.lastModifiedAt = lastModifiedAt;
            return self();
        }

        /**
         * @param properties
         * @return builder.
         */
        public T properties(Map<String, Object> properties) {
            this.properties = properties;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable CaliperEntity object.
         * @return a new instance of CaliperEntity.
         */
        public CaliperEntity build() {
            return new CaliperEntity(this);
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