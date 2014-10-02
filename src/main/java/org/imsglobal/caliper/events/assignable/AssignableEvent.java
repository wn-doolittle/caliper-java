package org.imsglobal.caliper.events.assignable;

import org.imsglobal.caliper.actions.AssignableActions;
import org.imsglobal.caliper.entities.assignable.CaliperAssignableDigitalResource;
import org.imsglobal.caliper.events.CaliperEvent;

import java.util.ResourceBundle;

public class AssignableEvent extends CaliperEvent {

    public enum Identifier {
        CONTEXT("http://purl.imsglobal.org/ctx/caliper/v1/AssignableEvent"),
        TYPE("http://purl.imsglobal.org/caliper/v1/AssignableEvent");

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
     * Assignable action
     */
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
        this.action = builder.action;
        this.object = builder.object;
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
        private String action;
        private Object object;

        /**
         * Initialize type with default valueS.  Required if .builder() properties are not set by user.
         */
        public Builder() {
            context(AssignableEvent.Identifier.CONTEXT.uri());
            type(AssignableEvent.Identifier.TYPE.uri());
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