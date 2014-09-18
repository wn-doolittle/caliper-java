package org.imsglobal.caliper.events.assignable;

import org.imsglobal.caliper.actions.AssignableActions;
import org.imsglobal.caliper.entities.assignable.CaliperAssignableDigitalResource;
import org.imsglobal.caliper.events.CaliperEvent;

import java.util.ResourceBundle;

public class AssignableEvent extends CaliperEvent {

    private final String context;
    private final String type;
    private final String action;
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
    public AssignableEvent() {
    super();

    setContext("http://purl.imsglobal.org/ctx/caliper/v1/AssignableEvent");
    setType("http://purl.imsglobal.org/caliper/v1/AssignableEvent");
    }
    */

    /**
    public static AssignableEvent forAction(Action action) {
    AssignableEvent event = new AssignableEvent();
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
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperEvent.Builder<T>  {
        private static final String ASSIGNABLEEVENT_CONTEXT = "http://purl.imsglobal.org/ctx/caliper/v1/AssignableEvent";
        private static final String ASSIGNABLEEVENT_TYPE = "http://purl.imsglobal.org/caliper/v1/AssignableEvent";
        private String context;
        private String type;
        private String action;
        private Object object;

        /**
         * Initialize type with default valueS.  Required if .builder() properties are not set by user.
         */
        public Builder() {
            context(ASSIGNABLEEVENT_CONTEXT);
            type(ASSIGNABLEEVENT_TYPE);
        }

        /**
         * TODO Perhaps we should just auto-generate the context for the user; drop setter?
         * @param context
         * @return builder
         */
        @Override
        public T context(String context) {
            if (type.equals(ASSIGNABLEEVENT_CONTEXT)) {
                this.context = type;
            } else {
                this.context = ASSIGNABLEEVENT_CONTEXT;
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
            if (type.equals(ASSIGNABLEEVENT_TYPE)) {
                this.type = type;
            } else {
                this.type = ASSIGNABLEEVENT_TYPE;
            }
            return self();
        }

        /**
         * @param key
         * @return builder
         */
        @Override
        public T action(String key) {
            if (AssignableActions.hasKey(key)) {
                this.action = ResourceBundle.getBundle("resources.actions").getString(key);
                return self();
            } else {
                throw new IllegalArgumentException("Unrecognized constant");
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

        /** (non-Javadoc)
        * @see org.imsglobal.caliper.events.CaliperEvent#setObject(java.lang.Object)
        */
        /**
         @Override
         public void setObject(Object object) {

         // TODO - Enforce restrictions on object type per metric profile
         if (getAction().equals(Action.started.toString())) {
         if (!(object instanceof CaliperAssignableDigitalResource)) {
         // TODO - throw proper exception
         }
         }

         super.setObject(object);
         }
         */

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