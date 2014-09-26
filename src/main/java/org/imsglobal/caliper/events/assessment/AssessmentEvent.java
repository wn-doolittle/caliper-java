package org.imsglobal.caliper.events.assessment;

import org.imsglobal.caliper.actions.AssessmentActions;
import org.imsglobal.caliper.events.CaliperEvent;

import java.util.ResourceBundle;

public class AssessmentEvent extends CaliperEvent {

    private final String context;
    private final String type;
    private final String action;

    /**
     * @param builder apply builder object properties to the AssessmentEvent object.
     */
    protected AssessmentEvent(Builder<?> builder) {
        super(builder);
        this.context = builder.context;
        this.type = builder.type;
        this.action = builder.action;
    }

    /**
     public AssessmentEvent() {
     super();

     setContext("http://purl.imsglobal.org/ctx/caliper/v1/AssessmentEvent");
     setType("http://purl.imsglobal.org/caliper/v1/AssessmentEvent");
     }

     public static AssessmentEvent forAction(Action action) {
     AssessmentEvent event = new AssessmentEvent();
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
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperEvent.Builder<T>  {
        private static final String ASSESSMENTEVENT_CONTEXT = "http://purl.imsglobal.org/ctx/caliper/v1/AssessmentEvent";
        private static final String ASSESSMENTEVENT_TYPE = "http://purl.imsglobal.org/caliper/v1/AssessmentEvent";
        private String context;
        private String type;
        private String action;

        /**
         * Initialize type with default valueS.  Required if .builder() properties are not set by user.
         */
        public Builder() {
            context(ASSESSMENTEVENT_CONTEXT);
            type(ASSESSMENTEVENT_TYPE);
        }

        /**
         * TODO Perhaps we should always just auto-generate the context for the user; drop setter?
         * @param context
         * @return builder
         */
        @Override
        public T context(String context) {
            if (context.equals(ASSESSMENTEVENT_CONTEXT)) {
                this.context = context;
            } else {
                this.context = ASSESSMENTEVENT_CONTEXT;
            }
            return self();
        }

        /**
         * TODO Perhaps we should always just auto-generate the context for the user; drop setter?
         * @param type
         * @return builder
         */
        @Override
        public T type(String type) {
            if (type.equals(ASSESSMENTEVENT_TYPE)) {
                this.type = type;
            } else {
                this.type = ASSESSMENTEVENT_TYPE;
            }
            return self();
        }

        /**
         * @param key
         * @return builder
         */
        @Override
        public T action(String key) {
            if (AssessmentActions.hasKey(key)) {
                this.action = ResourceBundle.getBundle("resources.actions").getString(key);
                return self();
            } else {
                throw new IllegalArgumentException("Unrecognized constant");
            }
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