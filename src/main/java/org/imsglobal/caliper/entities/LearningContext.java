package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.lis.LISOrganization;

@JsonPropertyOrder({ "edApp", "lisOrganization", "agent"})
public class LearningContext {

    @JsonProperty("edApp")
    private SoftwareApplication edApp;

    @JsonProperty("lisOrganization")
    private LISOrganization lisOrganization;

    @JsonProperty("agent")
    private Agent agent;

    /**
     * @param builder apply builder object properties to the LearningContext object.
     */
    protected LearningContext(Builder<?> builder) {
        //super(builder);
        this.edApp = builder.edApp;
        this.lisOrganization = builder.lisOrganization;
        this.agent = builder.agent;
    }

    /**
     * @return the educational app.
     */
    public SoftwareApplication getEdApp() {
        return edApp;
    }

    /**
     * @return the LIS Organization.
     */
    public LISOrganization getLisOrganization() {
        return lisOrganization;
    }

    /**
     * @return the agent.
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * Initialize default parameter values in the builder.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> {
    // public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private SoftwareApplication edApp;
        private LISOrganization lisOrganization;
        private Agent agent;

        protected abstract T self();

        /**
         * @param edApp
         * @return builder.
         */
        private T edApp(SoftwareApplication edApp) {
            this.edApp = edApp;
            return self();
        }

        /**
         * @param lisOrganization
         * @return builder.
         */
        private T lisOrganization(LISOrganization lisOrganization) {
            this.lisOrganization = lisOrganization;
            return self();
        }

        /**
         * @param agent
         * @return builder.
         */
        private T agent(Agent agent) {
            this.agent = agent;
            return self();
        }

        /**
         * Client invokes the build method in order to create an immutable LearningContext object.
         * @return the LearningContext.
         */
        public LearningContext build() {
            return new LearningContext(this);
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
