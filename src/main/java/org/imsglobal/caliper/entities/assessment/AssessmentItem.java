package org.imsglobal.caliper.entities.assessment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.entities.assignable.AssignableDigitalResource;
import org.imsglobal.caliper.entities.assignable.Response;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Caliper representation of an Assessment Item.  Part of the Assessment Metric Profile.
 */
@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "description",
    "objectType",
    "alignedLearningObjective",
    "keywords",
    "isPartOf",
    "properties",
    "dateCreated",
    "dateModified",
    "datePublished",
    "dateToActivate",
    "dateToShow",
    "dateToStartOn",
    "dateToSubmit",
    "maxAttempts",
    "maxSubmits",
    "maxScore",
    "cardinality",
    "correctResponse",
    "isTimeDependent" })
public class AssessmentItem extends AssignableDigitalResource implements org.imsglobal.caliper.entities.qti.AssessmentItem {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("cardinality")
    private final String cardinality;

    @JsonProperty("correctResponse")
    private final Response correctResponse;

    @JsonProperty("isTimeDependent")
    private final boolean isTimeDependent;

    /**
     * @param builder apply builder object properties to the AssessmentItem object.
     */
    protected AssessmentItem(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.cardinality = builder.cardinality;
        this.correctResponse = builder.correctResponse;
        this.isTimeDependent = builder.isTimeDependent;

        //TODO validation: check cardinality, times
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
     * The number of sub-actions required of an actor in order to generate a response.
     * Single: a single actor response with each item consisting of a single response-type.
     * Multiple: one or more actor responses with each item consisting of a single response-type.
     * Ordered: One or more actor responses with each item consisting of a single response-type,
     * and the order of selection being significant.
     * @return the cardinality of the response (single, multiple, ordered)
     */
    @Nullable
    public String getCardinality() {
        return cardinality;
    }

    /**
     * @return the correct response
     */
    @Nullable
    public Response getCorrectResponse() {
        return correctResponse;
    }

    /**
     * Indicate whether or not the time taken to respond is important and must be recorded.
     * This could be used by responses which set a sequence of events to be completed
     * in a predefined period or where the response sequence is determined by the
     * time taken to complete certain responses.
     *
     * @return true/false
     */
    @Nonnull
    public boolean getIsTimeDependent() {
        return isTimeDependent;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends AssignableDigitalResource.Builder<T>  {
        private String type = AssignableDigitalResource.Type.ASSESSMENT_ITEM.uri();
        private String cardinality;
        private Response correctResponse;
        private boolean isTimeDependent = false;

        /**
         * Constructor
         */
        public Builder() {
        }

        /**
         * The user may override the default AssessmentItem type with a more specific Caliper conformant type URI.
         * @param type
         * @return builder.
         */
        public T type(String type) {
            this.type = type;
            return self();
        }

        /**
         * @param cardinality
         * @return builder.
         */
        public T cardinality(String cardinality) {
            this.cardinality = cardinality;
            return self();
        }

        /**
         * @param correctResponse
         * @return builder
         */
        public T correctResponse(Response correctResponse) {
            this.correctResponse = correctResponse;
            return self();
        }

        /**
         * @param isTimeDependent
         * @return builder.
         */
        public T isTimeDependent(boolean isTimeDependent) {
            this.isTimeDependent = isTimeDependent;
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