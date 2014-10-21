package org.imsglobal.caliper.profiles;

import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.annotation.Annotation;
import java.util.List;

public class AnnotationProfile extends BaseProfile {

    private List<Annotation> annotations = Lists.newArrayList();

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
        private List<Annotation> annotations = Lists.newArrayList();

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