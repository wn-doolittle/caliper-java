package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EventContext {
    ANNOTATION("http://purl.imsglobal.org/ctx/caliper/v1/AnnotationEvent"),
    ASSESSMENT("http://purl.imsglobal.org/ctx/caliper/v1/AssessmentEvent"),
    ASSESSMENT_ITEM("http://purl.imsglobal.org/ctx/caliper/v1/AssessmentItemEvent"),
    ASSIGNABLE("http://purl.imsglobal.org/ctx/caliper/v1/AssignableEvent"),
    EVENT("http://purl.imsglobal.org/ctx/caliper/v1/Event"),
    MEDIA("http://purl.imsglobal.org/ctx/caliper/v1/MediaEvent"),
    NAVIGATION("http://purl.imsglobal.org/ctx/caliper/v1/NavigationEvent"),
    OUTCOME("http://purl.imsglobal.org/ctx/caliper/v1/OutcomeEvent"),
    READING("http://purl.imsglobal.org/ctx/caliper/v1/ReadingEvent"),
    SESSION("http://purl.imsglobal.org/ctx/caliper/v1/SessionEvent"),
    VIEW("http://purl.imsglobal.org/ctx/caliper/v1/ViewEvent");

    private final String value;

    /**
     * Private constructor
     * @param value
     */
    private EventContext(final String value) {
        this.value = value;
    }

    /**
     * @return URI value string
     */
    @JsonValue
    public String getValue() {
        return value;
    }
}