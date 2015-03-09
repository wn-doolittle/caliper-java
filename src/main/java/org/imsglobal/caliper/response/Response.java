package org.imsglobal.caliper.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableMap;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "description",
    "extensions",
    "dateCreated",
    "dateModified",
    "assignable",
    "actor",
    "attempt",
    "startedAtTime",
    "endedAtTime",
    "duration" })
public abstract class Response extends Entity implements Generatable {

    public enum Type {
        // DRAGOBJECT("http://purl.imsglobal.org/caliper/v1/Response/DragObject"),
        // ESSAY("http://purl.imsglobal.org/caliper/v1/Response/Essay"),
        // HOTSPOT("http://purl.imsglobal.org/caliper/v1/Response/HotSpot"),
        FILLINBLANK("http://purl.imsglobal.org/caliper/v1/Response/FillinBlank"),
        MULTIPLECHOICE("http://purl.imsglobal.org/caliper/v1/Response/MultipleChoice"),
        MULTIPLERESPONSE("http://purl.imsglobal.org/caliper/v1/Response/MultipleResponse"),
        SELECTTEXT("http://purl.imsglobal.org/caliper/v1/Response/SelectText"),
        // SHORTANSWER("http://purl.imsglobal.org/caliper/v1/Response/ShortAnswer"),
        // SLIDER("http://purl.imsglobal.org/caliper/v1/Response/Slider"),
        TRUEFALSE("http://purl.imsglobal.org/caliper/v1/Response/TrueFalse");

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
         * @param key
         * @return true if lookup returns a key match; false otherwise.
         */
        public static boolean hasKey(String key) {
            return lookup.containsKey(key);
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
         * @return Response.Type enum
         */
        public static Response.Type lookupConstantWithTypeURI(String uri) {
            return lookup.get(uri);
        }
    }

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("assignable")
    private final String assignableId; // retain? can retrieve from attempt

    @JsonProperty("actor")
    private final String actorId; // retain? can retrieve from attempt

    @JsonProperty("attempt")
    private Attempt attempt;

    @JsonProperty("startedAtTime")
    private DateTime startedAtTime;

    @JsonProperty("endedAtTime")
    private DateTime endedAtTime;

    @JsonProperty("duration")
    private String duration;

    /**
     * @param builder apply builder object properties to the Attempt object.
     */
    protected Response(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.assignableId = builder.assignableId;
        this.actorId = builder.actorId;
        this.attempt = builder.attempt;
        this.startedAtTime = builder.startedAtTime;
        this.endedAtTime = builder.endedAtTime;
        this.duration = builder.duration;
    }

    /**
     * @return the type
     */
    @Override
    @Nonnull
    public String getType() {
        return type;
    }

    /**
     * @return the assignable identifier
     */
    @Nonnull
    public String getAssignableId() {
        return assignableId;
    }

    /**
     * @return the actor identifier
     */
    @Nonnull
    public String getActorId() {
        return actorId;
    }

    /**
     * @return attempt associated with the response;
     */
    @Nonnull
    public Attempt getAttempt() {
        return attempt;
    }

    /**
     * @return started at time
     */
    @Nullable
    public DateTime getStartedAtTime() {
        return startedAtTime;
    }

    /**
     * @return ended at time
     */
    @Nullable
    public DateTime getEndedAtTime() {
        return endedAtTime;
    }

    /**
     * An xsd:duration (http://books.xmlschemata.org/relaxng/ch19-77073.html)
     * The format is expected to be PnYnMnDTnHnMnS.  Valid values include PT1004199059S, PT130S,
     * PT2M10S, P1DT2S, -P1Y, or P1Y2M3DT5H20M30.123S.  The following values are invalid:
     * 1Y (leading P is missing), P1S (T separator is missing), P-1Y (all parts must be positive),
     * P1M2Y (parts order is significant and Y must precede M) or P1Y-1M (all parts must be positive).
     *
     * @return duration
     */
    @Nullable
    public String getDuration() {
        return duration;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Entity.Builder<T>  {
        private String type;
        private String assignableId;
        private String actorId;
        private Attempt attempt;
        private DateTime startedAtTime;
        private DateTime endedAtTime;
        private String duration;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(Entity.Type.RESPONSE.uri());
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
         * @param assignableId
         * @return builder.
         */
        public T assignableId(String assignableId) {
            this.assignableId = assignableId;
            return self();
        }

        /**
         * @param actorId
         * @return builder.
         */
        public T actorId(String actorId) {
            this.actorId = actorId;
            return self();
        }

        /**
         * @param attempt
         * @return builder.
         */
        public T attempt(Attempt attempt) {
            this.attempt = attempt;
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
     *
     */
    private static class Builder2 extends Builder<Builder2> {
        @Override
        protected Builder2 self() {
            return this;
        }
    }
}