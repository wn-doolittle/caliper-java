package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.assignable.CaliperAssignableDigitalResource;
import org.imsglobal.caliper.entities.outcome.Result;

import java.util.HashMap;
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
    private Result result;

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected OutcomeProfile(Builder<?> builder) {
        super(builder);
        this.assignable = builder.assignable;
        this.result = builder.result;
    }

    /**
     * @return assignable digital resource.
     */
    public CaliperAssignableDigitalResource getAssignable() {
        return assignable;
    }

    /**
     * @return result.
     */
    public Result getResult() {
        return result;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private CaliperAssignableDigitalResource assignable;
        private Result result;

        /**
         * @param assignable
         * @return builder.
         */
        public T assignable(CaliperAssignableDigitalResource assignable) {
            this.assignable = assignable;
            return self();
        }

        /**
         * @param result
         * @return builder.
         */
        public T result(Result result) {
            this.result = result;
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