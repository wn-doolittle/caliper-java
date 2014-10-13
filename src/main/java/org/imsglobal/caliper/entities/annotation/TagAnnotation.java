package org.imsglobal.caliper.entities.annotation;

import com.google.common.collect.Lists;

import java.util.List;

public class TagAnnotation extends Annotation {

    private List<String> tags = Lists.newArrayList();

    /**
     * @param builder apply builder object properties to the TagAnnotation object.
     */
    protected TagAnnotation(Builder<?> builder) {
        super(builder);
        this.tags = builder.tags;
    }

    /**
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Annotation.Builder<T>  {
        private List<String> tags = Lists.newArrayList();

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(Annotation.Identifier.TAG_ANNOTATON_TYPE.uri());
        }

        /**
         * @param tags
         * @return annotation tags.
         */
        public T tags(List<String> tags) {
            this.tags = tags;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of TagAnnotation.
         */
        public TagAnnotation build() {
            return new TagAnnotation(this);
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