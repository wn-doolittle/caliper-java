package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.entities.foaf.Agent;

import java.util.Date;

@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "lastModifiedTime",
    "actor",
    "startedAtTime",
    "endedAtTime",
    "duration" })
public class Session extends Entity implements org.imsglobal.caliper.entities.Generatable,
                                               org.imsglobal.caliper.entities.Targetable {
    @JsonProperty("@type")
    private final String type;

    @JsonProperty("actor")
    private final Agent actor;

    @JsonProperty("startedAtTime")
    private Date startedAtTime;

    @JsonProperty("endedAtTime")
    private Date endedAtTime;

    @JsonProperty("duration")
    private String duration;

    /**
     * @param builder apply builder object properties to the WebPage object.
     */
    protected Session(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.actor = builder.actor;
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
     * @return the actor
     */
    public Agent getActor() {
        return actor;
    }

    /**
     * @return session start time
     */
    public Date getStartedAtTime() {
        return startedAtTime;
    }

    /**
     * @return session end time
     */
    public Date getEndedAtTime() {
        return endedAtTime;
    }

    /**
     * @return session duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Entity.Builder<T>  {
        private String type;
        private Agent actor;
        private Date startedAtTime;
        private Date endedAtTime;
        private String duration;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(Entity.Type.SESSION.uri());
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
         * @param actor
         * @return builder.
         */
        public T actor(Agent actor) {
            this.actor = actor;
            return self();
        }

        /**
         * @param startedAtTime
         * @return builder.
         */
        public T startedAtTime(Date startedAtTime) {
            this.startedAtTime = startedAtTime;
            return self();
        }

        /**
         * @param endedAtTime
         * @return builder.
         */
        public T endedAtTime(Date endedAtTime) {
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

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of WebPage.
         */
        public Session build() {
            return new Session(this);
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