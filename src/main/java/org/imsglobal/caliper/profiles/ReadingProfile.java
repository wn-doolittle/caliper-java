package org.imsglobal.caliper.profiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadingProfile extends BaseProfile {

    public enum ReadingAction {
        NAVIGATEDTO("reading.navigatedTo"),
        SEARCHED("reading.searched"),
        VIEWED("reading.viewed");

        private final String key;
        private static final Map<String, ReadingAction> lookup = new HashMap<String, ReadingAction>();

        /**
         * Create reverse lookup hash map
         */
        static {
            for (ReadingAction constants : ReadingAction.values())
                lookup.put(constants.key(), constants);
        }

        /**
         * Constructor
         * @param key
         */
        private ReadingAction(String key) {
            this.key = key;
        }

        /**
         * @return ResourceBundle key for internationalized action strings.
         */
        public String key() {
            return key;
        }

        /**
         * @param key
         * @return true if lookup returns a key match; false otherwise.
         */
        public static boolean hasKey(String key) {
            return lookup.containsKey(key);
        }

        /**
         * @param key
         * @return enum constant by reverse lookup
         */
        public static ReadingAction lookupConstant(String key) {
            return lookup.get(key);
        }
    }

    private List<Object> frameList = new ArrayList<Object>();
    private List<Object> navigatedFromList = new ArrayList<Object>();

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected ReadingProfile(Builder<?> builder) {
        super(builder);
        this.frameList = builder.frameList;
        this.navigatedFromList = builder.navigatedFromList;
    }

    /**
     * A frame registers the location or range within a piece of content (a page, a section, or epub cfi).
     * @return List of frames
     */
    public List<Object> getFrameList() {
        return frameList;
    }

    /**
     * @return return a list of starting locations.
     */
    public List<Object> getNavigatedFromList() {
        return navigatedFromList;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {

        private List<Object> frameList = new ArrayList<Object>();
        private List<Object> navigatedFromList = new ArrayList<Object>();

        /**
         * @param frameList
         * @return builder.
         */
        public T frameList(List<Object> frameList) {
            this.frameList = frameList;
            return self();
        }

        /**
         * @param frame
         * @return builder
         */
        public T frame(Object frame) {
            this.frameList.add(frame);
            return self();
        }

        /**
         * @param navigatedFromList
         * @return builder.
         */
        public T navigatedFromList(List<Object> navigatedFromList) {
            this.navigatedFromList = navigatedFromList;
            return self();
        }

        /**
         * @param navigatedFrom
         * @return builder
         */
        public T navigatedFrom(Object navigatedFrom) {
            this.navigatedFromList.add(navigatedFrom);
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