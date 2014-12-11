package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.outcome.Result;
import org.imsglobal.caliper.profiles.OutcomeProfile;

public class OutcomeEvent extends org.imsglobal.caliper.events.Event {

    @JsonProperty("@context")
    private final String context;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("action")
    private final String action;

    @JsonProperty("object")
    private Attempt object;

    @JsonProperty("generated")
    private Result generated;

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
     * @return the attempt object.
     */
    @Override
    public Attempt getObject() {
        return object;
    }

    /**
     * @return the generated result.
     */
    @Override
    public Result getGenerated() {
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
        private Attempt object;
        private Result generated;

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
         * @return builder.
         */
        @Override
        public T action(String key) {
            try {
                this.action = OutcomeProfile.getActionFromBundle(key);
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
                this.object = OutcomeProfile.validateObject(object);
            } catch (ClassCastException e) {
                //TODO log and do something clever with exception.
            }

            return self();
        }

        /**
         * @param generated
         * @return builder.
         */
        @Override
        public T generated(Generatable generated) {
            try {
                this.generated = OutcomeProfile.validateGenerated(generated);
            } catch (ClassCastException e) {
                //TODO log and do something clever with exception.
            }

            return self();
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