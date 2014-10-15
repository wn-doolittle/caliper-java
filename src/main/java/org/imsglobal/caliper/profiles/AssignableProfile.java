package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.assignable.CaliperAssignableDigitalResource;

import java.util.ArrayList;
import java.util.List;

public class AssignableProfile extends BaseProfile {

    private CaliperAssignableDigitalResource assignable;
    private List<Attempt> attempts = new ArrayList<Attempt>();

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected AssignableProfile(Builder<?> builder) {
        super(builder);
        this.assignable = builder.assignable;
        this.attempts = builder.attempts;
    }

    /**
     * @return assignable digital resource.
     */
    public CaliperAssignableDigitalResource getAssignable() {
        return assignable;
    }

    /**
     * @return attempts
     */
    public List<Attempt> getAttempts() {
        return attempts;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private CaliperAssignableDigitalResource assignable;
        private List<Attempt> attempts = new ArrayList<Attempt>();

        /**
         * @param assignable
         * @return builder.
         */
        public T assignable(CaliperAssignableDigitalResource assignable) {
            this.assignable = assignable;
            return self();
        }

        /**
         * @param attempts
         * @return builder
         */
        public T attempts(List<Attempt> attempts) {
            this.attempts = attempts;
            return self();
        }

        /**
         * @param attempt
         * @return builder.
         */
        public T attempt(Attempt attempt) {
            this.attempts.add(attempt);
            return self();
        }

        /**
         * Client invokes the build method in order to create an immutable profile object.
         * @return a new instance of MediaProfile.
         */
        public AssignableProfile build() {
            return new AssignableProfile(this);
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