package org.imsglobal.caliper.entities.assessment;

import org.imsglobal.caliper.entities.assignable.CaliperAssignableDigitalResource;
import org.imsglobal.caliper.entities.qti.Assessment;

import java.util.ArrayList;
import java.util.List;

/**
 * Caliper representation of an Assessment.
 * 
 * Part of the Assessment Metric Profile
 */
public class CaliperAssessment extends CaliperAssignableDigitalResource implements Assessment {

    private final String type;
    private List<CaliperAssessmentItem> assessmentItems = new ArrayList<CaliperAssessmentItem>();

    /**
     * @param builder apply builder object properties to the CaliperAssessment object.
     */
    protected CaliperAssessment(Builder<?> builder) {
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
    public List<CaliperAssessmentItem> getAssessmentItems() {
        return assessmentItems;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperAssignableDigitalResource.Builder<T>  {
        private String type;
        private List<CaliperAssessmentItem> assessmentItems = new ArrayList<CaliperAssessmentItem>();

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(CaliperAssignableDigitalResource.Type.ASSESSMENT.uri());
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
        public T assessmentItems(List<CaliperAssessmentItem> assessmentItems) {
            this.assessmentItems = assessmentItems;
            return self();
        }

        /**
         * @param assessmentItem
         * @return builder
         */
        public T assessmentItem(CaliperAssessmentItem assessmentItem) {
            this.assessmentItems.add(assessmentItem);
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of CaliperAssessment.
         */
        public CaliperAssessment build() {
            return new CaliperAssessment(this);
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