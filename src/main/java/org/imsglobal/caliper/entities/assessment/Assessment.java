package org.imsglobal.caliper.entities.assessment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.assignable.AssignableDigitalResource;
import org.imsglobal.caliper.entities.qti.QtiAssessment;

import java.util.List;

/**
 * Caliper representation of an QTI Assessment.  Part of the Assessment Metric Profile
 */
public class Assessment extends AssignableDigitalResource implements QtiAssessment {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("assessmentItems")
    private List<AssessmentItem> assessmentItems = Lists.newArrayList();

    /**
     * @param builder apply builder object properties to the CaliperAssessment object.
     */
    protected Assessment(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.assessmentItems = builder.assessmentItems;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return assessment items
     */
    public List<AssessmentItem> getAssessmentItems() {
        return assessmentItems;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends AssignableDigitalResource.Builder<T>  {
        private String type;
        private List<AssessmentItem> assessmentItems = Lists.newArrayList();

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(AssignableDigitalResource.Type.ASSESSMENT.uri());
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