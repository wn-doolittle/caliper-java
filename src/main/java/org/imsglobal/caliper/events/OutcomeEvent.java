package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.lis.Organization;
import org.imsglobal.caliper.entities.outcome.Result;
import org.imsglobal.caliper.entities.schemadotorg.SoftwareApplication;
import org.imsglobal.caliper.json.DateTimeSerializer;
import org.imsglobal.caliper.profiles.OutcomeProfile;
import org.imsglobal.caliper.profiles.ProfileUtils;
import org.imsglobal.caliper.validators.EventValidator.Conformance;
import org.imsglobal.caliper.validators.ValidatorResult;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    "group" })
public class OutcomeEvent implements Event {

    @JsonProperty("@context")
    private final String context;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("edApp")
    private final SoftwareApplication edApp;

    @JsonProperty("group")
    private final Organization lisOrganization;

    @JsonProperty("actor")
    private final Agent actor;

    @JsonProperty("action")
    private final String action;

    @JsonProperty("object")
    private final Attempt object;

    @JsonProperty("target")
    private final Targetable target;

    @JsonProperty("generated")
    private final Result generated;

    @JsonProperty("startedAtTime")
    private final DateTime startedAtTime;

    @JsonProperty("endedAtTime")
    private final DateTime endedAtTime;

    @JsonProperty("duration")
    private final String duration;

    @JsonIgnore
    private static final Logger log = LoggerFactory.getLogger(OutcomeEvent.class);

    /**
     * Utilize builder to construct OutcomeEvent.  Validate Outcome object copy rather than the
     * Outcome builder.  This approach protects the class against parameter changes from another
     * thread during the "window of vulnerability" between the time the parameters are checked
     * until when they are copied.  Validate properties of builder copy and if conformance violations
     * are found throw an IllegalStateException (Bloch, Effective Java, 2nd ed., items 2, 39, 60, 63).
     *
     * @param builder
     */
    protected OutcomeEvent(Builder builder) {
        this.context = builder.context;
        this.type = builder.type;
        this.edApp = builder.edApp;
        this.lisOrganization = builder.lisOrganization;
        this.actor = builder.actor;
        this.action = builder.action;
        this.object = builder.object;
        this.target = builder.target;
        this.generated = builder.generated;
        this.startedAtTime = builder.startedAtTime;
        this.endedAtTime = builder.endedAtTime;
        this.duration = builder.duration;

        ValidatorResult result = OutcomeProfile.validateEvent(this);
        if (!result.isValid()) {
            throw new IllegalStateException(result.errorMessage().toString());
        }
    }

    /**
     * Required.
     * @return the context
     */
    public String getContext() {
        return context;
    }

    /**
     * Required.
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Optional.
     * @return the edApp
     */
    public SoftwareApplication getEdApp() {
        return edApp;
    }

    /**
     * Optional.
     * @return the lisOrganization
     */
    public Organization getLisOrganization() {
        return lisOrganization;
    }

    /**
     * Required.
     * @return the actor
     */
    @Override
    public Agent getActor() {
        return actor;
    }

    /**
     * Required.
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * Required.  Override with a covariant return type (Attempt).
     * @return the object
     */
    @Override
    public Attempt getObject() {
        return object;
    }

    /**
     * Optional.
     * @return the target
     */
    public Targetable getTarget() {
        return target;
    }

    /**
     * Required.  Override with a covariant return type (Result).
     * @return generated
     */
    @Override
    public Result getGenerated() {
        return generated;
    }

    /**
     * Required.
     * @return the startedAt time
     */
    @JsonSerialize(using = DateTimeSerializer.class)
    public DateTime getStartedAtTime() {
        return startedAtTime;
    }

    /**
     * Optional.
     * @return endedAt time
     */
    @JsonSerialize(using = DateTimeSerializer.class)
    public DateTime getEndedAtTime() {
        return endedAtTime;
    }

    /**
     * Optional. An xsd:duration (http://books.xmlschemata.org/relaxng/ch19-77073.html)
     * The format is expected to be PnYnMnDTnHnMnS
     * Valid values include PT1004199059S, PT130S, PT2M10S, P1DT2S, -P1Y, or P1Y2M3DT5H20M30.123S.
     * The following values are invalid: 1Y (leading P is missing), P1S (T separator is missing),
     * P-1Y (all parts must be positive), P1M2Y (parts order is significant and Y must precede M),
     * or P1Y-1M (all parts must be positive).
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     */
    public static class Builder {
        private final String event = "OutcomeEvent ";
        private String context;
        private String type;
        private SoftwareApplication edApp;
        private Organization lisOrganization;
        private Agent actor;
        private String action;
        private Attempt object;
        private Targetable target;
        private Result generated;
        private DateTime startedAtTime;
        private DateTime endedAtTime;
        private String duration;

        /**
         * Initialize type with default values.
         */
        private Builder() {
            context(Event.Context.OUTCOME.uri());
            type(Event.Type.OUTCOME.uri());
        }

        /**
         * @param context
         * @return builder.
         */
        private Builder context(String context) {
            this.context = context;
            return this;
        }

        /**
         * @param type
         * @return builder.
         */
        private Builder type(String type) {
            this.type = type;
            return this;
        }

        /**
         * @param edApp
         * @return builder.
         */
        public Builder edApp(SoftwareApplication edApp) {
            this.edApp = edApp;
            return this;
        }

        /**
         * @param lisOrganization
         * @return builder.
         */
        public Builder lisOrganization(Organization lisOrganization) {
            this.lisOrganization = lisOrganization;
            return this;
        }

        /**
         * @param actor
         * @return builder.
         */
        public Builder actor(Agent actor) {
            this.actor = actor;
            return this;
        }

        /**
         * @param actionKey
         * @return builder.
         */
        public Builder action(String actionKey) {
            if (OutcomeProfile.Actions.hasKey(actionKey)) {
                this.action = ProfileUtils.getLocalizedAction(actionKey);
            } else {
                throw new IllegalArgumentException(event + Conformance.ACTION_UNRECOGNIZED.violation());
            }
            return this;
        }

        /**
         * @param object
         * @return builder.
         */
        public Builder object(Attempt object) {
            this.object = object;
            return this;
        }

        /**
         * @param target
         * @return builder.
         */
        public Builder target(Targetable target) {
            this.target = target;
            return this;
        }

        /**
         * @param generated
         * @return builder.
         */
        public Builder generated(Result generated) {
            this.generated = generated;
            return this;
        }

        /**
         * @param startedAtTime
         * @return builder.
         */
        public Builder startedAtTime(DateTime startedAtTime) {
            this.startedAtTime = startedAtTime;
            return this;
        }

        /**
         * @param endedAtTime
         * @return builder.
         */
        public Builder endedAtTime(DateTime endedAtTime) {
            this.endedAtTime = endedAtTime;
            return this;
        }

        /**
         * @param duration
         * @return builder.
         */
        public Builder duration(String duration) {
            this.duration = duration;
            return this;
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of OutcomeEvent.
         */
        public OutcomeEvent build() {
            return new OutcomeEvent(this);
        }
    }

    /**
     * Static factory method.
     * @return a new instance of the builder.
     */
    public static Builder builder() {
        return new Builder();
    }
}