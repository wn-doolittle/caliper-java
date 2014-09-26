package org.imsglobal.caliper.events.reading;

import org.imsglobal.caliper.actions.ReadingActions;
import org.imsglobal.caliper.events.CaliperEvent;

import java.util.ResourceBundle;

public class ViewedEvent extends CaliperEvent {

    private final String context;
    private final String type;
    private final String action;

    /**
     * @param builder apply builder object properties to the ViewedEvent object.
     */
    protected ViewedEvent (Builder<?> builder) {
        super(builder);
        this.context = builder.context;
        this.type = builder.type;
        this.action = builder.action;
    }

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
        private static final String VIEWEDEVENT_CONTEXT = "http://purl.imsglobal.org/ctx/caliper/v1/ViewedEvent";
        private static final String VIEWEDEVENT_TYPE = "http://purl.imsglobal.org/caliper/v1/ViewedEvent";
        private String context;
        private String type;
        private String action;

        /**
         * Initialize type with default valueS.  Required if .builder() properties are not set by user.
         */
        public Builder() {
            context(VIEWEDEVENT_CONTEXT);
            type(VIEWEDEVENT_TYPE);
            action("reading.viewed");
        }

        /**
         * TODO Perhaps we should always just auto-generate the context for the user; drop setter?
         * @param context
         * @return builder
         */
        @Override
        public T context(String context) {
            if (context.equals(VIEWEDEVENT_CONTEXT)) {
                this.context = context;
            } else {
                this.context = VIEWEDEVENT_CONTEXT;
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
            if (type.equals(VIEWEDEVENT_TYPE)) {
                this.type = type;
            } else {
                this.type = VIEWEDEVENT_TYPE;
            }
            return self();
        }

        /**
         * TODO Validation while limited to reading events does not restrict arg to "viewed" only;
         * @param key
         * @return builder
         */
        @Override
        public T action(String key) {
            if (ReadingActions.hasKey(key)) {
                this.action = ResourceBundle.getBundle("resources.actions").getString(key);
                return self();
            } else {
                throw new IllegalArgumentException("Unrecognized constant");
            }
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of ViewedEvent.
         */
        public ViewedEvent build() {
            return new ViewedEvent (this);
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