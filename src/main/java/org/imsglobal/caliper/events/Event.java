package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;
import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.schemadotorg.SoftwareApplication;
import org.imsglobal.caliper.entities.w3c.Organization;
import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.validators.EventValidator;
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

        private final String value;
        private static Map<String, Context> lookup;

        /**
         * Create reverse lookup hash map
         */
        static {
            Map<String, Context> map = new HashMap<String, Context>();
            for (Context constants : Context.values()) {
                map.put(constants.getValue(), constants);
            }
            lookup = ImmutableMap.copyOf(map);
        }

        /**
         * Private constructor
         * @param value
         */
        private Context(final String value) {
            this.value = value;
        }

        /**
         * @return URI value string
         */
        @JsonValue
        public String getValue() {
            return value;
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

        private final String value;
        private static Map<String, Type> lookup;

        /**
         * Create reverse lookup hash map
         */
        static {
            Map<String, Type> map = new HashMap<String, Type>();
            for (Type constants : Type.values()) {
                map.put(constants.getValue(), constants);
            }
            lookup = ImmutableMap.copyOf(map);
        }

        /**
         * Private constructor
         * @param value
         */
        private Type(final String value) {
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
        public static Event.Type lookupConstantWithTypeURI(String uri) {
            return lookup.get(uri);
        }
    }

    @JsonProperty("@context")
    private final Context context;

    @JsonProperty("@type")
    private final Type type;

    @JsonProperty("edApp")
    private final SoftwareApplication edApp;

    @JsonProperty("group")
    private final Organization group;

    @JsonProperty("actor")
    private final Agent actor;

    @JsonProperty("action")
    protected final Action action;

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
     * Utilize builder to construct Event.  Validate object copy rather than the
     * builder.  This approach protects the class against parameter changes from another
     * thread during the "window of vulnerability" between the time the parameters are checked
     * until when they are copied.
     *
     * @param builder
     */
    protected Event(Builder<?> builder) {

        // Validator.checkContextUri(builder.context, Context.EVENT);
        // Validator.checkTypeUri(builder.type, Type.EVENT);
        // Validator.checkAction(builder.action, Event.class);
        EventValidator.checkStartTime(builder.startedAtTime, builder.endedAtTime);
        EventValidator.checkDuration(builder.startedAtTime, builder.endedAtTime, builder.duration);

        this.context = builder.context;
        this.type = builder.type;
        this.edApp = builder.edApp;
        this.group = builder.group;
        this.actor = builder.actor;
        this.action = builder.action;
        this.object = builder.object;
        this.target = builder.target;
        this.generated = builder.generated;
        this.startedAtTime = builder.startedAtTime;
        this.endedAtTime = builder.endedAtTime;
        this.duration = builder.duration;
    }

    /**
     * Required.
     * @return the context
     */
    @Nonnull
    public Context getContext() {
        return context;
    }

    /**
     * Required.
     * @return the type
     */
    @Nonnull
    public Type getType() {
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
     * @return the group
     */
    @Nullable
    public Organization getGroup() {
        return group;
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
    public Action getAction() {
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
        private Context context;
        private Type type;
        private SoftwareApplication edApp;
        private Organization group;
        private Agent actor;
        private Action action;
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
            context(Event.Context.EVENT);
            type(Event.Type.EVENT);
        }

        /**
         * @param context
         * @return builder.
         */
        private T context(Context context) {
            this.context = context;
            return self();
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(Type type) {
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
         * @param group
         * @return builder.
         */
        public T group(Organization group) {
            this.group = group;
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
        public T action(Action action) {
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