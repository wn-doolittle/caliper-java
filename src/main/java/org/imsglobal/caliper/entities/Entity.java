package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.imsglobal.caliper.entities.schemadotorg.Thing;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * The base Caliper Entity.  Analogous to a schema.org Thing.
 */
@JsonPropertyOrder({ "@id", "@type", "name", "description", "extensions", "dateCreated", "dateModified" })
public abstract class Entity implements Thing {

    public enum Type {
        AGENT("http://purl.imsglobal.org/caliper/v1/Agent"),
        ATTEMPT("http://purl.imsglobal.org/caliper/v1/Attempt"),
        DIGITAL_RESOURCE("http://purl.imsglobal.org/caliper/v1/DigitalResource"),
        ENTITY("http://purl.imsglobal.org/caliper/v1/Entity"),
        GENERATED("http://purl.imsglobal.org/caliper/v1/Generated"),
        LEARNING_OBJECTIVE("http://purl.imsglobal.org/caliper/v1/LearningObjective"),
        LIS_PERSON("http://purl.imsglobal.org/caliper/v1/lis/Person"),
        LIS_ORGANIZATION("http://purl.imsglobal.org/caliper/v1/lis/Organization"),
        RESPONSE("http://purl.imsglobal.org/caliper/v1/Response"),
        RESULT("http://purl.imsglobal.org/caliper/v1/Result"),
        SESSION("http://purl.imsglobal.org/caliper/v1/Session"),
        SOFTWARE_APPLICATION("http://purl.imsglobal.org/caliper/v1/SoftwareApplication"),
        TARGET("http://purl.imsglobal.org/caliper/v1/Target"),
        VIEW("http://purl.imsglobal.org/caliper/v1/View");

        private final String uri;
        private static Map<String, Type> lookup;

        /**
         * Create reverse lookup hash map
         */
        static {
            Map<String, Type> map = new HashMap<String, Type>();
            for (Type constants : Type.values()) {
                map.put(constants.uri(), constants);
            }
            lookup = ImmutableMap.copyOf(map);
        }

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

        /**
         * Retrieve enum type from reverse lookup map.
         * @param uri
         * @return Entity.Type enum
         */
        public static Entity.Type lookupConstantWithTypeURI(String uri) {
            return lookup.get(uri);
        }
    }

    @Reference
    @JsonProperty("@id")
    protected final String id;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("extensions")
    private final Map<String, String> extensions;

    @JsonProperty("dateCreated")
    private final DateTime dateCreated;

    @JsonProperty("dateModified")
    private final DateTime dateModified;

    /**
     * @param builder apply builder object properties to the Entity object.
     */
    protected Entity(Builder<?> builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.name = builder.name;
        this.extensions = ImmutableMap.copyOf(builder.extensions);
        this.description = builder.description;
        this.dateCreated = builder.dateCreated;
        this.dateModified = builder.dateModified;
    }

    /**
     * @return the id.
     */
    @Nonnull
    public String getId() {
        return id;
    }

    /**
     * @return the type.
     */
    @Nonnull
    public String getType() {
        return type;
    }

    /**
     * @return name.
     */
    @Nullable
    public String getName() {
        return name;
    }

    /**
     * @return description.
     */
    @Nullable
    public String getDescription() {
        return description;
    }

    /**
     * @return custom extensions (key/value pairs).
     */
    @Nullable
    public Map<String, String> getExtensions() {
        return extensions;
    }

    /**
     * @return date created.
     */
    @Nullable
    public DateTime getDateCreated()
    {
        return dateCreated;
    }

    /**
     * @return the date modified.
     */
    @Nullable
    public DateTime getDateModified() {
        return dateModified;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> {
        private String id;
        private String type;
        private String name;
        private String description;
        private Map<String, String> extensions = Maps.newHashMap();
        private DateTime dateCreated;
        private DateTime dateModified;

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
         * @param description
         * @return builder.
         */
        public T description(String description) {
            this.description = description;
            return self();
        }

        /**
         * @param key
         * @param value
         * @return builder
         */
        public T extension(String key, String value) {
            this.extensions.put(key, value);
            return self();
        }

        /**
         * @param extensions
         * @return builder
         */
        public T extensions(Map<String, String> extensions) {
            this.extensions.putAll(extensions);
            return self();
        }

        /**
         * @param dateCreated
         * @return builder.
         */
        public T dateCreated(DateTime dateCreated) {
            this.dateCreated = dateCreated;
            return self();
        }

        /**
         * @param dateModified
         * @return builder.
         */
        public T dateModified(DateTime dateModified) {
            this.dateModified = dateModified;
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