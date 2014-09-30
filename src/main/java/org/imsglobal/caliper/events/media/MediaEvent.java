package org.imsglobal.caliper.events.media;

import org.imsglobal.caliper.actions.MediaActions;
import org.imsglobal.caliper.entities.media.CaliperMediaObject;
import org.imsglobal.caliper.entities.media.MediaLocation;
import org.imsglobal.caliper.events.CaliperEvent;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ResourceBundle;

public class MediaEvent extends CaliperEvent {

    /**
    @JsonIgnoreType
    public enum Action {
        started, paused, ended, jumpedTo, forwardedTo, rewindedTo, changedSpeed,
        changedVolume, muted, unmuted, changedResolution, changedViewerSize,
        enteredFullScreen, exitedFullScreen, openPopup, closePopup,
        enabledCloseCaptioning, disabledCloseCaptioning
	}
    */

    private final String context;
    private final String type;
    private final String action;
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
        this.context = builder.context;
        this.type = builder.type;
        this.action = builder.action;
        this.object = builder.object;
        this.mediaLocation = builder.mediaLocation;
    }

    /**
     public MediaEvent() {
     super();

     setContext("http://purl.imsglobal.org/ctx/caliper/v1/MediaEvent");
     setType("http://purl.imsglobal.org/caliper/v1/MediaEvent");
     }

     public static MediaEvent forAction(Action action) {
     MediaEvent event = new MediaEvent();
     event.setAction(action.toString());
     return event;
     }
     */

    /**
     * @return context
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
     * TODO original version did not include an accessor for the object.  Retain or drop?
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
        private static final String MEDIAEVENT_CONTEXT = "http://purl.imsglobal.org/ctx/caliper/v1/MediaEvent";
        private static final String MEDIAEVENT_TYPE = "http://purl.imsglobal.org/caliper/v1/MediaEvent";
        private String context;
        private String type;
        private String action;
        private Object object;
        private MediaLocation mediaLocation;

        /**
         * Initialize type with default valueS.  Required if .builder() properties are not set by user.
         */
        public Builder() {
            context(MEDIAEVENT_CONTEXT);
            type(MEDIAEVENT_TYPE);
        }

        /**
         * TODO Perhaps we should just auto-generate the context for the user; drop setter?
         * @param context
         * @return builder
         */
        @Override
        public T context(String context) {
            if (context.equals(MEDIAEVENT_CONTEXT)) {
                this.context = context;
            } else {
                this.context = MEDIAEVENT_CONTEXT;
            }
            return self();
        }

        /**
         * TODO Perhaps we should just auto-generate the context for the user; drop setter?
         * @param type
         * @return builder
         */
        @Override
        public T type(String type) {
            if (type.equals(MEDIAEVENT_TYPE)) {
                this.type = type;
            } else {
                this.type = MEDIAEVENT_TYPE;
            }
            return self();
        }

        /**
         * @param key
         * @return builder
         */
        @Override
        public T action(String key) {
            if (MediaActions.hasKey(key)) {
                this.action = ResourceBundle.getBundle("resources.actions").getString(key);
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
         @Override
         public void setObject(Object object) {

         // TODO - Enforce restrictions on object type per metric profile
         if (getAction().equals(Action.started.toString())) {
         if (!(object instanceof CaliperMediaObject)) {
         // TODO - throw proper exception
         }
         }

         super.setObject(object);
         }
         */

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