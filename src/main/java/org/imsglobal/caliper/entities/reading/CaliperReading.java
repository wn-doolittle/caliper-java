package org.imsglobal.caliper.entities.reading;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.CaliperDigitalResource;

public class CaliperReading extends CaliperDigitalResource {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("learningResourceType")
    private final String learningResourceType;

    @JsonProperty("educationalUse")
    private final String educationalUse;

    @JsonProperty("timeRequired")
    private final String timeRequired;

    @JsonProperty("version")
    private final String version;

    /**
     * @param builder apply builder object properties to the CaliperAssessment object.
     */
    protected CaliperReading(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.learningResourceType = builder.learningResourceType;
        this.educationalUse = builder.educationalUse;
        this.timeRequired = builder.timeRequired;
        this.version = builder.version;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return the predominant type or kind characterizing the learning
     * resource, e.g. 'article', 'handout', 'presentation'.
     */
    public String getLearningResourceType() {
        return learningResourceType;
    }

    /**
     * @return the purpose of a work in its educational context; e.g. 'assignment', 'group work'.
     */
    public String getEducationalUse() {
        return educationalUse;
    }

    /**
     * @return Approximate or typical time it takes to work with or through this learning resource
     * for the typical intended target audience, e.g. 'P30M', 'P1H25M' (ISO 8601 duration format).
     */
    public String getTimeRequired() {
        return timeRequired;
    }

    /**
     * @return the version of the CreativeWork embodied by a specified resource.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperDigitalResource.Builder<T>  {
        private String type;
        private String learningResourceType;
        private String educationalUse;
        private String timeRequired;
        private String version;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(CaliperDigitalResource.Type.CALIPER_READING.uri());
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
         * @param learningResourceType
         * @return builder
         */
        public T learningResourceType(String learningResourceType) {
            this.learningResourceType = learningResourceType;
            return self();
        }

        /**
         * @param educationalUse
         * @return builder
         */
        public T educationalUse(String educationalUse) {
            this.educationalUse = educationalUse;
            return self();
        }

        /**
         * @param timeRequired
         * @return builder
         */
        public T timeRequired(String timeRequired) {
            this.timeRequired = timeRequired;
            return self();
        }

        /**
         * @param version
         * @return builder
         */
        public T version(String version) {
            this.version = version;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of CaliperAssessment.
         */
        public CaliperReading build() {
            return new CaliperReading(this);
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