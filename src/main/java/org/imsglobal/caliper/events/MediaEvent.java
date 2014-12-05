package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.entities.media.MediaLocation;
import org.imsglobal.caliper.entities.media.MediaObject;
import org.imsglobal.caliper.profiles.MediaProfile;

@JsonPropertyOrder({
    "@context",
    "@type",
    "actor",
    "action",
    "object",
    "mediaLocation",
    "target",
    "generated",
    "startedAtTime",
    "endedAtTime",
    "duration",
    "edApp",
    "group" })
public class MediaEvent extends org.imsglobal.caliper.events.Event {

    @JsonProperty("@context")
    private final String context;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("action")
    private final String action;

    @JsonProperty("object")
    private MediaObject object;

    /**
     * Describes the location within the media that is relevant to this event
     */
    @JsonProperty("mediaLocation")
    private MediaLocation mediaLocation;

    /**
     * @param builder apply builder object properties to the MediaEvent object.
     */
    protected MediaEvent(Builder<?> builder) {
        super(builder);
        this.context = builder.context;
        this.type = builder.type;
        this.action = builder.action;
        this.object = builder.object;
        this.mediaLocation = builder.mediaLocation;
    }

    /**
     * @return the context
     */
    @Override
    public String getContext() {
        return context;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return the action
     */
    @Override
    public String getAction() {
        return action;
    }

    /**
     * @return the media object
     */
    @Override
    public MediaObject getObject() {
        return object;
    }

    /**
     * @return the mediaLocation
     */
    public MediaLocation getMediaLocation() {
        return mediaLocation;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Event.Builder<T>  {
        private String context;
        private String type;
        private String action;
        private MediaObject object;
        private MediaLocation mediaLocation;

        /**
         * Initialize type with default valueS.  Required if .builder() properties are not set by user.
         */
        public Builder() {
            context(Event.Context.MEDIA.uri());
            type(Event.Type.MEDIA.uri());
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
         * @param key
         * @return builder.
         */
        @Override
        public T action(String key) {
            try {
                this.action = MediaProfile.getActionFromBundle(key);
            } catch (IllegalArgumentException e) {
                //TODO log and do something clever with exception.
            }

            return self();
        }

        /**
         * @param object
         * @return builder.
         */
        @Override
        public T object(Object object) {
            try {
                this.object = MediaProfile.validateObject(object);
            } catch (ClassCastException e) {
                //TODO log and do something clever with exception.
            }

            return self();
        }

        /**
         * @param mediaLocation
         * @return builder
         */
        public T mediaLocation(MediaLocation mediaLocation) {
            this.mediaLocation = mediaLocation;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of MediaEvent.
         */
        public MediaEvent build() {
            return new MediaEvent(this);
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