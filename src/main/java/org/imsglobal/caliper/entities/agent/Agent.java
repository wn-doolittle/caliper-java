package org.imsglobal.caliper.entities.agent;

import org.imsglobal.caliper.entities.AbstractEntity;
import org.imsglobal.caliper.entities.EntityType;

public class Agent extends AbstractEntity implements CaliperAgent {

    /**
     * @param builder apply builder object properties to the object.
     */
    protected Agent(Builder<?> builder) {
        super(builder);
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> extends AbstractEntity.Builder<T> {

        /**
         * Constructor
         */
        public Builder() {
            super.type(EntityType.AGENT);
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the Agent.
         */
        public Agent build() {
            return new Agent(this);
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