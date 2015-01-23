package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
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
    "alignedLearningObjective",
    "keyword",
    "partOf",
    "lastModifiedTime" })
public class DigitalResource extends Entity implements org.imsglobal.caliper.entities.schemadotorg.CreativeWork,
                                                       org.imsglobal.caliper.entities.Targetable {

    public enum Type {
        ASSIGNABLE_DIGITAL_RESOURCE("http://purl.imsglobal.org/caliper/v1/AssignableDigitalResource"),
        EPUB_CHAPTER("http://www.idpf.org/epub/vocab/structure/#chapter"),
        EPUB_PART("http://www.idpf.org/epub/vocab/structure/#part"),
        EPUB_SUB_CHAPTER("http://www.idpf.org/epub/vocab/structure/#subchapter"),
        EPUB_VOLUME("http://www.idpf.org/epub/vocab/structure/#volume"),
        FRAME("http://purl.imsglobal.org/caliper/v1/Frame"),
        MEDIA_LOCATION("http://purl.imsglobal.org/caliper/v1/MediaLocation"),
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
    private final ImmutableList<String> objectTypes;

    @JsonProperty("alignedLearningObjective")
    private final ImmutableList<LearningObjective> learningObjectives;

    @JsonProperty("keyword")
    private final ImmutableList<String> keywords;

    @JsonProperty("partOf")
    private final Object partOf;

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected DigitalResource(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.objectTypes = ImmutableList.copyOf(builder.objectTypes);
        //this.objectTypes = ImmutableList.<String>builder().addAll(objectTypes).build();
        this.learningObjectives = ImmutableList.copyOf(builder.learningObjectives);
        //this.learningObjectives = ImmutableList.<LearningObjective>builder().addAll(learningObjectives).build();
        this.keywords = ImmutableList.copyOf(builder.keywords);
        //this.keywords = ImmutableList.<String>builder().addAll(keywords).build();
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
     * Return an immutable view of the objectTypes list.
     * @return the objectTypes
     */
    public ImmutableList<String> getObjectTypes() {
        return objectTypes;
    }

    /**
     * Return an immutable view of the learningObjectives list.
     * @return the learning objectives
     */
    public ImmutableList<LearningObjective> getLearningObjectives() {
        return learningObjectives;
    }

    /**
     * Return an immutable view of the keywords list.
     * @return the keywords
     */
    public ImmutableList<String> getKeywords() {
        return keywords;
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
        private List<LearningObjective> learningObjectives = Lists.newArrayList();
        private List<String> keywords = Lists.newArrayList();
        private Object partOf;

        /*
         * Constructor
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
         * @param learningObjectives
         * @return builder.
         */
        public T learningObjectives(List<LearningObjective> learningObjectives) {
            this.learningObjectives = learningObjectives;
            return self();
        }

        /**
         * @param learningObjective
         * @return builder.
         */
        public T learningObjective(LearningObjective learningObjective) {
            this.learningObjectives.add(learningObjective);
            return self();
        }

        /**
         * @param keywords
         * @return builder.
         */
        public T keywords(List<String> keywords) {
            this.keywords = keywords;
            return self();
        }

        /**
         * @param keyword
         * @return builder.
         */
        public T keyword(String keyword) {
            this.keywords.add(keyword);
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