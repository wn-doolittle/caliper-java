package org.imsglobal.caliper.entities.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Strings;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.Targetable;

import java.util.UUID;

/**
 * Media Location
 */
@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "objectType",
    "alignedLearningObjective",
    "keywords",
    "isPartOf",
    "dateModified",
    "datePublished",
    "currentTime" })
public class MediaLocation extends DigitalResource implements Targetable {

    @JsonProperty("@id")
    private final String id;

    @JsonProperty("@type")
    private final String type;

    /**
     * The time value (from beginning of media) that indicates the current
     * location
     */
    @JsonProperty("currentTime")
    private long currentTime;

    /**
     * @param builder apply builder object properties to the MediaLocation object.
     */
    protected MediaLocation(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.id = builder.id;
        this.currentTime = builder.currentTime;
    }

    /**
     * @return the id
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return the currentTime
     */
    public long getCurrentTime() {
        return currentTime;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends DigitalResource.Builder<T>  {
        private String id;
        private String type;
        private long currentTime;
        private UUID uuid = new UUID(1000l, 500l);

        protected abstract T self();

        /**
         * Initialize type with default values.
         */
        public Builder() {
            id(DigitalResource.Type.MEDIA_LOCATION.uri() + "/" + uuid);
            type(DigitalResource.Type.MEDIA_LOCATION.uri());
        }

        /**
         * @param id
         * @return builder
         */
        @Override
        public T id(String id) {
            if (Strings.isNullOrEmpty(id)) {
                this.id = DigitalResource.Type.MEDIA_LOCATION.uri() + "/" + uuid;
            } else {
                this.id = id;
            }
            return self();
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
         * @param currentTime
         * @return builder
         */
        public T currentTime(long currentTime) {
            this.currentTime = currentTime;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of MediaLocation.
         */
        public MediaLocation build() {
            return new MediaLocation(this);
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