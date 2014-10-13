package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.schemadotorg.CreativeWork;

import java.util.ArrayList;
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
        "lastModifiedTime",
        "properties",
        "name",
        "alignedLearningObjective",
        "keyword",
        "objectType",
        "partOf" })
public class CaliperDigitalResource extends CaliperEntity implements CreativeWork {

    public enum Type {
        CALIPER_ASSIGNABLE_DIGITAL_RESOURCE("http://purl.imsglobal.org/caliper/v1/CaliperAssignableDigitalResource"),
        CALIPER_MEDIA_OBJECT("http://purl.imsglobal.org/caliper/v1/CaliperMediaObject"),
        EPUB_CHAPTER("http://www.idpf.org/epub/vocab/structure/#chapter"),
        EPUB_PART("http://www.idpf.org/epub/vocab/structure/#part"),
        EPUB_SUB_CHAPTER("http://www.idpf.org/epub/vocab/structure/#subchapter"),
        EPUB_VOLUME("http://www.idpf.org/epub/vocab/structure/#volume");

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

    @JsonProperty("name")
    private String name;

    @JsonProperty("partOf")
    private Object partOf;

    @JsonProperty("objectType")
    private Object objectType;

    @JsonProperty("alignedLearningObjective")
    private List<LearningObjective> alignedLearningObjective = Lists.newArrayList();

    @JsonProperty("keyword")
    private List<String> keyword = Lists.newArrayList();

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected CaliperDigitalResource(Builder<?> builder) {
        super(builder);
        this.name = builder.name;
        this.partOf = builder.partOf;
        this.objectType = builder.objectType;
        this.alignedLearningObjective = builder.alignedLearningObjective;
        this.keyword = builder.keyword;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the parent reference.
     */
    public Object getPartOf() {
        return partOf;
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
     * @return the objectType
     */
    public Object getObjectType() {
        return objectType;
    }

    /**
     * Initialize default parameter values in the builder.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperEntity.Builder<T>  {
        private String name;
        private Object partOf;
        private List<LearningObjective> alignedLearningObjective = new ArrayList<LearningObjective>();
        private List<String> keyword = new ArrayList<String>();
        private Object objectType;

        /*
         * Initialize type with default value.
         */
        public Builder() {
            type(CaliperEntity.Type.CALIPER_DIGITAL_RESOURCE.uri());
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
         * @param partOf
         * @return builder.
         */
        public T partOf(Object partOf) {
            this.partOf = partOf;
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
         * @param keyword
         * @return builder.
         */
        public T keyword(List<String> keyword) {
            this.keyword = keyword;
            return self();
        }

        /**
         * @param objectType
         * @return builder.
         */
        public T objectType(Object objectType) {
            this.objectType = objectType;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable profile object.
         * @return a new instance of the AssessmentProfile.
         */
        public CaliperDigitalResource build() {
            return new CaliperDigitalResource(this);
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