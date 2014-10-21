package org.imsglobal.caliper.entities.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.CaliperDigitalResource;

/**
 * An image, video, or audio object embedded in a web page.
 */
public abstract class CaliperMediaObject extends CaliperDigitalResource {

    public enum Type {
        CALIPER_AUDIO_OBJECT("http://purl.imsglobal.org/caliper/v1/CaliperAudioObject"),
        CALIPER_IMAGE_OBJECT("http://purl.imsglobal.org/caliper/v1/CaliperImageObject"),
        CALIPER_VIDEO_OBJECT("http://purl.imsglobal.org/caliper/v1/CaliperVideoObject");

        private final String uri;

        /**
         * Private constructor
         * @param uri
         */
        private Type(final String uri) {
            this.uri = uri;
        }

        /**
         * @return URI string
         */
        public String uri() {
            return uri;
        }
    }

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("duration")
    private long duration;

    /**
     * @param builder apply builder object properties to the CaliperMediaObject object.
     */
    protected CaliperMediaObject(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.duration = builder.duration;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return duration
     */
    public long getDuration() {
        return duration;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).  Given the abstract nature
     * of BaseProfile, the builder's .build() method is omitted.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperDigitalResource.Builder<T> {
        private String type;
        private long duration;

        protected abstract T self();

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(CaliperDigitalResource.Type.CALIPER_MEDIA_OBJECT.uri());
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
