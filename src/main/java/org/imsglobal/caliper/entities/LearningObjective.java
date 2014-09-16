package org.imsglobal.caliper.entities;

public class LearningObjective extends CaliperEntity {

    private final String type;

    /**
     * @param builder apply builder object properties to the Target object.
     */
    protected LearningObjective(Builder<?> builder) {
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
        private final String LEARNOBJECTIVE_TYPE = "http://purl.imsglobal.org/caliper/v1/LearningObjective";
        private String type;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(LEARNOBJECTIVE_TYPE);
        }

        /**
         * @param type
         * @return builder
         */
        @Override
        public T type(String type) {
            if (type.equals(LEARNOBJECTIVE_TYPE)) {
                this.type = type;
            } else {
                this.type = LEARNOBJECTIVE_TYPE;
            }
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the AssessmentProfile.
         */
        public LearningObjective build() {
            return new LearningObjective(this);
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
