package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DigitalResourceType implements Type {
    ASSIGNABLE_DIGITAL_RESOURCE("http://purl.imsglobal.org/caliper/v1/AssignableDigitalResource"),
    EPUB_CHAPTER("http://www.idpf.org/epub/vocab/structure/#chapter"),
    EPUB_PART("http://www.idpf.org/epub/vocab/structure/#part"),
    EPUB_SUB_CHAPTER("http://www.idpf.org/epub/vocab/structure/#subchapter"),
    EPUB_VOLUME("http://www.idpf.org/epub/vocab/structure/#volume"),
    FRAME("http://purl.imsglobal.org/caliper/v1/Frame"),
    READING("http://www.idpf.org/epub/vocab/structure"),
    WEB_PAGE("http://purl.imsglobal.org/caliper/v1/WebPage");

    private final String value;

    /**
     * Private constructor
     * @param value
     */
    private DigitalResourceType(final String value) {
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