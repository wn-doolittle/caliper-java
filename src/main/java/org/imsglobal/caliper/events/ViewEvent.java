package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.reading.Frame;
import org.imsglobal.caliper.profiles.ReadingProfile;

public class ViewEvent extends org.imsglobal.caliper.events.Event {

    @JsonProperty("@context")
    private final String context;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("action")
    private final String action;

    @JsonProperty("object")
    private final DigitalResource object;

    @JsonProperty("target")
    private final Frame target;

    /**
     * @param builder apply builder object properties to the ViewEvent object.
     */
    protected ViewEvent(Builder<?> builder) {
        super(builder);
        this.context = builder.context;
        this.type = builder.type;
        this.action = builder.action;
        this.object = builder.object;
        this.target = builder.target;
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
     * @return digital resource object.
     */
    @Override
    public DigitalResource getObject() {
        return object;
    }

    public Frame getTarget() {
        return target;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Event.Builder<T>  {
        private String context;
        private String type;
        private String action;
        private DigitalResource object;
        private Frame target;

        /**
         * Initialize type with default values.  Required if .builder() properties are not set by user.
         */
        public Builder() {
            context(Event.Context.VIEW.uri());
            type(Event.Type.VIEW.uri());
            action(ReadingProfile.ReadingActions.VIEWED.key());
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
         * @return builder
         */
        @Override
        public T action(String key) {
            try {
                this.action = ReadingProfile.getViewActionFromBundle(key);
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
                this.object = ReadingProfile.validateObject(object);
            } catch (ClassCastException e) {
                //TODO log and do something clever with exception.
            }

            return self();
        }

        /**
         * @param target
         * @return builder.
         */
        @Override
        public T target(Object target) {
            try {
                this.target = ReadingProfile.validateTarget(target);
            } catch (ClassCastException e) {
                //TODO log and do something clever with exception.
            }

            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of ViewEvent.
         */
        public ViewEvent build() {
            return new ViewEvent(this);
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