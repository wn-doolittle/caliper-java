package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.assessment.CaliperAssessment;

public class AssessmentProfile extends BaseProfile {

    private CaliperAssessment assessment;

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected AssessmentProfile(Builder<?> builder) {
        super(builder);
        this.assessment = builder.assessment;
    }

    /**
     * @return Assessment
     */
    public CaliperAssessment getAssessment() {
        return assessment;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private CaliperAssessment assessment;

        /**
         * @param assessment
         * @return builder.
         */
        public T assessment(CaliperAssessment assessment) {
            this.assessment = assessment;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable profile object.
         * @return a new instance of the AssessmentProfile.
         */
        public AssessmentProfile build() {
            return new AssessmentProfile(this);
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