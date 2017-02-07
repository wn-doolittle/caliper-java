package org.imsglobal.caliper;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Context {
    REMOTE_CALIPER_JSONLD_CONTEXT("http://purl.imsglobal.org/ctx/caliper/v1p1");

    private final String value;

    /**
     * Private constructor
     * @param value
     */
    private Context(final String value) {
        this.value = value;
    }

    /**
     * @return default string
     */
    @JsonValue
    public String value() {
        return value;
    }
}