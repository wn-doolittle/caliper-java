package org.imsglobal.caliper.events;

import org.imsglobal.caliper.actions.ReadingActions;
import org.imsglobal.caliper.entities.DigitalResource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ResourceBundle;

public class NavigationEvent extends Event {

    @JsonProperty("@context")
    private final String context;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("action")
    private final String action;

    /**
     * Describes the resource from which the navigation starts
     */
    @JsonProperty("navigatedFrom")
    private DigitalResource fromResource;

    /**
     * @param builder apply builder object properties to the NavigationEvent object.
     */
    protected NavigationEvent(Builder<?> builder) {
        super(builder);
        this.context = builder.context;
        this.type = builder.type;
        this.action = builder.action;
        this.fromResource = builder.fromResource;
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
     * @return the fromResource
     */
    public DigitalResource getFromResource() {
        return fromResource;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Event.Builder<T>  {
        private String context;
        private String type;
        private String action;
        private DigitalResource fromResource;

        /**
         * Initialize type with default values.
         */
        public Builder() {
            context(Event.Context.NAVIGATION.uri());
            type(Event.Type.NAVIGATION.uri());
            action(ReadingActions.NAVIGATED_TO.key());
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
            if (key.equals("navigation.navigatedTo")) {
                this.action = ResourceBundle.getBundle("actions").getString(key);
                return self();
            } else {
                throw new IllegalArgumentException("Unrecognized constant: " + key);
            }
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