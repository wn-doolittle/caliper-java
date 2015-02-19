package org.imsglobal.caliper.entities.lis;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.Entity;

import javax.annotation.Nonnull;

public class Person extends Entity implements org.imsglobal.caliper.entities.foaf.Agent {

    @JsonProperty("@type")
    private final String type;

    /**
     * @param builder apply builder object properties to the Person object.
     */
    protected Person(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
    }

    /**
     * @return the type
     */
    @Override
    @Nonnull
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
            type(Entity.Type.LIS_PERSON.uri());
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
         * @return a new instance of the Person.
         */
        public Person build() {
            return new Person(this);
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