package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.assignable.CaliperAssignableDigitalResource;
import org.imsglobal.caliper.entities.outcome.Outcome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutcomeProfile extends BaseProfile {

    public enum OutcomeAction {
        GRADED("outcome.graded");
        // POSTED("outcome.posted");
        // REPORTED("outcome.reported");

        private final String key;
        private static final Map<String, OutcomeAction> lookup = new HashMap<String, OutcomeAction>();

        /**
         * Create reverse lookup hash map
         */
        static {
            for (OutcomeAction constants : OutcomeAction.values())
                lookup.put(constants.key(), constants);
        }

        /**
         * Constructor
         * @param key
         */
        private OutcomeAction(String key) {
            this.key = key;
        }

        /**
         * @return ResourceBundle key for internationalized action strings.
         */
        public String key() {
            return key;
        }

        /**
         * @param key
         * @return true if lookup returns a key match; false otherwise.
         */
        public static boolean hasKey(String key) {
            return lookup.containsKey(key);
        }

        /**
         * @param key
         * @return enum constant by reverse lookup
         */
        public static OutcomeAction lookupConstant(String key) {
            return lookup.get(key);
        }
    }

    private CaliperAssignableDigitalResource assignable;
    private List<Outcome> outcomes = new ArrayList<Outcome>();

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected OutcomeProfile(Builder<?> builder) {
        super(builder);
        this.assignable = builder.assignable;
        this.outcomes = builder.outcomes;
    }

    /**
     * @return assignable digital resource.
     */
    public CaliperAssignableDigitalResource getAssignable() {
        return assignable;
    }

    /**
     * @return list of outcomes (paired attempt and result)
     */
    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private CaliperAssignableDigitalResource assignable;
        private List<Outcome> outcomes = new ArrayList<Outcome>();

        /**
         * @param assignable
         * @return builder.
         */
        public T assignable(CaliperAssignableDigitalResource assignable) {
            this.assignable = assignable;
            return self();
        }

        /**
         * @param outcomes
         * @return builder
         */
        public T outcomes(List<Outcome> outcomes) {
            this.outcomes = outcomes;
            return self();
        }

        /**
         * @param outcome
         * @return builder
         */
        public T outcome(Outcome outcome) {
            this.outcomes.add(outcome);
            return self();
        }

        /**
         * Client invokes the build method in order to create an immutable profile object.
         * @return a new instance of MediaProfile.
         */
        public OutcomeProfile build() {
            return new OutcomeProfile(this);
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