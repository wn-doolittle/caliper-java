package org.imsglobal.caliper.entities.assessment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.entities.assignable.AssignableDigitalResource;

/**
 * Caliper representation of an Assessment Item.
 * 
 * Part of the Assessment Metric Profile.
 */
@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "objectType",
    "alignedLearningObjective",
    "keywords",
    "isPartOf",
    "dateCreated",
    "dateModified",
    "datePublished",
    "dateToActivate",
    "dateToShow",
    "dateToStartOn",
    "dateToSubmit",
    "maxAttempts",
    "maxSubmits",
    "maxScore" })
public class AssessmentItem extends AssignableDigitalResource implements org.imsglobal.caliper.entities.qti.AssessmentItem {

    @JsonProperty("@type")
    private final String type;

    /**
     * @param builder apply builder object properties to the AssessmentItem object.
     */
    protected AssessmentItem(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends AssignableDigitalResource.Builder<T>  {
        private String type;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(AssignableDigitalResource.Type.ASSESSMENT_ITEM.uri());
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
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of AssessmentItem.
         */
        public AssessmentItem build() {
            return new AssessmentItem(this);
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