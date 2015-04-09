package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.assessment.AssessmentItem;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.response.Response;
import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.validators.EventValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

@SupportedActions({
    Action.STARTED,
    Action.COMPLETED,
    Action.SKIPPED,
    Action.REVIEWED,
    Action.VIEWED
})
public class AssessmentItemEvent extends Event {

    @JsonProperty("@context")
    private final EventContext context;

    @JsonProperty("@type")
    private final EventType type;

    @JsonProperty("action")
    private final Action action;

    @JsonIgnore
    private static final Logger log = LoggerFactory.getLogger(AssessmentItemEvent.class);

    /**
     * Utilize builder to construct AssessmentItemEvent.  Validate AssessmentItem object copy rather than the
     * AssessmentItem builder.  This approach protects the class against parameter changes from another
     * thread during the "window of vulnerability" between the time the parameters are checked
     * until when they are copied.
     *
     * @param builder
     */
    protected AssessmentItemEvent(Builder<?> builder) {
        super(builder);

        EventValidator.checkContextUri(builder.context, EventContext.ASSESSMENT_ITEM);
        EventValidator.checkTypeUri(builder.type, EventType.ASSESSMENT_ITEM);
        EventValidator.checkActorType(getActor(), Person.class);
        EventValidator.checkAction(builder.action, AssessmentItemEvent.class);
        EventValidator.checkObjectType(getObject(), AssessmentItem.class);

        if (builder.action == Action.COMPLETED) {
            EventValidator.checkGeneratedType(getGenerated(), Response.class);
        } else {
            EventValidator.checkGeneratedType(getGenerated(), Attempt.class);
        }

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
            context(EventContext.ASSESSMENT_ITEM);
            type(EventType.ASSESSMENT_ITEM);
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
         * @return a new AssessmentItemEvent instance.
         */
        public AssessmentItemEvent build() {
            return new AssessmentItemEvent(this);
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