package org.imsglobal.caliper.entities.reading;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.validators.ValidatorResult;
import org.imsglobal.caliper.validators.entities.DigitalResourceValidator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Reading extends org.imsglobal.caliper.entities.DigitalResource {

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
    protected Reading(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.learningResourceType = builder.learningResourceType;
        this.educationalUse = builder.educationalUse;
        this.timeRequired = builder.timeRequired;
        this.version = builder.version;

        ValidatorResult result = new DigitalResourceValidator<Reading>().validate(this);
        if (!result.isValid()) {
            throw new IllegalStateException(result.errorMessage().toString());
        }
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
     * @return the predominant type or kind characterizing the learning
     * resource, e.g. 'article', 'handout', 'presentation'.
     */
    @Nullable
    public String getLearningResourceType() {
        return learningResourceType;
    }

    /**
     * @return the purpose of a work in its educational context; e.g. 'assignment', 'group work'.
     */
    @Nullable
    public String getEducationalUse() {
        return educationalUse;
    }

    /**
     * @return Approximate or typical time it takes to work with or through this learning resource
     * for the typical intended target audience, e.g. 'P30M', 'P1H25M' (ISO 8601 duration format).
     */
    @Nullable
    public String getTimeRequired() {
        return timeRequired;
    }

    /**
     * @return the version of the CreativeWork embodied by a specified resource.
     */
    @Nullable
    public String getVersion() {
        return version;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends DigitalResource.Builder<T>  {
        private String type;
        private String learningResourceType;
        private String educationalUse;
        private String timeRequired;
        private String version;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(DigitalResource.Type.READING.uri());
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
        public Reading build() {
            return new Reading(this);
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