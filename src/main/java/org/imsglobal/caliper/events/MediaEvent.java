package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.media.MediaObject;
import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.validators.EventValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

@SupportedActions({
    Action.OPENED_POPOUT,
    Action.CLOSED_POPOUT,
    Action.EXITED_FULLSCREEN,
    Action.ENTERED_FULLSCREEN,
    Action.CHANGED_SIZE,
    Action.CHANGED_RESOLUTION,
    Action.STARTED,
    Action.REWOUND,
    Action.RESUMED,
    Action.FORWARDED_TO,
    Action.PAUSED,
    Action.JUMPED_TO,
    Action.ENDED,
    Action.CHANGED_SPEED,
    Action.UNMUTED,
    Action.MUTED,
    Action.CHANGED_VOLUME,
    Action.DISABLED_CLOSED_CAPTIONING,
    Action.ENABLED_CLOSED_CAPTIONING
})
public class MediaEvent extends Event {

    @JsonProperty("@context")
    private final EventContext context;

    @JsonProperty("@type")
    private final EventType type;

    @JsonProperty("action")
    private final Action action;

    @JsonIgnore
    private static final Logger log = LoggerFactory.getLogger(MediaEvent.class);

    /**
     * Utilize builder to construct MediaEvent.  Validate Media object copy rather than the
     * Media builder.  This approach protects the class against parameter changes from another
     * thread during the "window of vulnerability" between the time the parameters are checked
     * until when they are copied.
     *
     * @param builder
     */
    protected MediaEvent(Builder<?> builder) {
        super(builder);

        EventValidator.checkContext(builder.context, EventContext.MEDIA);
        EventValidator.checkType(builder.type, EventType.MEDIA);
        EventValidator.checkActorType(getActor(), Person.class);
        EventValidator.checkAction(builder.action, MediaEvent.class);
        EventValidator.checkObjectType(getObject(), MediaObject.class);

        this.context = builder.context;
        this.type = builder.type;
        this.action = builder.action;
    }

    /**
     * Required.
     * @return the context
     */
    @Override
    @Nonnull
    public EventContext getContext() {
        return context;
    }

    /**
     * Required.
     * @return the type
     */
    @Override
    @Nonnull
    public EventType getType() {
        return type;
    }

    /**
     * Required.
     * @return the action
     */
    @Override
    @Nonnull
    public Action getAction() {
        return action;
    }

    /**
     * Initialize default parameter values in the builder.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Event.Builder<T>  {
        private EventContext context;
        private EventType type;
        private Action action;

        /*
         * Constructor
         */
        public Builder() {
            context(EventContext.MEDIA);
            type(EventType.MEDIA);
        }

        /**
         * @param context
         * @return builder.
         */
        private T context(EventContext context) {
            this.context = context;
            return self();
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(EventType type) {
            this.type = type;
            return self();
        }

        /**
         * @param action
         * @return builder.
         */
        @Override
        public T action(Action action) {
            this.action = action;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable profile object.
         * @return a new MediaEvent instance.
         */
        public MediaEvent build() {
            return new MediaEvent(this);
        }
    }

    /**
     * Self-reference that permits sub-classing of builder.
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