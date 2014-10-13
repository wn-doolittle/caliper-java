package org.imsglobal.caliper.entities.lis;

import org.imsglobal.caliper.entities.CaliperAgent;

public class LISPerson extends CaliperAgent {

    /**
     * @param builder apply builder object properties to the LISPerson object.
     */
    protected LISPerson(Builder<?> builder) {
        super(builder);
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperAgent.Builder<T>  {

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(CaliperAgent.Type.LIS_PERSON.uri());
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the LISPerson.
         */
        public LISPerson build() {
            return new LISPerson(this);
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