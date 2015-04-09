package org.imsglobal.caliper.entities.annotation;

import com.fasterxml.jackson.annotation.JsonValue;
import org.imsglobal.caliper.entities.Type;

public enum AnnotationType implements Type {
    BOOKMARK_ANNOTATION("http://purl.imsglobal.org/caliper/v1/BookmarkAnnotation"),
    HIGHLIGHT_ANNOTATION("http://purl.imsglobal.org/caliper/v1/HighlightAnnotation"),
    SHARED_ANNOTATION("http://purl.imsglobal.org/caliper/v1/SharedAnnotation"),
    TAG_ANNOTATION("http://purl.imsglobal.org/caliper/v1/TagAnnotation");

    private final String value;

    /**
     * Private constructor
     *
     * @param value
     */
    private AnnotationType(final String value) {
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