package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Caliper representation of a CreativeWork (https://schema.org/CreativeWork)
 *
 * We add learning specific attributes, including a list of
 * {@link LearningObjective} learning objectives and a list of
 * {@link String} keywords
 *
 * In addition, we add the following attributes:
 *
 * name (https://schema.org/name) -the name of the resource,
 *
 * about (https://schema.org/about) - the subject matter of the resource
 *
 * language (https://schema.org/Language) - Natural languages such as
 * Spanish, Tamil, Hindi, English, etc. and programming languages such
 * as Scheme and Lisp.
 */

@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "objectType",
    "properties",
    "alignedLearningObjective",
    "keyword",
    "partOf",
    "lastModifiedTime" })
public class DigitalResource extends Entity implements org.imsglobal.caliper.entities.schemadotorg.CreativeWork {

    public enum Type {
        ASSIGNABLE_DIGITAL_RESOURCE("http://purl.imsglobal.org/caliper/v1/AssignableDigitalResource"),
        EPUB_CHAPTER("http://www.idpf.org/epub/vocab/structure/#chapter"),
        EPUB_PART("http://www.idpf.org/epub/vocab/structure/#part"),
        EPUB_SUB_CHAPTER("http://www.idpf.org/epub/vocab/structure/#subchapter"),
        EPUB_VOLUME("http://www.idpf.org/epub/vocab/structure/#volume"),
        FRAME("http://purl.imsglobal.org/caliper/v1/Frame"),
        MEDIA_OBJECT("http://purl.imsglobal.org/caliper/v1/MediaObject"),
        READING("http://www.idpf.org/epub/vocab/structure"),
        WEB_PAGE("http://purl.imsglobal.org/caliper/v1/WebPage");

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

    @JsonProperty("objectType")
    private List<String> objectTypes = Lists.newArrayList();

    @JsonProperty("alignedLearningObjective")
    private List<LearningObjective> alignedLearningObjective = Lists.newArrayList();

    @JsonProperty("keyword")
    private List<String> keyword = Lists.newArrayList();

    @JsonProperty("partOf")
    private Object partOf;

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected DigitalResource(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.objectTypes = builder.objectTypes;
        this.alignedLearningObjective = builder.alignedLearningObjective;
        this.keyword = builder.keyword;
        this.partOf = builder.partOf;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return the objectTypes
     */
    public List<String> getObjectTypes() {
        return objectTypes;
    }

    /**
     * @return the aligned learning objectives
     */
    public List<LearningObjective> getAlignedLearningObjective() {
        return alignedLearningObjective;
    }

    /**
     * @return the keywords
     */
    public List<String> getKeyword() {
        return keyword;
    }

    /**
     * @return the parent reference.
     */
    public Object getPartOf() {
        return partOf;
    }

    /**
     * Initialize default parameter values in the builder.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Entity.Builder<T>  {
        private String type;
        private List<String> objectTypes = Lists.newArrayList();
        private List<LearningObjective> alignedLearningObjective = Lists.newArrayList();
        private List<String> keyword = Lists.newArrayList();
        private Object partOf;

        /*
         * Initialize type with default value.
         */
        public Builder() {
            type(Entity.Type.DIGITAL_RESOURCE.uri());
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
         * @param objectTypes
         * @return builder.
         */
        public T objectTypes(List<String> objectTypes) {
            this.objectTypes = objectTypes;
            return self();
        }

        /**
         * @param objectType
         * @return builder.
         */
        public T objectType(String objectType) {
            this.objectTypes.add(objectType);
            return self();
        }

        /**
         * @param alignedLearningObjective
         * @return builder.
         */
        public T alignedLearningObjective(List<LearningObjective> alignedLearningObjective) {
            this.alignedLearningObjective = alignedLearningObjective;
            return self();
        }

        /**
         * @param learningObjective
         * @return builder.
         */
        public T learningObjective(LearningObjective learningObjective) {
            this.alignedLearningObjective.add(learningObjective);
            return self();
        }

        /**
         * @param keyword
         * @return builder.
         */
        public T keyword(List<String> keyword) {
            this.keyword = keyword;
            return self();
        }

        /**
         * @param partOf
         * @return builder.
         */
        public T partOf(Object partOf) {
            this.partOf = partOf;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable profile object.
         * @return a new instance of the AssessmentProfile.
         */
        public DigitalResource build() {
            return new DigitalResource(this);
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