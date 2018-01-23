package org.imsglobal.caliper.config;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Timeout {
    CONNECTION_REQUESTOR_TIMEOUT(10000),
    CONNECTION_TIMEOUT(10000),
    SOCKET_TIMEOUT(10000);

    private final int value;

    /**
     * Private constructor
     * @param value
     */
    private Timeout(final int value) {
        this.value = value;
    }

    /**
     * @return default string
     */
    @JsonValue
    public int value() {
        return value;
    }
}