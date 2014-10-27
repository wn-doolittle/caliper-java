package org.imsglobal.caliper.profiles;

import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.assignable.AssignableDigitalResource;
import org.imsglobal.caliper.entities.outcome.Outcome;

import java.util.List;

public class OutcomeProfile extends Profile {

    private AssignableDigitalResource assignable;
    private List<Outcome> outcomes = Lists.newArrayList();

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected OutcomeProfile(Builder<?> builder) {
        super(builder);
        this.assignable = builder.assignable;
        this.outcomes = builder.outcomes;
    }

    /**
     * @return assignable digital resource.
     */
    public AssignableDigitalResource getAssignable() {
        return assignable;
    }

    /**
     * @return list of outcomes (paired attempt and result)
     */
    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Profile.Builder<T>  {
        private AssignableDigitalResource assignable;
        private List<Outcome> outcomes = Lists.newArrayList();

        /**
         * @param assignable
         * @return builder.
         */
        public T assignable(AssignableDigitalResource assignable) {
            this.assignable = assignable;
            return self();
        }

        /**
         * @param outcomes
         * @return builder
         */
        public T outcomes(List<Outcome> outcomes) {
            this.outcomes = outcomes;
            return self();
        }

        /**
         * @param outcome
         * @return builder
         */
        public T outcome(Outcome outcome) {
            this.outcomes.add(outcome);
            return self();
        }

        /**
         * Client invokes the build method in order to create an immutable profile object.
         * @return a new instance of MediaProfile.
         */
        public OutcomeProfile build() {
            return new OutcomeProfile(this);
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