package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.assessment.Assessment;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.profiles.AssessmentProfile;

public class AssessmentEvent extends org.imsglobal.caliper.events.Event {

    @JsonProperty("@context")
    private final String context;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("action")
    private final String action;

    @JsonProperty("object")
    private final Assessment object;

    @JsonProperty("generated")
    private final Attempt generated;

    /**
     * @param builder apply builder object properties to the AssessmentEvent object.
     */
    protected AssessmentEvent(Builder<?> builder) {
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
     * @return assessment object.
     */
    @Override
    public Assessment getObject() {
        return object;
    }

    /**
     * @return generated attempt
     */
    @Override
    public Attempt getGenerated() {
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
        private Assessment object;
        private Attempt generated;

        /**
         * Initialize with default values.
         */
        public Builder() {
            context(Event.Context.ASSESSMENT.uri());
            type(Event.Type.ASSESSMENT.uri());
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
                this.action = AssessmentProfile.getActionFromBundle(key);
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
                this.object = AssessmentProfile.validateObject(object);
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
        public T generated(Object generated) {
            try {
                this.generated = AssessmentProfile.validateGenerated(generated);
            } catch (ClassCastException e) {
                //TODO log and do something clever with exception.
            }

            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of AssessmentEvent.
         */
        public AssessmentEvent build() {
            return new AssessmentEvent(this);
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