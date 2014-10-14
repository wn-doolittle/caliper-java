package org.imsglobal.caliper.entities.assessment;

import org.imsglobal.caliper.entities.assignable.CaliperAssignableDigitalResource;
import org.imsglobal.caliper.entities.qti.Assessment;

/**
 * Caliper representation of an Assessment.
 * 
 * Part of the Assessment Metric Profile
 */
public class CaliperAssessment extends CaliperAssignableDigitalResource implements Assessment {

    private final String type;

    /**
     * @param builder apply builder object properties to the CaliperAssessment object.
     */
    protected CaliperAssessment(Builder<?> builder) {
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
    public static abstract class Builder<T extends Builder<T>> extends CaliperAssignableDigitalResource.Builder<T>  {
        private String type;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(CaliperAssignableDigitalResource.Type.ASSESSMENT.uri());
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(String type) {
            this.type = type;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of CaliperAssessment.
         */
        public CaliperAssessment build() {
            return new CaliperAssessment(this);
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