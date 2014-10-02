package org.imsglobal.caliper.events.outcome;

import org.imsglobal.caliper.actions.OutcomeActions;
import org.imsglobal.caliper.entities.assessment.Attempt;
import org.imsglobal.caliper.entities.outcome.Result;
import org.imsglobal.caliper.events.CaliperEvent;

import java.util.ResourceBundle;

public class OutcomeEvent extends CaliperEvent {

    public enum Identifier {
        CONTEXT("http://purl.imsglobal.org/ctx/caliper/v1/OutcomeEvent"),
        TYPE("http://purl.imsglobal.org/caliper/v1/OutcomeEvent");

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
     * Outcome action
     */
    private final String action;

    /**
     * Outcome activity context
     */
    private Object object;

    /**
     * Outcome generated result
     */
    private Object generated;

    /**
     * @param builder apply builder object properties to the OutcomeEvent object.
     */
    protected OutcomeEvent(Builder<?> builder) {
        super(builder);
        this.action = builder.action;
        this.object = builder.object;
        this.generated = builder.generated;
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
    public static abstract class Builder<T extends Builder<T>> extends CaliperEvent.Builder<T>  {
        private String action;
        private Object object;
        private Object generated;

        /**
         * Initialize type with default valueS.  Required if .builder() properties are not set by user.
         */
        public Builder() {
            context(OutcomeEvent.Identifier.CONTEXT.uri());
            type(OutcomeEvent.Identifier.TYPE.uri());
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