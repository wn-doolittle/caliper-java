package org.imsglobal.caliper.profiles;

import java.util.ResourceBundle;

import org.imsglobal.caliper.actions.ReadingActions;
import org.imsglobal.caliper.events.CaliperEvent;

public class ReadingProfile extends BaseProfile {

    private ReadingActions actions;
    private Object frame;
    private Object navigatedFrom;

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected ReadingProfile(Builder<?> profileContext) {
        super(profileContext);
    }
    
    public CaliperEvent get(Class eventType) {
    	
    	return null;
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
    public ReadingActions getActions() {
        return actions;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private Object frame;
        private Object navigatedFrom;
        private String action;

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
         * @param key
         * @return builder after validating action key.
         */
        public T action(String key) {
            this.action = validateAction(key);
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

    /**
     * @param key resource bundle key attribute of target constant
     * @return resource bundle key
     */
    private static String validateAction(String key) {
        if (ReadingActions.hasKey(key)) {
            return ResourceBundle.getBundle("resources.actions").getString(key);
        } else {
            throw new IllegalArgumentException("Unrecognized constant");
        }
    }
}