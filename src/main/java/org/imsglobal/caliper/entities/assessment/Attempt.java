package org.imsglobal.caliper.entities.assessment;

import org.imsglobal.caliper.entities.CaliperAgent;
import org.imsglobal.caliper.entities.CaliperEntity;
import org.imsglobal.caliper.entities.assignable.Assignable;

/**
 * Representation of an Attempt. Attempts are generated as part of or
 * are the object of an interaction represented by an AssignableEvent
 */
public class Attempt extends CaliperEntity {

    private final String type;
    private Assignable assignable;
    private CaliperAgent actor;
    private int count;

    /**
     * @param builder apply builder object properties to the Attempt object.
     */
    protected Attempt(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.assignable = builder.assignable;
        this.actor = builder.actor;
        this.count = builder.count;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return the assignable
     */
    public Assignable getAssignable() {
        return assignable;
    }

    /**
     * @return the actor
     */
    public CaliperAgent getActor() {
        return actor;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperEntity.Builder<T>  {
        private static final String ATTEMPT_TYPE = "http://purl.imsglobal.org/caliper/v1/Attempt";
        private String type;
        private Assignable assignable;
        private CaliperAgent actor;
        private int count;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(ATTEMPT_TYPE);
        }

        /**
         * @param type
         * @return builder
         */
        @Override
        public T type(String type) {
            if (type.equals(ATTEMPT_TYPE)) {
                this.type = type;
            } else {
                this.type = ATTEMPT_TYPE;
            }
            return self();
        }

        /**
         * @param assignable
         * @return builder
         */
        public T assignable(Assignable assignable) {
            this.assignable = assignable;
            return self();
        }

        /**
         * @param actor
         * @return builder
         */
        public T actor(CaliperAgent actor) {
            this.actor = actor;
            return self();
        }

        /**
         * @param count
         * @return builder
         */
        public T count(int count) {
            this.count = count;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of Attempt.
         */
        public Attempt build() {
            return new Attempt(this);
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