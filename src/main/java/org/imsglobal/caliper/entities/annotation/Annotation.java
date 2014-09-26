package org.imsglobal.caliper.entities.annotation;

import org.imsglobal.caliper.entities.CaliperEntity;
import org.imsglobal.caliper.entities.schemadotorg.Thing;

/**
 * The super-class of all Annotation types.
 * 
 * Direct sub-types can include - Highlight, Attachment, etc. - all of
 * which are specified in the Caliper Annotation Metric Profile
 */
public class Annotation extends CaliperEntity implements Thing {

    private final String type;
    private Object target;
    // private Target target;

    /**
     * @param builder apply builder object properties to the Annotation object.
     */
    protected Annotation(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.target = builder.target;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }


    /**
     * @return the target
     */
    public Object getTarget() {
        return target;
    }


    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperEntity.Builder<T>  {
        private static final String ANNOTATON_TYPE = "http://purl.imsglobal.org/caliper/v1/Annotation";
        private String type;
        private Object target;
        // private Target target;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(ANNOTATON_TYPE);
        }

        /**
         * @param type
         * @return the IMS Global type reference URI.
         */
        @Override
        public T type(String type) {
            if (type.equals(ANNOTATON_TYPE)) {
                this.type = type;
            } else {
                this.type = ANNOTATON_TYPE;
            }
            return self();
        }

        /**
         * @param target
         * @return annotation target.
         */
        public T target(Object target) {
            this.target = target;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of Annotation.
         */
        public Annotation build() {
            return new Annotation(this);
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