package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.assignable.CaliperAssignableDigitalResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssignableProfile extends BaseProfile {

    public enum AssignableAction {
        ABANDONED("assignable.abandoned"),
        ACTIVATED("assignable.activated"),
        COMPLETED("assignable.completed"),
        DEACTIVATED("assignable.deactivated"),
        HID("assignable.hid"),
        REVIEWED("assignable.reviewed"),
        SHOWED("assignable.showed"),
        STARTED("assignable.started"),
        SUBMITTED("assignable.submitted");

        private final String key;
        private static final Map<String, AssignableAction> lookup = new HashMap<String, AssignableAction>();

        /**
         * Create reverse lookup hash map
         */
        static {
            for (AssignableAction constants : AssignableAction.values())
                lookup.put(constants.key(), constants);
        }

        /**
         * Constructor
         * @param key
         */
        private AssignableAction(String key) {
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
        public static AssignableAction lookupConstant(String key) {
            return lookup.get(key);
        }
    }

    private CaliperAssignableDigitalResource assignable;
    private List<Attempt> attempts = new ArrayList<Attempt>();

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected AssignableProfile(Builder<?> builder) {
        super(builder);
        this.assignable = builder.assignable;
        this.attempts = builder.attempts;
    }

    /**
     * @return assignable digital resource.
     */
    public CaliperAssignableDigitalResource getAssignable() {
        return assignable;
    }

    /**
     * @return attempts
     */
    public List<Attempt> getAttempts() {
        return attempts;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private CaliperAssignableDigitalResource assignable;
        private List<Attempt> attempts = new ArrayList<Attempt>();

        /**
         * @param assignable
         * @return builder.
         */
        public T assignable(CaliperAssignableDigitalResource assignable) {
            this.assignable = assignable;
            return self();
        }

        /**
         * @param attempts
         * @return builder
         */
        public T attempts(List<Attempt> attempts) {
            this.attempts = attempts;
            return self();
        }

        /**
         * @param attempt
         * @return builder.
         */
        public T attempt(Attempt attempt) {
            this.attempts.add(attempt);
            return self();
        }

        /**
         * Client invokes the build method in order to create an immutable profile object.
         * @return a new instance of MediaProfile.
         */
        public AssignableProfile build() {
            return new AssignableProfile(this);
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