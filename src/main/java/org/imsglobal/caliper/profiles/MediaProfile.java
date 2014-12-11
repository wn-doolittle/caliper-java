package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.media.*;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MediaProfile extends org.imsglobal.caliper.profiles.Profile {

    public enum MediaActions {
        ENABLEDCLOSEDCAPTIONING("media.accessibility.enabledClosedCaptioning"),
        DISABLEDCLOSEDCAPTIONING("media.accessibility.disabledClosedCaptioning"),

        CHANGEDVOLUME("media.audio.changedVolume"),
        MUTED("media.audio.muted"),
        UNMUTED("media.audio.unmuted"),

        CHANGEDSPEED("media.playback.changedSpeed"),
        ENDED("media.playback.ended"),
        JUMPEDTO("media.playback.jumpedTo"),
        FORWARDEDTO("media.playback.forwardedTo"),
        PAUSED("media.playback.paused"),
        RESUMED("media.playback.resumed"),
        REWINDED("media.playback.rewindedTo"),
        STARTED("media.playback.started"),

        CHANGEDRESOLUTION("media.viewer.changedResolution"),
        CHANGEDVIEWERSIZE("media.viewer.changedViewerSize"),
        CLOSEDPOPOUT("media.viewer.closedPopout"),
        ENTEREDFULLSCREEN("media.viewer.enteredFullScreen"),
        EXITEDFULLSCREEN("media.viewer.exitedFullScreen"),
        OPENEDPOPOUT("media.viewer.openedPopout"),

        NAVIGATED_TO("navigation.navigatedTo");

        private final String key;
        private static final Map<String, MediaActions> lookup = new HashMap<String, MediaActions>();

        /**
         * Create reverse lookup hash map
         */
        static {
            for (MediaActions constants : MediaActions.values())
                lookup.put(constants.key(), constants);
        }

        /**
         * Constructor
         *
         * @param key
         */
        private MediaActions(String key) {
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
        public static MediaActions lookupConstant(String key) {
            return lookup.get(key);
        }
    }

    /**
     * Constructor
     */
    public MediaProfile() {

    }

    /**
     * @param key
     * @return localized action string.
     */
    public static String getActionFromBundle(String key) {
        if (MediaActions.hasKey(key) || Actions.hasKey(key)) {
            return ResourceBundle.getBundle("actions").getString(key);
        } else {
            throw new IllegalArgumentException("Unrecognized key: " + key);
        }
    }

    /**
     * @param object
     * @return activityContext object.
     */
    public static MediaObject validateObject(Object object) {
        if (object instanceof AudioObject) {
            // TODO CHECK REQUIRED PROPS
            return (AudioObject) object;
        } else if (object instanceof ImageObject) {
            // TODO CHECK REQUIRED PROPS
            return (ImageObject) object;
        } else if (object instanceof VideoObject) {
            // TODO CHECK REQUIRED PROPS
            return (VideoObject) object;
        } else {
            throw new ClassCastException("Object must be of type MediaObject.");
        }
    }

    /**
     * @param target
     * @return target MediaLocation.
     */
    public static MediaLocation validateTarget(Targetable target) {
        if (target instanceof MediaLocation) {
            // TODO add additional checks
            return (MediaLocation) target;
        } else {
            throw new ClassCastException("Target must be of type MediaLocation.");
        }
    }
}