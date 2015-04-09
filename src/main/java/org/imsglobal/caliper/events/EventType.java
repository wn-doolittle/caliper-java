package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public enum EventType {
    ANNOTATION("http://purl.imsglobal.org/caliper/v1/AnnotationEvent"),
    ASSESSMENT("http://purl.imsglobal.org/caliper/v1/AssessmentEvent"),
    ASSESSMENT_ITEM("http://purl.imsglobal.org/caliper/v1/AssessmentItemEvent"),
    ASSIGNABLE("http://purl.imsglobal.org/caliper/v1/AssignableEvent"),
    EVENT("http://purl.imsglobal.org/caliper/v1/Event"),
    MEDIA("http://purl.imsglobal.org/caliper/v1/MediaEvent"),
    NAVIGATION("http://purl.imsglobal.org/caliper/v1/NavigationEvent"),
    OUTCOME("http://purl.imsglobal.org/caliper/v1/OutcomeEvent"),
    READING("http://purl.imsglobal.org/caliper/v1/ReadingEvent"),
    SESSION("http://purl.imsglobal.org/caliper/v1/SessionEvent"),
    VIEW("http://purl.imsglobal.org/caliper/v1/ViewEvent");

    private final String value;
    private static Map<String, EventType> lookup;

    /**
     * Create reverse lookup hash map
     */
    static {
        Map<String, EventType> map = new HashMap<String, EventType>();
        for (EventType constants : EventType.values()) {
            map.put(constants.getValue(), constants);
        }
        lookup = ImmutableMap.copyOf(map);
    }

    /**
     * Private constructor
     * @param value
     */
    private EventType(final String value) {
        this.value = value;
    }

    /**
     * @return URI string
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Retrieve enum type from reverse lookup map.
     * @param uri
     * @return Event.Type enum
     */
    public static EventType lookupConstantWithTypeURI(String uri) {
        return lookup.get(uri);
    }
}