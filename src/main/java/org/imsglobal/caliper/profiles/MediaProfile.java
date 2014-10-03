package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.schemadotorg.MediaObject;

import java.util.HashMap;
import java.util.Map;

public class MediaProfile extends BaseProfile {

    public enum MediaAction {
        ENABLEDCLOSECAPTIONING("media.accessibility.enabledCloseCaptioning"),
        DISABLEDCLOSEDCAPTIONING("media.accessibility.disabledCloseCaptioning"),

        CHANGEDVOLUME("media.audio.changedVolume"),
        MUTED("media.audio.muted"),
        UNMUTED("media.audio.unmuted"),

        STARTED("media.playback.started"),
        PAUSED("media.playback.paused"),
        ENDED("media.playback.ended"),
        JUMPEDTO("media.playback.jumpedTo"),
        FORWARDEDTO("media.playback.forwardedTo"),
        REWINDED("media.playback.rewindedTo"),
        CHANGEDSPEED("media.playback.changedSpeed"),

        CHANGEDRESOLUTION("media.viewer.changedResolution"),
        CHANGEDVIEWERSIZE("media.viewer.changedViewerSize"),
        ENTEREDFULLSCREEN("media.viewer.enteredFullScreen"),
        EXITEDFULLSCREEN("media.viewer.exitedFullScreen"),
        OPENEDPOPOUT("media.viewer.openedPopout"),
        CLOSEDPOPOUT("media.viewer.closedPopout");

        private final String key;
        private static final Map<String, MediaAction> lookup = new HashMap<String, MediaAction>();

        /**
         * Create reverse lookup hash map
         */
        static {
            for (MediaAction constants : MediaAction.values())
                lookup.put(constants.key(), constants);
        }

        /**
         * Constructor
         * @param key
         */
        private MediaAction(String key) {
            this.key = key;
        }

        /**
         * @return ResourceBundle key for internationalized action strings.
         */
        public String key() {
            return key;
        }

        /**
         * @param key
         * @return true if lookup returns a key match; false otherwise.
         */
        public static boolean hasKey(String key) {
            return lookup.containsKey(key);
        }

        /**
         * @param key
         * @return enum constant by reverse lookup
         */
        public static MediaAction lookupConstant(String key) {
            return lookup.get(key);
        }
    }

    private MediaObject mediaObject;

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected MediaProfile(Builder<?> builder) {
        super(builder);
        this.mediaObject = builder.mediaObject;
    }

    /**
     * @return media object
     */
   public MediaObject getMediaObject() {
       return mediaObject;
   }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private MediaObject mediaObject;

        /**
         * @param mediaObject
         * @return builder.
         */
        public T mediaObject(MediaObject mediaObject) {
            this.mediaObject = mediaObject;
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
}