package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.actions.AnnotationActions;
import org.imsglobal.caliper.entities.annotation.Annotation;

public class AnnotationProfile extends BaseProfile {

    private static AnnotationActions action;
    private Annotation annotation;

    /**
     * @param builder apply builder object properties to the profile object.
     * @param action
     */
    protected AnnotationProfile(Builder<?> builder, AnnotationActions action) {
        super(builder);
        this.annotation = builder.annotation;
        this.action = action;
    }

    /**
     * @return annotation
     */
    public Annotation getAnnotation() {
        return annotation;
    }

    /**
     * @return action.
     */
    public AnnotationActions getAction() {
        return action;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private Annotation annotation;

        /**
         * @param annotation
         * @return builder.
         */
        public T annotation(Annotation annotation) {
            this.annotation = annotation;
            return self();
        }

        /**
         * Client invokes the build method in order to create an immutable profile object.
         * @return a new instance of AnnotationProfile.
         */
        public AnnotationProfile build() {
            return new AnnotationProfile(this, action);
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