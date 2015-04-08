package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.validators.EventValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

@SupportedActions({
    Action.VIEWED
})
public class ViewEvent extends Event {

    @JsonProperty("@context")
    private final Context context;

    @JsonProperty("@type")
    private final Type type;

    @JsonProperty("action")
    private final Action action;

    @JsonIgnore
    private static final Logger log = LoggerFactory.getLogger(ViewEvent.class);

    /**
     * Utilize builder to construct ViewEvent.  Validate View object copy rather than the
     * View builder.  This approach protects the class against parameter changes from another
     * thread during the "window of vulnerability" between the time the parameters are checked
     * until when they are copied.
     *
     * @param builder
     */
    protected ViewEvent(Builder<?> builder) {
        super(builder);

        EventValidator.checkContextUri(builder.context, Context.VIEW);
        EventValidator.checkTypeUri(builder.type, Type.VIEW);
        EventValidator.checkAction(builder.action, ViewEvent.class);
        EventValidator.checkObjectType(getObject(), DigitalResource.class);
        EventValidator.checkTargetType(getTarget(), DigitalResource.class);

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
    public Context getContext() {
        return context;
    }

    /**
     * Required.
     * @return the type
     */
    @Override
    @Nonnull
    public Type getType() {
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
        private Context context;
        private Type type;
        private Action action;

        /*
         * Constructor
         */
        public Builder() {
            context(Context.VIEW);
            type(Type.VIEW);
            action(Action.VIEWED);
        }

        /**
         * @param context
         * @return builder.
         */
        private T context(Context context) {
            this.context = context;
            return self();
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(Type type) {
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
         * @return a new ViewEvent instance.
         */
        public ViewEvent build() {
            return new ViewEvent(this);
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