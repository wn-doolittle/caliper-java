package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.actions.ReadingActions;

public class ReadingProfile extends BaseProfile {

    private ReadingActions action;
    private Object frame;
    private Object navigatedFrom;

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected ReadingProfile(Builder<?> builder) {
        super(builder);
        this.frame = builder.frame;
        this.navigatedFrom = builder.navigatedFrom;
    }

    /**
     * A frame registers the location or range within a piece of content (a page, a section, or epub cfi).
     * @return frame
     */
    public Object getFrame() {
        return frame;
    }

    /**
     * @return return the starting location.
     */
    public Object navigatedFrom() {
        return navigatedFrom;
    }

    /**
     * @return action.
     */
    public ReadingActions getAction() {
        return action;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private Object frame;
        private Object navigatedFrom;

        /**
         * @param frame
         * @return builder.
         */
        public T frame(Object frame) {
            this.frame = frame;
            return self();
        }

        /**
         * @param navigatedFrom
         * @return builder.
         */
        public T navigatedFrom(Object navigatedFrom) {
            this.navigatedFrom = navigatedFrom;
            return self();
        }

        /**
         * Client invokes the build method in order to create an immutable profile object.
         * @return a new instance of MediaProfile.
         */
        public ReadingProfile build() {
            return new ReadingProfile(this);
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