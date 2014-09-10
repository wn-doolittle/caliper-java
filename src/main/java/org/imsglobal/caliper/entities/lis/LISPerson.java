package org.imsglobal.caliper.entities.lis;

import org.imsglobal.caliper.entities.CaliperAgent;
import org.imsglobal.caliper.entities.CaliperEntity;

public class LISPerson extends CaliperEntity implements CaliperAgent {

    private final String type;

    /**
     * @param builder apply builder object properties to the LISPerson object.
     */
    protected LISPerson(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperEntity.Builder<T>  {
        private static final String LISPERSON_TYPE = "http://purl.imsglobal.org/caliper/v1/LISPerson";
        private String type;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(LISPERSON_TYPE);
        }

        /**
         * @param type
         * @return the IMS Global type reference URI.
         */
        @Override
        public T type(String type) {
            if (type.equals(LISPERSON_TYPE)) {
                this.type = type;
            } else {
                this.type = LISPERSON_TYPE;
            }
            return self();
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
