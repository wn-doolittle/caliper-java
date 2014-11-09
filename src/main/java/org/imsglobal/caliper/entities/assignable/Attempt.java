package org.imsglobal.caliper.entities.assignable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.entities.Agent;
import org.imsglobal.caliper.entities.Entity;

/**
 * Representation of an Attempt. Attempts are generated as part of or
 * are the object of an interaction represented by an AssignableEvent
 */
@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "properties",
    "lastModifiedTime",
    "count",
    "startedAtTime",
    "endedAtTime",
    "duration" })
public class Attempt extends org.imsglobal.caliper.entities.Entity {

    @JsonProperty("@type")
    private final String type;

    @JsonIgnore
    private final Assignable assignable;

    @JsonIgnore
    private final Agent actor;

    @JsonProperty("count")
    private int count;

    @JsonProperty("startedAtTime")
    private long startedAtTime;

    @JsonProperty("endedAtTime")
    private long endedAtTime;

    /**
     * An xsd:duration (http://books.xmlschemata.org/relaxng/ch19-77073.html)
     * The format is expected to be PnYnMnDTnHnMnS
     * Valid values include PT1004199059S, PT130S, PT2M10S, P1DT2S, -P1Y, or P1Y2M3DT5H20M30.123S.
     * The following values are invalid: 1Y (leading P is missing), P1S (T separator is missing),
     * P-1Y (all parts must be positive), P1M2Y (parts order is significant and Y must precede M),
     * or P1Y-1M (all parts must be positive).
     */
    @JsonProperty("duration")
    private String duration;

    /**
     * @param builder apply builder object properties to the Attempt object.
     */
    protected Attempt(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.assignable = builder.assignable;
        this.actor = builder.actor;
        this.count = builder.count;
        this.startedAtTime = builder.startedAtTime;
        this.endedAtTime = builder.endedAtTime;
        this.duration = builder.duration;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return the assignable
     */
    public Assignable getAssignable() {
        return assignable;
    }

    /**
     * @return the actor
     */
    public Agent getActor() {
        return actor;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @return started at time
     */
    public long getStartedAtTime() {
        return startedAtTime;
    }

    /**
     * @return ended at time
     */
    public long getEndedAtTime() {
        return endedAtTime;
    }

    /**
     * @param endedAtTime
     */
    public void updateEndedAtTime(long endedAtTime) {
        this.endedAtTime = endedAtTime;
    }

    /**
     * @return duration of event
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration
     */
    public void updateDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Entity.Builder<T>  {
        private String type;
        private Assignable assignable;
        private Agent actor;
        private int count;
        private long startedAtTime;
        private long endedAtTime;
        private String duration;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(Entity.Type.ATTEMPT.uri());
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
         * @param assignable
         * @return builder
         */
        public T assignable(Assignable assignable) {
            this.assignable = assignable;
            return self();
        }

        /**
         * @param actor
         * @return builder
         */
        public T actor(Agent actor) {
            this.actor = actor;
            return self();
        }

        /**
         * @param count
         * @return builder
         */
        public T count(int count) {
            this.count = count;
            return self();
        }

        /**
         * @param startedAtTime
         * @return
         */
        public T startedAtTime(long startedAtTime) {
            this.startedAtTime = startedAtTime;
            return self();
        }

        /**
         * @param endedAtTime
         * @return builder
         */
        public T endedAtTime(long endedAtTime) {
            this.endedAtTime = endedAtTime;
            return self();
        }

        /**
         * @param duration
         * @return
         */
        public T duration(String duration) {
            this.duration = duration;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of Attempt.
         */
        public Attempt build() {
            return new Attempt(this);
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

    /**
     * Static factory method.
     * @return a new instance of the builder.
     */
    public static Builder<?> builder() {
        return new Builder2();
    }
}