package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.profiles.MediaProfile;
import org.imsglobal.caliper.profiles.ProfileUtils;
import org.imsglobal.caliper.validators.EventValidator;
import org.imsglobal.caliper.validators.ValidatorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

public class MediaEvent extends Event {

    @JsonProperty("@context")
    private final String context;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("action")
    private final String action;

    @JsonIgnore
    private static final Logger log = LoggerFactory.getLogger(MediaEvent.class);

    /**
     * Utilize builder to construct MediaEvent.  Validate Media object copy rather than the
     * Media builder.  This approach protects the class against parameter changes from another
     * thread during the "window of vulnerability" between the time the parameters are checked
     * until when they are copied.  Validate properties of builder copy and if conformance violations
     * are found throw an IllegalStateException (Bloch, Effective Java, 2nd ed., items 2, 39, 60, 63).
     *
     * @param builder
     */
    protected MediaEvent(Builder<?> builder) {
        super(builder);
        this.context = builder.context;
        this.type = builder.type;
        this.action = builder.action;

        ValidatorResult result = MediaProfile.validateEvent(this);
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
    public String getAction() {
        return action;
    }

    /**
     * Initialize default parameter values in the builder.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Event.Builder<T>  {
        private String event = "MediaEvent";
        private String context;
        private String type;
        private String action;

        /*
         * Constructor
         */
        public Builder() {
            context(Context.MEDIA.uri());
            type(Type.MEDIA.uri());
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
            if (MediaProfile.Actions.hasKey(key)) {
                this.action = ProfileUtils.getLocalizedAction(key);
            } else {
                throw new IllegalArgumentException(event + EventValidator.Conformance.ACTION_UNRECOGNIZED.violation());
            }
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