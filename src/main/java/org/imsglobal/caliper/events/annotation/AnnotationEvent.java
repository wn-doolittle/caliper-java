package org.imsglobal.caliper.events.annotation;

import org.imsglobal.caliper.actions.AnnotationActions;
import org.imsglobal.caliper.events.CaliperEvent;

import java.util.ResourceBundle;

public class AnnotationEvent extends CaliperEvent {

    private final String context;
    private final String type;
    private final String action;

    /**
     * @param builder apply builder object properties to the AnnotationEvent object.
     */
    protected AnnotationEvent(Builder<?> builder) {
        super(builder);
        this.context = builder.context;
        this.type = builder.type;
        this.action = builder.action;
    }

    /**
     public AnnotationEvent() {
     setContext("http://purl.imsglobal.org/ctx/caliper/v1/AnnotationEvent");
     setType("http://purl.imsglobal.org/caliper/v1/AnnotationEvent");
     }

     public static AnnotationEvent forAction(String action) {
     AnnotationEvent event = new AnnotationEvent();
     event.setAction(action);
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
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperEvent.Builder<T>  {
        private static final String ANNOTATIONEVENT_CONTEXT = "http://purl.imsglobal.org/ctx/caliper/v1/AnnotationEvent";
        private static final String ANNOTATIONEVENT_TYPE = "http://purl.imsglobal.org/caliper/v1/AnnotationEvent";
        private String context;
        private String type;
        private String action;

        /**
         * Initialize type with default valueS.  Required if .builder() properties are not set by user.
         */
        public Builder() {
            context(ANNOTATIONEVENT_CONTEXT);
            type(ANNOTATIONEVENT_TYPE);
        }

        /**
         * TODO Perhaps we should always just auto-generate the context for the user; drop setter?
         * @param context
         * @return builder
         */
        @Override
        public T context(String context) {
            if (type.equals(ANNOTATIONEVENT_CONTEXT)) {
                this.context = type;
            } else {
                this.context = ANNOTATIONEVENT_CONTEXT;
            }
            return self();
        }

        /**
         * TODO Perhaps we should always just auto-generate the context for the user; drop setter?
         * @param type
         * @return builder
         */
        @Override
        public T type(String type) {
            if (type.equals(ANNOTATIONEVENT_TYPE)) {
                this.type = type;
            } else {
                this.type = ANNOTATIONEVENT_TYPE;
            }
            return self();
        }

        /**
         * @param key
         * @return builder
         */
        @Override
        public T action(String key) {
            if (AnnotationActions.hasKey(key)) {
                this.action = ResourceBundle.getBundle("resources.actions").getString(key);
                return self();
            } else {
                throw new IllegalArgumentException("Unrecognized constant");
            }
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of AnnotationEvent.
         */
        public AnnotationEvent build() {
            return new AnnotationEvent(this);
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