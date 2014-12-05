package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * The base Caliper Entity.  Analogous to a schema.org Thing.
 */
@JsonPropertyOrder({ "@id", "@type", "name", "properties", "lastModifiedTime" })
public abstract class Entity {

    public enum Type {
        AGENT("http://purl.imsglobal.org/caliper/v1/Agent"),
        ATTEMPT("http://purl.imsglobal.org/caliper/v1/Attempt"),
        DIGITAL_RESOURCE("http://purl.imsglobal.org/caliper/v1/DigitalResource"),
        ENTITY("http://purl.imsglobal.org/caliper/v1/Entity"),
        LEARNING_OBJECTIVE("http://purl.imsglobal.org/caliper/v1/LearningObjective"),
        RESPONSE("http://purl.imsglobal.org/caliper/v1/Response"),
        RESULT("http://purl.imsglobal.org/caliper/v1/Result"),
        VIEW("http://purl.imsglobal.org/caliper/v1/View");

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

    @Reference
    @JsonProperty("@id")
    protected final String id;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("properties")
    private final ImmutableMap<String, Object> properties;

    @JsonProperty("lastModifiedTime")
    private final long lastModifiedTime;

    /**
     * @param builder apply builder object properties to the Entity object.
     */
    protected Entity(Builder<?> builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.name = builder.name;
        this.properties = ImmutableMap.copyOf(builder.properties);
        //this.properties = ImmutableMap.<String, Object>builder().putAll(builder.properties).build();
        //this.properties = Collections.unmodifiableMap(properties);
        this.lastModifiedTime = builder.lastModifiedTime;
    }

    /**
     * @return the id.
     */
    public String getId() {
        return id;
    }

    /**
     * @return the type.
     */
    public String getType() {
        return type;
    }

    /**
     * @return human readable identifier.
     */
    public String getName() {
        return name;
    }

    /**
     * Return an immutable view of the properties map.
     * @return properties.
     */
    public ImmutableMap<String, Object> getProperties() {
        return properties;
    }

    /**
     * @return the lastModifiedTime.
     */
    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> {
        private String id;
        private String type;
        private String name;
        private Map<String, Object> properties = Maps.newHashMap();
        private long lastModifiedTime;

        protected abstract T self();

        /**
         * Constructor
         */
        public Builder() {
            type(Type.ENTITY.uri());
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
         * @param type
         * @return builder.
         */
        private T type(String type) {
            this.type = type;
            return self();
        }

        /**
         * @param name
         * @return builder.
         */
        public T name(String name) {
            this.name = name;
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
         * @param key
         * @param value
         * @return builder.
         */
        public T property(String key, Object value) {
            this.properties.put(key, value);
            return self();
        }

        /**
         * @param lastModifiedTime
         * @return builder.
         */
        public T lastModifiedTime(long lastModifiedTime) {
            this.lastModifiedTime = lastModifiedTime;
            return self();
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
}