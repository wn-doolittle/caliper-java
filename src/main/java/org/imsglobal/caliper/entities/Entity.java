package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.entities.schemadotorg.Thing;

import java.util.Date;

/**
 * The base Caliper Entity.  Analogous to a schema.org Thing.
 */
@JsonPropertyOrder({ "@id", "@type", "name", "lastModifiedTime" })
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
        //RESPONSE("http://purl.imsglobal.org/caliper/v1/Response"),
        RESULT("http://purl.imsglobal.org/caliper/v1/Result"),
        SESSION("http://purl.imsglobal.org/caliper/v1/Session"),
        SOFTWARE_APPLICATION("http://purl.imsglobal.org/caliper/v1/SoftwareApplication"),
        TARGET("http://purl.imsglobal.org/caliper/v1/Target"),
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

    @JsonProperty("lastModifiedTime")
    private final Date lastModifiedTime;

    /**
     * @param builder apply builder object properties to the Entity object.
     */
    protected Entity(Builder<?> builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.name = builder.name;
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
     * @return the lastModifiedTime.
     */
    public Date getLastModifiedTime() {
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
        private Date lastModifiedTime;

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
         * @param lastModifiedTime
         * @return builder.
         */
        public T lastModifiedTime(Date lastModifiedTime) {
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