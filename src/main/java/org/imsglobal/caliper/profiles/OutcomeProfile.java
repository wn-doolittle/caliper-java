package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.actions.OutcomeActions;
import org.imsglobal.caliper.entities.outcome.Result;

public class OutcomeProfile extends BaseProfile {

    private OutcomeActions actions;
    private Result result;

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected OutcomeProfile(Builder<?> builder) {
        super(builder);
        this.result = builder.result;
    }

    /**
     * @return Outcome actions
     */
    public OutcomeActions getActions() {
        return actions;
    }

    /**
     * @return result.
     */
    public Result getResult() {
        return result;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private Result result;

        /**
         * @param result
         * @return builder.
         */
        public T result(Result result) {
            this.result = result;
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