package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.annotation.Annotation;
import org.imsglobal.caliper.profiles.AnnotationProfile;

public class AnnotationEvent extends org.imsglobal.caliper.events.Event {

    @JsonProperty("@context")
    private final String context;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("action")
    private final String action;

    @JsonProperty("object")
    private final Annotation object;

    @JsonProperty("target")
    private final DigitalResource target;

    /**
     * @param builder apply builder object properties to the AnnotationEvent object.
     */
    protected AnnotationEvent(Builder<?> builder) {
        super(builder);
        this.context = builder.context;
        this.type = builder.type;
        this.action = builder.action;
        this.object = builder.object;
        this.target = builder.target;
    }

    /**
     * @return the context.
     */
    @Override
    public String getContext() {
        return context;
    }

    /**
     * @return the type.
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return the action.
     */
    @Override
    public String getAction() {
        return action;
    }

    /**
     * @return the annotation object.
     */
    @Override
    public Annotation getObject() {
        return object;
    }

    /**
     * @return target digital resource.
     */
    @Override
    public DigitalResource getTarget() {
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
        private Annotation object;
        private DigitalResource target;

        /**
         * Initialize with default values.
         */
        public Builder() {
            context(Event.Context.ANNOTATION.uri());
            type(Event.Type.ANNOTATION.uri());
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
                this.action = AnnotationProfile.getActionFromBundle(key);
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
                this.object = AnnotationProfile.validateObject(object);
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
                this.target = AnnotationProfile.validateTarget(target);
            } catch (ClassCastException e) {
                //TODO log and do something clever with exception.
            }

            return self();
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