package org.imsglobal.caliper.entities.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * An audio object embedded in a web page.
 */
@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "objectType",
    "alignedLearningObjective",
    "keyword",
    "partOf",
    "lastModifiedTime",
    "duration",
    "volumeMin",
    "volumeMax",
    "volumeLevel",
    "muted" })
public class AudioObject extends MediaObject implements org.imsglobal.caliper.entities.schemadotorg.AudioObject {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("volumeMin")
    private String volumeMin;

    @JsonProperty("volumeMax")
    private String volumeMax;

    @JsonProperty("volumeLevel")
    private String volumeLevel;

    @JsonProperty("muted")
    private boolean muted;

    /**
     * @param builder apply builder object properties to the AudioObject object.
     */
    protected AudioObject(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.volumeMin = builder.volumeMin;
        this.volumeMax = builder.volumeMax;
        this.volumeLevel = builder.volumeLevel;
        this.muted = builder.muted;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return minimum volume
     */
    public String getVolumeMin() {
        return volumeMin;
    }

    /**
     * @return maximum volume
     */
    public String getVolumeMax() {
        return volumeMax;
    }

    /**
     * @return volume level
     */
    public String getVolumeLevel() {
        return volumeLevel;
    }

    /**
     * @return muted status (true/false)
     */
    public boolean getMuted() {
        return muted;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends MediaObject.Builder<T>  {
        private String type;
        private String volumeMin;
        private String volumeMax;
        private String volumeLevel;
        private boolean muted;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(MediaObject.Type.AUDIO_OBJECT.uri());
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
         * @param volumeMin
         * @return builder
         */
        public T volumeMin(String volumeMin) {
            this.volumeMin = volumeMin;
            return self();
        }

        /**
         * @param volumeMax
         * @return builder
         */
        public T volumeMax(String volumeMax) {
            this.volumeMax = volumeMax;
            return self();
        }

        /**
         * @param volumeLevel
         * @return builder
         */
        public T volumeLevel(String volumeLevel) {
            this.volumeLevel = volumeLevel;
            return self();
        }

        /**
         * @param muted
         * @return builder
         */
        public T muted (boolean muted) {
            this.muted = muted;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of AudioObject.
         */
        public AudioObject build() {
            return new AudioObject(this);
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