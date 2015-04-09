package org.imsglobal.caliper.entities.assignable;

import com.fasterxml.jackson.annotation.JsonValue;
import org.imsglobal.caliper.entities.Type;

public enum AssignableDigitalResourceType implements Type {
    ASSESSMENT("http://purl.imsglobal.org/caliper/v1/Assessment"),
    ASSESSMENT_ITEM("http://purl.imsglobal.org/caliper/v1/AssessmentItem");

    private final String value;

    /**
     * Private constructor
     * @param value
     */
    private AssignableDigitalResourceType(final String value) {
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