package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.annotation.Annotation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnotationProfile extends BaseProfile {

    public enum AnnotationAction {
        ATTACHED("annotation.attached"),
        BOOKMARKED("annotation.bookmarked"),
        CLASSIFIED("annotation.classified"),
        COMMENTED("annotation.commented"),
        DESCRIBED("annotation.described"),
        HIGHLIGHTED("annotation.highlighted"),
        IDENTIFIED("annotation.identified"),
        LIKED("annotation.liked"),
        LINKED("annotation.linked"),
        RANKED("annotation.ranked"),
        QUESTIONED("annotation.questioned"),
        RECOMMENDED("annotation.recommended"),
        REPLIED("annotation.replied"),
        SHARED("annotation.shared"),
        SUBSCRIBED("annotation.subscribed"),
        TAGGED("annotation.tagged");

        private final String key;
        private static final Map<String, AnnotationAction> lookup = new HashMap<String, AnnotationAction>();

        /**
         * Create reverse lookup hash map
         */
        static {
            for (AnnotationAction constants : AnnotationAction.values())
                lookup.put(constants.key(), constants);
        }

        /**
         * Constructor
         * @param key
         */
        private AnnotationAction(String key) {
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
        public static AnnotationAction lookupConstant(String key) {
            return lookup.get(key);
        }
    }

    /**
     * List of annotations
     */
    private List<Annotation> annotations = new ArrayList<Annotation>();

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected AnnotationProfile(Builder<?> builder) {
        super(builder);
        this.annotations = builder.annotations;
    }

    /**
     * @return annotation
     */
    public List<Annotation> getAnnotations() {
        return annotations;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private List<Annotation> annotations = new ArrayList<Annotation>();

        /**
         * @param annotations
         * @return builder.
         */
        public T annotations(List<Annotation> annotations) {
            this.annotations = annotations;
            return self();
        }

        /**
         * @param annotation
         * @return builder.
         */
        public T annotation(Annotation annotation) {
            this.annotations.add(annotation);
            return self();
        }

        /**
         * Client invokes the build method in order to create an immutable profile object.
         * @return a new instance of AnnotationProfile.
         */
        public AnnotationProfile build() {
            return new AnnotationProfile(this);
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