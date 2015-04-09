package org.imsglobal.caliper.entities.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.EntityType;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;

/**
 * An image object embedded in a web page.
 */
public class ImageObject extends MediaObject implements org.imsglobal.caliper.entities.schemadotorg.ImageObject {

    @JsonProperty("@type")
    private final EntityType type;

    /**
     * @param builder apply builder object properties to the ImageObject object.
     */
    protected ImageObject(Builder<?> builder) {
        super(builder);

        EntityValidator.checkTypeUri(builder.type, EntityType.IMAGE_OBJECT);

        this.type = builder.type;
    }

    /**
     * @return the type
     */
    @Override
    @Nonnull
    public EntityType getType() {
        return type;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends MediaObject.Builder<T>  {
        private EntityType type;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(EntityType.IMAGE_OBJECT);
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(EntityType type) {
            this.type = type;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of ImageObject.
         */
        public ImageObject build() {
            return new ImageObject(this);
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