package org.imsglobal.caliper.entities.assessment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.entities.EntityType;
import org.imsglobal.caliper.entities.assignable.AssignableDigitalResource;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;

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
    "extensions",
    "dateCreated",
    "dateModified",
    "datePublished",
    "version",
    "dateToActivate",
    "dateToShow",
    "dateToStartOn",
    "dateToSubmit",
    "maxAttempts",
    "maxSubmits",
    "maxScore",
    "isTimeDependent" })
public class AssessmentItem extends AssignableDigitalResource {

    @JsonProperty("@type")
    private final EntityType type;

    @JsonProperty("isTimeDependent")
    private final boolean isTimeDependent;

    /**
     * @param builder apply builder object properties to the AssessmentItem object.
     */
    protected AssessmentItem(Builder<?> builder) {
        super(builder);

        EntityValidator.checkTypeUri(builder.type, EntityType.ASSESSMENT_ITEM);

        this.type = builder.type;
        this.isTimeDependent = builder.isTimeDependent;
    }

    /**
     * @return the type
     */
    @Override
    @Nonnull
    public EntityType getType() {
        return type;
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
        private EntityType type;
        private boolean isTimeDependent = false;

        /**
         * Constructor
         */
        public Builder() {
            type(EntityType.ASSESSMENT_ITEM);
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(EntityType type) {
            this.type = type;
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