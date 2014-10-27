package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.actions.OutcomeActions;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.outcome.Result;

import java.util.ResourceBundle;

public class OutcomeEvent extends Event {

    @JsonProperty("@context")
    private final String context;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("action")
    private final String action;

    /**
     * Outcome activity context
     */
    @JsonProperty("object")
    private Object object;

    /**
     * Outcome generated result
     */
    @JsonProperty("generated")
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
     * @return object
     */
    @Override
    public Object getObject() {
        return object;
    }

    /**
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
    public static abstract class Builder<T extends Builder<T>> extends Event.Builder<T>  {
        private String context;
        private String type;
        private String action;
        private Object object;
        private Object generated;

        /**
         * Initialize type with default values.
         */
        public Builder() {
            context(Event.Context.OUTCOME.uri());
            type(Event.Type.OUTCOME.uri());
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
         * @see org.imsglobal.caliper.events.Event#setObject(java.lang.Object)
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

        /*
         * (non-Javadoc)
         * @see org.imsglobal.caliper.events.Event#setGenerated(java.lang.Object)
         */

        /**
         * @param generated
         * @return builder
         */
        @Override
        public T generated(Object generated) {
            if (generated instanceof Result) {
                this.generated = generated;
                return self();
            } else {
                throw new ClassCastException("Object cannot be cast as a Caliper result");
                // TODO add logging
                // TODO do something clever with exception
            }
        }

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