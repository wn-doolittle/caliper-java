package org.imsglobal.caliper.entities.assessment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.EntityType;
import org.imsglobal.caliper.entities.assignable.AssignableDigitalResource;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Caliper representation of an Assessment.  Part of the Assessment Metric Profile
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
    "assessmentItems" })
public class Assessment extends AssignableDigitalResource {

    @JsonProperty("@type")
    private final EntityType type;

    @JsonProperty("assessmentItems")
    private final ImmutableList<AssessmentItem> assessmentItems;

    /**
     * @param builder apply builder object properties to the CaliperAssessment object.
     */
    protected Assessment(Builder<?> builder) {
        super(builder);

        EntityValidator.checkTypeUri(builder.type, EntityType.ASSESSMENT);

        this.type = builder.type;
        this.assessmentItems = ImmutableList.copyOf(builder.assessmentItems);
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
     * Return an immutable view of the assessmentItems list.
     * @return assessment items
     */
    @Nullable
    public ImmutableList<AssessmentItem> getAssessmentItems() {
        return assessmentItems;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends AssignableDigitalResource.Builder<T>  {
        private EntityType type;
        private List<AssessmentItem> assessmentItems = Lists.newArrayList();

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(EntityType.ASSESSMENT);
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
         * @param assessmentItems
         * @return builder
         */
        public T assessmentItems(List<AssessmentItem> assessmentItems) {
            this.assessmentItems = assessmentItems;
            return self();
        }

        /**
         * @param assessmentItem
         * @return builder
         */
        public T assessmentItem(AssessmentItem assessmentItem) {
            this.assessmentItems.add(assessmentItem);
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of CaliperAssessment.
         */
        public Assessment build() {
            return new Assessment(this);
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