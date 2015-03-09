package org.imsglobal.caliper.entities.annotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableMap;
import org.imsglobal.caliper.entities.Entity;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * The super-class of all Annotation types.
 * 
 * Direct sub-types can include - Highlight, Attachment, etc. - all of
 * which are specified in the Caliper Annotation Metric Profile
 */
@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "description",
    "extensions",
    "dateCreated",
    "dateModified",
    "annotatedId" })
public abstract class Annotation extends Entity implements org.imsglobal.caliper.entities.Generatable {

    public enum Type {
        ANNOTATION("http://purl.imsglobal.org/caliper/v1/Annotation"),
        BOOKMARK_ANNOTATION("http://purl.imsglobal.org/caliper/v1/BookmarkAnnotation"),
        HIGHLIGHT_ANNOTATION("http://purl.imsglobal.org/caliper/v1/HighlightAnnotation"),
        SHARED_ANNOTATION("http://purl.imsglobal.org/caliper/v1/SharedAnnotation"),
        TAG_ANNOTATION("http://purl.imsglobal.org/caliper/v1/TagAnnotation");

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
         * @return Event.Type enum
         */
        public static Annotation.Type lookupConstantWithTypeURI(String uri) {
            return lookup.get(uri);
        }
    }

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("annotatedId")
    private String annotatedId;

    /**
     * @param builder apply builder object properties to the Annotation object.
     */
    protected Annotation(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.annotatedId = builder.annotatedId;
    }

    /**
     * @return the type
     */
    @Override
    @Nonnull
    public String getType() {
        return type;
    }

    /**
     * @return the annotated object's identifier
     */
    @Nonnull
    public String getAnnotatedId() {
        return annotatedId;
    }


    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Entity.Builder<T>  {
        private String type;
        private String annotatedId;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(Annotation.Type.ANNOTATION.uri());
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
         * @param annotatedId
         * @return builder.
         */
        public T annotatedId(String annotatedId) {
            this.annotatedId = annotatedId;
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