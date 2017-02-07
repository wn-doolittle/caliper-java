package org.imsglobal.caliper.entities.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.EntityType;

import javax.annotation.Nullable;

/**
 * Concrete implementation of a generic MediaObject.
 */
public class MediaObject extends AbstractDigitalResource implements Media {

    @JsonProperty("duration")
    private String duration;

    /**
     * @param builder apply builder object properties to the MediaObject object.
     */
    protected MediaObject(Builder<?> builder) {
        super(builder);

        this.duration = builder.duration;
    }

    /**
     * @return duration
     */
    @Nullable
    public String getDuration() {
        return duration;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends AbstractDigitalResource.Builder<T>  {
        private String duration;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            super.type(EntityType.MEDIA_OBJECT);
        }

        /**
         * @param duration
         * @return duration
         */
        public T duration(String duration) {
            this.duration = duration;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of MediaObject.
         */
        public MediaObject build() {
            return new MediaObject(this);
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