package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.profiles.Profile.Action;
import org.imsglobal.caliper.validators.ValidatorResult;
import org.imsglobal.caliper.validators.events.NavigationEventValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@JsonPropertyOrder({
    "@context",
    "@type",
    "actor",
    "action",
    "object",
    "target",
    "generated",
    "startedAtTime",
    "endedAtTime",
    "duration",
    "edApp",
    "group",
    "navigatedFrom" })
@SupportedActions({Action.NAVIGATED_TO})
public class NavigationEvent extends Event {
    
    @JsonProperty("@context")
    private final String context;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("action")
    private final Action action;

    @JsonProperty("navigatedFrom")
    private final DigitalResource fromResource;

    @JsonIgnore
    private static final Logger log = LoggerFactory.getLogger(NavigationEvent.class);

    /**
     * Utilize builder to construct NavigationEvent.  Validate Navigation object copy rather than the
     * Navigation builder.  This approach protects the class against parameter changes from another
     * thread during the "window of vulnerability" between the time the parameters are checked
     * until when they are copied.  Validate properties of builder copy and if conformance violations
     * are found throw an IllegalStateException (Bloch, Effective Java, 2nd ed., items 2, 39, 60, 63).
     *
     * @param builder
     */
    protected NavigationEvent(Builder<?> builder) {
        super(builder);
        this.context = builder.context;
        this.type = builder.type;
        this.action = builder.action;
        this.fromResource = builder.fromResource;

        ValidatorResult result = new NavigationEventValidator().validate(this);
        if (!result.isValid()) {
            throw new IllegalStateException(result.errorMessage().toString());
        }
    }

    /**
     * Required.
     * @return the context
     */
    @Override
    @Nonnull
    public String getContext() {
        return context;
    }

    /**
     * Required.
     * @return the type
     */
    @Override
    @Nonnull
    public String getType() {
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
     * Describes the resource from which the navigation commences.
     * @return the fromResource
     */
    @Nullable
    public DigitalResource getFromResource() {
        return fromResource;
    }

    /**
     * Initialize default parameter values in the builder.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Event.Builder<T>  {
        private String context;
        private String type;
        private Action action;
        private DigitalResource fromResource;

        /*
         * Constructor
         */
        public Builder() {
            context(Context.NAVIGATION.uri());
            type(Type.NAVIGATION.uri());
            action(Action.NAVIGATED_TO);
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
         * @param action
         * @return builder.
         */
        @Override
        public T action(Action action) {
            this.action = action;
            return self();
        }

        /**
         * @param fromResource
         * @return builder
         */
        public T fromResource(DigitalResource fromResource) {
            this.fromResource = fromResource;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable profile object.
         * @return a new NavigationEvent instance.
         */
        public NavigationEvent build() {
            return new NavigationEvent(this);
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