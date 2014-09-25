package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.actions.AssessmentActions;
import org.imsglobal.caliper.entities.assessment.CaliperAssessment;
import org.imsglobal.caliper.entities.assessment.CaliperAssessmentItem;

import java.util.List;
import java.util.ArrayList;

public class AssessmentProfile extends BaseProfile {

    private static AssessmentActions action;
    private CaliperAssessment assessment;
    private List<CaliperAssessmentItem> assessmentItem = new ArrayList<CaliperAssessmentItem>();

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected AssessmentProfile(Builder<?> builder, AssessmentActions action) {
        super(builder);
        this.assessment = builder.assessment;
        this.assessmentItem = builder.assessmentItem;
        this.action = action;
    }

    /**
     * @return Assessment action enums
     */
    public AssessmentActions getAction() {
        return action;
    }

    /**
     * @return Assessment
     */
    public CaliperAssessment getAssessment() {
        return assessment;
    }

    /**
     * @return List of assessment items
     */
    public List<CaliperAssessmentItem>  getAssessmentItem() {
        return assessmentItem;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private CaliperAssessment assessment;
        private List<CaliperAssessmentItem> assessmentItem = new ArrayList<CaliperAssessmentItem>();

        /**
         * @param assessment
         * @return builder.
         */
        public T assessment(CaliperAssessment assessment) {
            this.assessment = assessment;
            return self();
        }

        /**
         * @param assessmentItem
         * @return builder.
         */
        public T assessmentItem(List<CaliperAssessmentItem> assessmentItem) {
            this.assessmentItem = assessmentItem;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable profile object.
         * @return a new instance of the AssessmentProfile.
         */
        public AssessmentProfile build() {
            return new AssessmentProfile(this, action);
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