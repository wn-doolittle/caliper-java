package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.CaliperDigitalResource;

public class ReadingProfile extends BaseProfile {

    private CaliperDigitalResource reading;

    /**
     * Constructor
     * @param builder apply builder object properties to the profile object.
     */
    protected ReadingProfile(Builder<?> builder) {
        super(builder);
        this.reading = builder.reading;
    }

    /**
     * @return reading
     */
    public CaliperDigitalResource getReading () {
        return reading;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {

        private CaliperDigitalResource reading;

        /**
         * @param reading
         * @return builder
         */
        public T reading(CaliperDigitalResource reading) {
            this.reading = reading;
            return self();
        }

        /**
         * Client invokes the build method in order to create an immutable profile object.
         * @return a new instance of MediaProfile.
         */
        public ReadingProfile build() {
            return new ReadingProfile(this);
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