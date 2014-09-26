package org.imsglobal.caliper.events.reading;

import org.imsglobal.caliper.actions.ReadingActions;
import org.imsglobal.caliper.entities.CaliperDigitalResource;
import org.imsglobal.caliper.events.CaliperEvent;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ResourceBundle;

public class NavigationEvent extends CaliperEvent {

    private final String context;
    private final String type;
    private final String action;

    /**
     * Describes the resource from which the navigation starts
     */
    @JsonProperty("navigatedFrom")
    private CaliperDigitalResource fromResource;

    /**
     * @param builder apply builder object properties to the NavigationEvent object.
     */
    protected NavigationEvent(Builder<?> builder) {
        super(builder);
        this.context = builder.context;
        this.type = builder.type;
        this.action = builder.action;
        this.fromResource = builder.fromResource;
    }

    /**
     public NavigationEvent() {
     super();

     setContext("http://purl.imsglobal.org/ctx/caliper/v1/NavigationEvent");
     setType("http://purl.imsglobal.org/caliper/v1/NavigationEvent");
     setAction("navigatedTo");
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
     * @return the fromResource
     */
    public CaliperDigitalResource getFromResource() {
        return fromResource;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperEvent.Builder<T>  {
        private static final String NAVIGATIONEVENT_CONTEXT = "http://purl.imsglobal.org/ctx/caliper/v1/NavigationEvent";
        private static final String NAVIGATIONEVENT_TYPE = "http://purl.imsglobal.org/caliper/v1/NavigationEvent";
        private String context;
        private String type;
        private String action;
        private CaliperDigitalResource fromResource;

        /**
         * Initialize type with default valueS.  Required if .builder() properties are not set by user.
         */
        public Builder() {
            context(NAVIGATIONEVENT_CONTEXT);
            type(NAVIGATIONEVENT_TYPE);
            action("reading.navigatedTo");
        }

        /**
         * TODO Perhaps we should always just auto-generate the context for the user; drop setter?
         * @param context
         * @return builder
         */
        @Override
        public T context(String context) {
            if (context.equals(NAVIGATIONEVENT_CONTEXT)) {
                this.context = context;
            } else {
                this.context = NAVIGATIONEVENT_CONTEXT;
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
            if (type.equals(NAVIGATIONEVENT_TYPE)) {
                this.type = type;
            } else {
                this.type = NAVIGATIONEVENT_TYPE;
            }
            return self();
        }

        /**
         * TODO Validation while limited to reading events does not restrict arg to "navigatedTo" only;
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
         * @param fromResource
         * @return builder
         */
        public T fromResource(CaliperDigitalResource fromResource) {
            this.fromResource = fromResource;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of NavigationEvent.
         */
        public NavigationEvent build() {
            return new NavigationEvent(this);
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