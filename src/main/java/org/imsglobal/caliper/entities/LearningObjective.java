package org.imsglobal.caliper.entities;

public class LearningObjective extends CaliperEntity {

    /**
     * @param builder apply builder object properties to the Target object.
     */
    protected LearningObjective(Builder<?> builder) {
        super(builder);
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperEntity.Builder<T>  {

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(CaliperEntity.Type.LEARNING_OBJECTIVE.uri());
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
