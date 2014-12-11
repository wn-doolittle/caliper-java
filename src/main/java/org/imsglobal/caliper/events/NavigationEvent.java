package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.profiles.Profile;

public class NavigationEvent extends org.imsglobal.caliper.events.Event {

    @JsonProperty("@context")
    private final String context;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("action")
    private final String action;

    @JsonProperty("object")
    private final DigitalResource object;

    /**
     * Describes the resource from which the navigation starts.
     */
    @JsonProperty("navigatedFrom")
    private DigitalResource fromResource;

    @JsonProperty("target")
    private final Targetable target;

    /**
     * @param builder apply builder object properties to the NavigationEvent object.
     */
    protected NavigationEvent(Builder<?> builder) {
        super(builder);
        this.context = builder.context;
        this.type = builder.type;
        this.action = builder.action;
        this.object = builder.object;
        this.fromResource = builder.fromResource;
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
     * @return the activity context.
     */
    @Override
    public DigitalResource getObject() {
        return object;
    }

    /**
     * @return the fromResource
     */
    public DigitalResource getFromResource() {
        return fromResource;
    }

    /**
     * @return the target resource.
     */
    @Override
    public Targetable getTarget() {
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
        private DigitalResource fromResource;
        private Targetable target;

        /**
         * Initialize type with default values.
         */
        public Builder() {
            context(Event.Context.NAVIGATION.uri());
            type(Event.Type.NAVIGATION.uri());
            action(Profile.Actions.NAVIGATED_TO.key());
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
                this.action = Profile.getNavigatedToActionFromBundle(key);
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
                this.object = Profile.validateDigitalResource(object);
            } catch (ClassCastException e) {
                //TODO log and do something clever with exception.
            }

            return self();
        }

        /**
         * @param fromResource
         * @return builder
         */
        public T fromResource(DigitalResource fromResource) {
            this.fromResource = fromResource;
            return self();
        }

        /**
         * @param target
         * @return builder.
         */
        @Override
        public T target(Targetable target) {
            try {
                this.target = Profile.validateTarget(target);
            } catch (ClassCastException e) {
                //TODO log and do something clever with exception.
            }

            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of NavigationEvent.
         */
        public NavigationEvent build() {
            return new NavigationEvent(this);
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