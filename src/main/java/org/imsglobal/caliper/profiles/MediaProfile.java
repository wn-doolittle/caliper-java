package org.imsglobal.caliper.profiles;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.actions.MediaActions;

import java.util.ResourceBundle;

@JsonPropertyOrder ({
    "mediaFrame",
    "mediaTimestamp",
    "mediaTargetFrame",
    "mediaTargetTimestamp",
    "mediaLength",
    "resolutionX",
    "resolutionY",
    "sizeX",
    "sizeY",
    "isEmbedded",
    "action" })
public class MediaProfile extends BaseProfile {

    @JsonProperty("mediaFrame")
    private int mediaFrame;

    @JsonProperty("mediaTimestamp")
    private long mediaTimestamp;

    @JsonProperty("mediaTargetFrame")
    private int mediaTargetFrame;

    @JsonProperty("mediaTargetTimestamp")
    private long mediaTargetTimestamp;

    @JsonProperty("mediaLength")
    private int mediaLength;

    @JsonProperty("resolutionX")
    private int resolutionX;

    @JsonProperty("resolutionY")
    private int resolutionY;

    @JsonProperty("sizeX")
    private int sizeX;

    @JsonProperty("sizeY")
    private int sizeY;

    @JsonProperty("isEmbedded")
    private boolean isEmbedded;

    @JsonProperty("action")
    private String action;

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected MediaProfile(Builder<?> builder) {
        super(builder);
        this.mediaFrame = builder.mediaFrame;
        this.mediaTimestamp = builder.mediaTimestamp;
        this.mediaTargetFrame = builder.mediaTargetFrame;
        this.mediaTargetTimestamp = builder.mediaTargetTimestamp;
        this.mediaLength = builder.mediaLength;
        this.resolutionX = builder.resolutionX;
        this.resolutionY = builder.resolutionY;
        this.sizeX = builder.sizeX;
        this.sizeY = builder.sizeY;
        this.isEmbedded = builder.isEmbedded;
        this.action = builder.action;
    }

    /**
     * @return media frame.
     */
    public int getMediaFrame() {
        return mediaFrame;
    }

    /**
     * @return media timestamp.
     */
    public long getMediaTimestamp() {
        return mediaTimestamp;
    }

    /**
     * @return media target frame.
     */
    public int getMediaTargetFrame() {
        return mediaTargetFrame;
    }

    /**
     * @return media target timestamp
     */
    public long getMediaTargetTimestamp() {
        return mediaTargetTimestamp;
    }

    /**
     * @return media length.
     */
    public int getMediaLength() {
        return mediaLength;
    }

    /**
     * @return number of horizontal pixels.
     */
    public int getResolutionX() {
        return resolutionX;
    }

    /**
     * @return number of vertical pixels.
     */
    public int getResolutionY() {
        return resolutionY;
    }

    /**
     * @return number of horizontal pixels.
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * @return number of vertical pixels.
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     * @return boolean value indicating whether or not media object is embedded.
     */
    public boolean getIsEmbedded() {
        return isEmbedded;
    }

    /**
     * @return action.
     */
    public String getAction() {
        return action;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private int mediaFrame;
        private long mediaTimestamp;
        private int mediaTargetFrame;
        private long mediaTargetTimestamp;
        private int mediaLength;
        private int resolutionX;
        private int resolutionY;
        private int sizeX;
        private int sizeY;
        private boolean isEmbedded = false;
        private String action;

        /**
         * @param mediaFrame
         * @return builder.
         */
        public T mediaFrame(int mediaFrame) {
            this.mediaFrame = mediaFrame;
            return self();
        }

        /**
         * @param mediaTimestamp
         * @return builder.
         */
        public T mediaTimestamp(long mediaTimestamp) {
            this.mediaTimestamp = mediaTimestamp;
            return self();
        }

        /**
         * @param mediaTargetFrame
         * @return builder.
         */
        public T mediaTargetFrame(int mediaTargetFrame) {
            this.mediaTargetFrame = mediaTargetFrame;
            return self();
        }

        /**
         * @param mediaTargetTimestamp
         * @return builder.
         */
        public T mediaTargetTimestamp(long mediaTargetTimestamp) {
            this.mediaTargetTimestamp = mediaTargetTimestamp;
            return self();
        }

        /**
         * @param mediaLength
         * @return builder.
         */
        public T mediaLength(int mediaLength) {
            this.mediaLength = mediaLength;
            return self();
        }

        /**
         * @param resolutionX
         * @return builder.
         */
        public T resolutionX(int resolutionX) {
            this.resolutionX = resolutionX;
            return self();
        }

        /**
         * @param resolutionY
         * @return builder.
         */
        public T resolutionY(int resolutionY) {
            this.resolutionY = resolutionY;
            return self();
        }

        /**
         * @param sizeX
         * @return builder.
         */
        public T sizeX(int sizeX) {
            this.sizeX = sizeX;
            return self();
        }

        /**
         * @param sizeY
         * @return builder.
         */
        public T sizeY(int sizeY) {
            this.sizeY = sizeY;
            return self();
        }

        /**
         * @param isEmbedded
         * @return builder.
         */
        public T isEmbedded(boolean isEmbedded) {
            this.isEmbedded = isEmbedded;
            return self();
        }

        /**
         * @param key
         * @return builder after validating action key.
         */
        public T action(String key) {
            this.action = validateAction(key);
            return self();
        }

        /**
         * Client invokes the build method in order to create an immutable profile object.
         * @return a new instance of MediaProfile.
         */
        public MediaProfile build() {
            return new MediaProfile(this);
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

    /**
     * @param key resource bundle key attribute of target constant
     * @return resource bundle key
     */
    private static String validateAction(String key) {
        if (MediaActions.hasKey(key)) {
            return ResourceBundle.getBundle("resources.actions").getString(key);
        } else {
            throw new IllegalArgumentException("Unrecognized constant");
        }
    }
}