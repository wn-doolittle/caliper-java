package org.imsglobal.caliper.entities.media;

import com.fasterxml.jackson.annotation.JsonValue;
import org.imsglobal.caliper.entities.Type;

public enum MediaObjectType implements Type {
    AUDIO_OBJECT("http://purl.imsglobal.org/caliper/v1/AudioObject"),
    IMAGE_OBJECT("http://purl.imsglobal.org/caliper/v1/ImageObject"),
    VIDEO_OBJECT("http://purl.imsglobal.org/caliper/v1/VideoObject"),
    MEDIA_LOCATION("http://purl.imsglobal.org/caliper/v1/MediaLocation");

    private final String value;

    /**
     * Private constructor
     *
     * @param value
     */
    private MediaObjectType(final String value) {
        this.value = value;
    }

    /**
     * @return URI string
     */
    @JsonValue
    public String getValue() {
        return value;
    }
}