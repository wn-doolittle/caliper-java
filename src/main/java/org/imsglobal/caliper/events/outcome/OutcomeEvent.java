package org.imsglobal.caliper.events.outcome;

import org.imsglobal.caliper.actions.OutcomeActions;
import org.imsglobal.caliper.entities.assessment.Attempt;
import org.imsglobal.caliper.entities.outcome.Result;
import org.imsglobal.caliper.events.CaliperEvent;

import java.util.ResourceBundle;

public class OutcomeEvent extends CaliperEvent {

    private final String context;
    private final String type;
    private final String action;
    private Object object;
    //private Generated generated;
    private Object generated;

    /**
     * @param builder apply builder object properties to the OutcomeEvent object.
     */
    protected OutcomeEvent(Builder<?> builder) {
        super(builder);
        this.context = builder.context;
        this.type = builder.type;
        this.action = builder.action;
        this.object = builder.object;
        this.generated = builder.generated;
    }

    /**
     public OutcomeEvent() {
     super();

     setContext("http://purl.imsglobal.org/ctx/caliper/v1/OutcomeEvent");
     setType("http://purl.imsglobal.org/caliper/v1/OutcomeEvent");
     }

     public static OutcomeEvent forAction(Action action) {
     OutcomeEvent event = new OutcomeEvent();
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
     * TODO original version did not include an accessor for generated.  Retain or drop?
     * @return object
     */
    @Override
    public Object getGenerated() {
        return generated;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperEvent.Builder<T>  {
        private static final String OUTCOMEEVENT_CONTEXT = "http://purl.imsglobal.org/ctx/caliper/v1/OutcomeEvent";
        private static final String OUTCOMEEVENT_TYPE = "http://purl.imsglobal.org/caliper/v1/OutcomeEvent";
        private String context;
        private String type;
        private String action;
        private Object object;
        //private Generated generated;
        private Object generated;

        /**
         * Initialize type with default valueS.  Required if .builder() properties are not set by user.
         */
        public Builder() {
            context(OUTCOMEEVENT_CONTEXT);
            type(OUTCOMEEVENT_TYPE);
        }

        /**
         * TODO Perhaps we should just auto-generate the context for the user; drop setter?
         * @param context
         * @return builder
         */
        @Override
        public T context(String context) {
            if (context.equals(OUTCOMEEVENT_CONTEXT)) {
                this.context = context;
            } else {
                this.context = OUTCOMEEVENT_CONTEXT;
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
            if (type.equals(OUTCOMEEVENT_TYPE)) {
                this.type = type;
            } else {
                this.type = OUTCOMEEVENT_TYPE;
            }
            return self();
        }

        /**
         * @param key
         * @return builder
         */
        @Override
        public T action(String key) {
            if (OutcomeActions.hasKey(key)) {
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
            if (object instanceof Attempt) {
                this.object = object;
                return self();
            } else {
                throw new ClassCastException("Object cannot be cast as a Caliper attempt");
                // TODO add logging
                // TODO do something clever with exception
            }
        }

        /**
        @Override
        public void setObject(Object object) {

            // TODO - Enforce restrictions on object type per metric profile
            if (getAction().equals(Action.graded.toString())) {
                if (!(object instanceof Attempt)) {
                    // TODO - throw proper exception
                }
            }

            super.setObject(object);
        }
        */

        /*
         * (non-Javadoc)
         * @see org.imsglobal.caliper.events.CaliperEvent#setGenerated(java.lang.Object)
         */

        /**
         * @param generated
         * @return builder
         */
        @Override
        public T generated(Object generated) {
            if (object instanceof Result) {
                this.generated = generated;
                return self();
            } else {
                throw new ClassCastException("Object cannot be cast as a Caliper result");
                // TODO add logging
                // TODO do something clever with exception
            }
        }

        /**
        @Override
        public void setGenerated(Object generated) {

            // TODO - Enforce restrictions on object type per metric profile
            if (getAction().equals(Action.graded.toString())) {
                if (!(generated instanceof Result)) {
                    // TODO - throw proper exception
                }
            }
            super.setGenerated(generated);
        }
        */

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of OutcomeEvent.
         */
        public OutcomeEvent build() {
            return new OutcomeEvent(this);
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