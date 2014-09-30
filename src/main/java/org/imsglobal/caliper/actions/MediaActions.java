package org.imsglobal.caliper.actions;

import java.util.HashMap;
import java.util.Map;

public enum MediaActions {
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