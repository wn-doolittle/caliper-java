package org.imsglobal.caliper.entities.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.EntityType;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * An image, video, or audio object embedded in a web page.
 */
@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "description",
    "objectType",
    "alignedLearningObjective",
    "keywords",
    "isPartOf",
    "extensions",
    "dateCreated",
    "dateModified",
    "datePublished",
    "version",
    "duration" })
public abstract class MediaObject extends DigitalResource implements org.imsglobal.caliper.entities.schemadotorg.MediaObject {

    @JsonProperty("@type")
    private final EntityType type;

    @JsonProperty("duration")
    private long duration;

    /**
     * @param builder apply builder object properties to the MediaObject object.
     */
    protected MediaObject(Builder<?> builder) {
        super(builder);

        EntityValidator.checkTypeUri(builder.type, EntityType.MEDIA_OBJECT);

        this.type = builder.type;
        this.duration = builder.duration;
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
     * @return duration
     */
    @Nullable
    public long getDuration() {
        return duration;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).  Given the abstract nature
     * of Profile, the builder's .build() method is omitted.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends DigitalResource.Builder<T> {
        private EntityType type;
        private long duration;

        protected abstract T self();

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(EntityType.MEDIA_OBJECT);
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
         * @param duration
         * @return duration
         */
        public T duration(long duration) {
            this.duration = duration;
            return self();
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
}