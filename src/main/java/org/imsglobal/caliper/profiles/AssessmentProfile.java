package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.assessment.Assessment;

public class AssessmentProfile extends org.imsglobal.caliper.profiles.Profile {

    private Assessment assessment;

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
    public Assessment getAssessment() {
        return assessment;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Profile.Builder<T>  {
        private Assessment assessment;

        /**
         * @param assessment
         * @return builder.
         */
        public T assessment(Assessment assessment) {
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