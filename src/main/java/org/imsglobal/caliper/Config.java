package org.imsglobal.caliper;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Config {
    DATA_FORMAT("JSON-LD"),
    DATA_VERSION("http://purl.imsglobal.org/ctx/caliper/v1p1"),
    REMOTE_CALIPER_JSONLD_CONTEXT("http://purl.imsglobal.org/ctx/caliper/v1p1"),
    TEST_FIXTURES_BASE_DIRECTORY("../caliper-common-fixtures/src/test/resources/fixtures/"),
    UUID_VERSION("4");

    private final String value;

    /**
     * Private constructor
     * @param value
     */
    private Config(final String value) {
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