package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "@id", "@type", "name", "description", "dateCreated", "dateModified" })
public class LearningObjective extends org.imsglobal.caliper.entities.Entity {

    @JsonProperty("@type")
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
    public static abstract class Builder<T extends Builder<T>> extends Entity.Builder<T>  {
        private String type;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(Entity.Type.LEARNING_OBJECTIVE.uri());
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