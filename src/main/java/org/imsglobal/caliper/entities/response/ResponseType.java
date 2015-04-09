package org.imsglobal.caliper.entities.response;

import com.fasterxml.jackson.annotation.JsonValue;
import org.imsglobal.caliper.entities.Type;

public enum ResponseType implements Type {
    FILLINBLANK("http://purl.imsglobal.org/caliper/v1/Response/FillinBlank"),
    MULTIPLECHOICE("http://purl.imsglobal.org/caliper/v1/Response/MultipleChoice"),
    MULTIPLERESPONSE("http://purl.imsglobal.org/caliper/v1/Response/MultipleResponse"),
    SELECTTEXT("http://purl.imsglobal.org/caliper/v1/Response/SelectText"),
    TRUEFALSE("http://purl.imsglobal.org/caliper/v1/Response/TrueFalse");

    private final String value;

    /**
     * Private constructor
     * @param value
     */
    private ResponseType(final String value) {
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