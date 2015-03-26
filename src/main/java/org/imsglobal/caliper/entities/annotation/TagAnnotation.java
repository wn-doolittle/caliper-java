package org.imsglobal.caliper.entities.annotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "description",
    "extensions",
    "dateCreated",
    "dateModified",
    "annotatedId",
    "tags" })
public class TagAnnotation extends org.imsglobal.caliper.entities.annotation.Annotation {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("tags")
    private ImmutableList<String> tags;

    /**
     * @param builder apply builder object properties to the TagAnnotation object.
     */
    protected TagAnnotation(Builder<?> builder) {
        super(builder);

        EntityValidator.checkTypeUri(builder.type, Type.TAG_ANNOTATION);

        this.type = builder.type;
        this.tags = ImmutableList.copyOf(builder.tags);
    }

    /**
     * @return the type
     */
    @Override
    @Nonnull
    public String getType() {
        return type;
    }

    /**
     * Return an immutable view of the tags list.
     * @return the tags
     */
    @Nullable
    public ImmutableList<String> getTags() {
        return tags;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Annotation.Builder<T>  {
        private String type;
        private List<String> tags = Lists.newArrayList();

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(Annotation.Type.TAG_ANNOTATION.uri());
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(String type) {
            this.type = type;
            return self();
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