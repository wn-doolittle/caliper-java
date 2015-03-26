package org.imsglobal.caliper.entities.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.validators.EntityValidator;

/**
 * Media Location
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
    "currentTime" })
public class MediaLocation extends DigitalResource implements Targetable {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("currentTime")
    private long currentTime;

    /**
     * @param builder apply builder object properties to the MediaLocation object.
     */
    protected MediaLocation(Builder<?> builder) {
        super(builder);

        EntityValidator.checkTypeUri(builder.type, Type.MEDIA_LOCATION);

        this.type = builder.type;
        this.currentTime = builder.currentTime;
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
        private String type;
        private long currentTime;

        /**
         * Initialize type with default values.
         */
        public Builder() {
            type(DigitalResource.Type.MEDIA_LOCATION.uri());
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