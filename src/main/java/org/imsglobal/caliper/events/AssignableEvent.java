package org.imsglobal.caliper.events.assignable;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.actions.AssignableActions;
import org.imsglobal.caliper.entities.assignable.CaliperAssignableDigitalResource;
import org.imsglobal.caliper.events.CaliperEvent;

import java.util.ResourceBundle;

public class AssignableEvent extends CaliperEvent {

    @JsonProperty("@context")
    private final String context;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("action")
    private final String action;

    /**
     * Assignable activity context
     */
    private Object object;

    /**
     * @param builder apply builder object properties to the AssignableEvent object.
     */
    protected AssignableEvent(Builder<?> builder) {
        super(builder);
        this.context = builder.context;
        this.type = builder.type;
        this.action = builder.action;
        this.object = builder.object;
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
     * TODO original version did not include an accessor for the object.  Retain or drop?
     * @return object
     */
    @Override
    public Object getObject() {
        return object;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperEvent.Builder<T>  {
        private String context;
        private String type;
        private String action;
        private Object object;

        /**
         * Initialize type with default valueS.  Required if .builder() properties are not set by user.
         */
        public Builder() {
            context(CaliperEvent.Context.ASSIGNABLE.uri());
            type(CaliperEvent.Type.ASSIGNABLE.uri());
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
            if (AssignableActions.hasKey(key)) {
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
            if (object instanceof CaliperAssignableDigitalResource) {
                this.object = object;
                return self();
            } else {
                throw new ClassCastException("Object cannot be cast as a Caliper assignable digital resource");
                // TODO add logging
                // TODO do something clever with exception
            }
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of AssignableEvent.
         */
        public AssignableEvent build() {
            return new AssignableEvent(this);
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