package org.imsglobal.caliper.events.media;

import org.imsglobal.caliper.actions.MediaActions;
import org.imsglobal.caliper.entities.media.CaliperMediaObject;
import org.imsglobal.caliper.entities.media.MediaLocation;
import org.imsglobal.caliper.events.CaliperEvent;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ResourceBundle;

public class MediaEvent extends CaliperEvent {

    public enum Identifier {
        CONTEXT("http://purl.imsglobal.org/ctx/caliper/v1/MediaEvent"),
        TYPE("http://purl.imsglobal.org/caliper/v1/MediaEvent");

        private final String uri;

        /**
         * Private constructor
         * @param uri
         */
        private Identifier(final String uri) {
            this.uri = uri;
        }

        /**
         * @return URI string
         */
        public String uri() {
            return uri;
        }
    }

    /**
     * Media action
     */
    private final String action;

    /**
     * Media activity context
     */
    private Object object;

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
        this.action = builder.action;
        this.object = builder.object;
        this.mediaLocation = builder.mediaLocation;
    }

    /**
     * @return the action
     */
    @Override
    public String getAction() {
        return action;
    }

    /**
     * @return object
     */
    @Override
    public Object getObject() {
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
    public static abstract class Builder<T extends Builder<T>> extends CaliperEvent.Builder<T>  {
        private String action;
        private Object object;
        private MediaLocation mediaLocation;

        /**
         * Initialize type with default valueS.  Required if .builder() properties are not set by user.
         */
        public Builder() {
            context(MediaEvent.Identifier.CONTEXT.uri());
            type(MediaEvent.Identifier.TYPE.uri());
        }

        /**
         * @param key
         * @return builder
         */
        @Override
        public T action(String key) {
            if (MediaActions.hasKey(key)) {
                this.action = ResourceBundle.getBundle("actions").getString(key);
                return self();
            } else {
                throw new IllegalArgumentException("Unrecognized constant: " + key);
                // TODO add logging
                // TODO do something clever with exception
            }
        }

        /*
        * (non-Javadoc)
        * @see org.imsglobal.caliper.events.CaliperEvent#setObject(java.lang.Object)
        */

        /**
         * @param object
         * @return builder
         */
        @Override
        public T object(Object object) {
            if (object instanceof CaliperMediaObject) {
                this.object = object;
                return self();
            } else {
                throw new ClassCastException("Object cannot be cast as a Caliper media object");
                // TODO add logging
                // TODO do something clever with exception
            }
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