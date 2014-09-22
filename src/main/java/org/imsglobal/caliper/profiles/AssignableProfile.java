package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.actions.AssignableActions;
import org.imsglobal.caliper.entities.assignable.CaliperAssignableDigitalResource;

public class AssignableProfile extends BaseProfile {

    private AssignableActions actions;
    private CaliperAssignableDigitalResource assignable;

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected AssignableProfile(Builder<?> builder) {
        super(builder);
        this.assignable = builder.assignable;
    }

    /**
     * @return Assignable action enums
     */
    public AssignableActions getActions() {
        return actions;
    }

    /**
     * @return assignable digital resource.
     */
    public CaliperAssignableDigitalResource getAssignable() {
        return assignable;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private CaliperAssignableDigitalResource assignable;

        /**
         * @param assignable
         * @return builder.
         */
        private T assignable(CaliperAssignableDigitalResource assignable) {
            this.assignable = assignable;
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