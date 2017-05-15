package org.imsglobal.caliper.entities.annotation;

import org.imsglobal.caliper.entities.EntityType;

/**
 * A Generic Annotation.
 */
public class Annotation extends AbstractAnnotation {

    /**
     * @param builder apply builder object properties to the object.
     */
    protected Annotation(Builder<?> builder) {
        super(builder);
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> extends AbstractAnnotation.Builder<T> {

        /**
         * Constructor
         */
        public Builder() {
            super.type(EntityType.ANNOTATION);
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the BookmarkAnnotation.
         */
        public Annotation build() {
            return new Annotation(this);
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