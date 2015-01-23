package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.profiles.Profile;
import org.imsglobal.caliper.profiles.SessionProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;

public class SessionEvent extends org.imsglobal.caliper.events.Event {

    @JsonProperty("@context")
    private final String context;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("action")
    private final String action;

    @JsonIgnore
    private static final Logger log = LoggerFactory.getLogger(SessionEvent.class);

    /**
     * Utilize builder to construct SessionEvent.  Validate Session object copy rather than the
     * Session builder.  This approach protects the class against parameter changes from another
     * thread during the "window of vulnerability" between the time the parameters are checked
     * until when they are copied.  Validate properties of builder copy and if conformance violations
     * are found throw an IllegalStateException (Bloch, Effective Java, 2nd ed., items 2, 39, 60, 63).
     *
     * @param builder
     */
    protected SessionEvent(Builder<?> builder) {
        super(builder);
        this.context = builder.context;
        this.type = builder.type;
        this.action = builder.action;

        try {
            SessionProfile.validateEvent(this);
        } catch (IllegalArgumentException ex) {
            log.error(ex.getMessage());
        } catch (IllegalStateException ex) {
            log.error(ex.getMessage());
        }
    }

    /**
     * @return the context
     */
    @Override
    @NotNull
    public String getContext() {
        return context;
    }

    /**
     * @return the type
     */
    @Override
    @NotNull
    public String getType() {
        return type;
    }

    /**
     * @return the action
     */
    @Override
    @NotNull
    public String getAction() {
        return action;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.  Will throw an
     * IllegalArgumentException if supplied action is not keyed to a localized action string.
     *
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Event.Builder<T> {
        private String context;
        private String type;
        private String action;

        /**
         * Initialize type with default values.
         */
        public Builder() {
            context(Event.Context.SESSION.uri());
            type(Event.Type.SESSION.uri());
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
            if (SessionProfile.Actions.hasKey(key)) {
                this.action = SessionProfile.getLocalizedAction(key);
            } else {
                throw new IllegalArgumentException(Profile.Conformance.ACTION_UNRECOGNIZED.violation());
            }
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         *
         * @return a new instance of ViewEvent.
         */
        public SessionEvent build() {
            return new SessionEvent(this);
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
     *
     * @return a new instance of the builder.
     */
    public static Builder<?> builder() {
        return new Builder2();
    }
}