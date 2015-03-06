package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableMap;
import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.lis.Organization;
import org.imsglobal.caliper.entities.schemadotorg.SoftwareApplication;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({
    "@context",
    "@type",
    "actor",
    "action",
    "object",
    "target",
    "generated",
    "startedAtTime",
    "endedAtTime",
    "duration",
    "edApp",
    "group" })
public abstract class Event {

    public enum Context {
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

        private final String uri;
        private static Map<String, Context> lookup;

        /**
         * Create reverse lookup hash map
         */
        static {
            Map<String, Context> map = new HashMap<String, Context>();
            for (Context constants : Context.values()) {
                map.put(constants.uri(), constants);
            }
            lookup = ImmutableMap.copyOf(map);
        }

        /**
         * Private constructor
         * @param uri
         */
        private Context(final String uri) {
            this.uri = uri;
        }

        /**
         * @return URI string
         */
        public String uri() {
            return uri;
        }

        /**
         * Retrieve enum type from reverse lookup map.
         * @param uri
         * @return Event.Context enum
         */
        public static Event.Context lookupConstantWithContextURI(String uri) {
            return lookup.get(uri);
        }
    }

    public enum Type {
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

        private final String uri;
        private static Map<String, Type> lookup;

        /**
         * Create reverse lookup hash map
         */
        static {
            Map<String, Type> map = new HashMap<String, Type>();
            for (Type constants : Type.values()) {
                map.put(constants.uri(), constants);
            }
            lookup = ImmutableMap.copyOf(map);
        }

        /**
         * Private constructor
         * @param uri
         */
        private Type(final String uri) {
            this.uri = uri;
        }

        /**
         * @return URI string
         */
        public String uri() {
            return uri;
        }

        /**
         * Retrieve enum type from reverse lookup map.
         * @param uri
         * @return Event.Type enum
         */
        public static Event.Type lookupConstantWithTypeURI(String uri) {
            return lookup.get(uri);
        }
    }

    @JsonProperty("@context")
    private final String context;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("edApp")
    private final SoftwareApplication edApp;

    @JsonProperty("group")
    private final Organization lisOrganization;

    @JsonProperty("actor")
    private final Agent actor;

    @JsonProperty("action")
    private final String action;

    @JsonProperty("object")
    private final Object object;

    @JsonProperty("target")
    private final Targetable target;

    @JsonProperty("generated")
    private final Generatable generated;

    @JsonProperty("startedAtTime")
    private final DateTime startedAtTime;

    @JsonProperty("endedAtTime")
    private final DateTime endedAtTime;

    @JsonProperty("duration")
    private final String duration;

    @JsonIgnore
    private static final Logger log = LoggerFactory.getLogger(Event.class);

    /**
     * @param builder apply builder object properties to the Entity object.
     */
    protected Event(Builder<?> builder) {
        this.context = builder.context;
        this.type = builder.type;
        this.edApp = builder.edApp;
        this.lisOrganization = builder.lisOrganization;
        this.actor = builder.actor;
        this.action = builder.action;
        this.object = builder.object;
        this.target = builder.target;
        this.generated = builder.generated;
        this.startedAtTime = builder.startedAtTime;
        this.endedAtTime = builder.endedAtTime;
        this.duration = builder.duration;

        // TODO consider letting the Sensor calculate the duration (make duration setter private)
        // TODO also consider changing the duration type from String to Duration (Jackson can serialize duration)
        /**
        if (startedAtTime != null && endedAtTime != null) {
            System.out.println(ProfileUtils.getDuration(startedAtTime, endedAtTime).toString());
        }
        */
    }

    /**
     * Required.
     * @return the context
     */
    @Nonnull
    public String getContext() {
        return context;
    }

    /**
     * Required.
     * @return the type
     */
    @Nonnull
    public String getType() {
        return type;
    }

    /**
     * Optional.
     * @return the edApp
     */
    @Nullable
    public SoftwareApplication getEdApp() {
        return edApp;
    }

    /**
     * Optional.
     * @return the lisOrganization
     */
    @Nullable
    public Organization getLisOrganization() {
        return lisOrganization;
    }

    /**
     * Required.
     * @return the actor
     */
    @Nonnull
    public Agent getActor() {
        return actor;
    }

    /**
     * Required.
     * @return the action
     */
    @Nonnull
    public String getAction() {
        return action;
    }

    /**
     * Required.
     * @return the object
     */
    @Nonnull
    public Object getObject() {
        return object;
    }

    /**
     * Optional.
     * @return the target
     */
    @Nullable
    public Targetable getTarget() {
        return target;
    }

    /**
     * Optional.
     * @return generated
     */
    @Nullable
    public Generatable getGenerated() {
        return generated;
    }

    /**
     * Required.
     * @return the startedAt time
     */
    @Nonnull
    public DateTime getStartedAtTime() {
        return startedAtTime;
    }

    /**
     * Optional.
     * @return endedAt time
     */
    @Nullable
    public DateTime getEndedAtTime() {
        return endedAtTime;
    }

    /**
     * Optional. An xsd:duration (http://books.xmlschemata.org/relaxng/ch19-77073.html)
     * The format is expected to be PnYnMnDTnHnMnS
     * Valid values include PT1004199059S, PT130S, PT2M10S, P1DT2S, -P1Y, or P1Y2M3DT5H20M30.123S.
     * The following values are invalid: 1Y (leading P is missing), P1S (T separator is missing),
     * P-1Y (all parts must be positive), P1M2Y (parts order is significant and Y must precede M),
     * or P1Y-1M (all parts must be positive).
     * @return the duration
     */
    @Nullable
    public String getDuration() {
        return duration;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> {
        private String context;
        private String type;
        private SoftwareApplication edApp;
        private Organization lisOrganization;
        private Agent actor;
        private String action;
        private Object object;
        private Targetable target;
        private Generatable generated;
        private DateTime startedAtTime;
        private DateTime endedAtTime;
        private String duration;

        protected abstract T self();

        /**
         * Initialize type with default values.
         */
        public Builder() {
            context(Event.Context.EVENT.uri());
            type(Event.Type.EVENT.uri());
        }

        /**
         * @param context
         * @return builder.
         */
        private T context(String context) {
            this.context = context;
            return self();
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(String type) {
            this.type = type;
            return self();
        }

        /**
         * @param edApp
         * @return builder.
         */
        public T edApp(SoftwareApplication edApp) {
            this.edApp = edApp;
            return self();
        }

        /**
         * @param lisOrganization
         * @return builder.
         */
        public T lisOrganization(Organization lisOrganization) {
            this.lisOrganization = lisOrganization;
            return self();
        }

        /**
         * @param actor
         * @return builder.
         */
        public T actor(Agent actor) {
            this.actor = actor;
            return self();
        }

        /**
         * @param action
         * @return builder.
         */
        public T action(String action) {
            this.action = action;
            return self();
        }

        /**
         * @param object
         * @return builder.
         */
        public T object(Object object) {
            this.object = object;
            return self();
        }

        /**
         * @param target
         * @return builder.
         */
        public T target(Targetable target) {
            this.target = target;
            return self();
        }

        /**
         * @param generated
         * @return builder.
         */
        public T generated(Generatable generated) {
            this.generated = generated;
            return self();
        }

        /**
         * @param startedAtTime
         * @return builder.
         */
        public T startedAtTime(DateTime startedAtTime) {
            this.startedAtTime = startedAtTime;
            return self();
        }

        /**
         * @param endedAtTime
         * @return builder.
         */
        public T endedAtTime(DateTime endedAtTime) {
            this.endedAtTime = endedAtTime;
            return self();
        }

        /**
         * @param duration
         * @return builder.
         */
        public T duration(String duration) {
            this.duration = duration;
            return self();
        }
    }

    /**
     * Self-reference that permits sub-classing of builder.
     */
    private static class Builder2 extends Builder<Builder2> {
        @Override
        protected Builder2 self() {
            return this;
        }
    }
}