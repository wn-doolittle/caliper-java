package org.imsglobal.caliper.entities.annotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import java.util.List;

public class SharedAnnotation extends org.imsglobal.caliper.entities.annotation.Annotation {

    @JsonProperty("@type")
    private final String type;

    // TODO should be a list of LISGroup or LISPersons
    @JsonProperty("withAgents")
    private List<String> withAgents = Lists.newArrayList();

    /**
     * @param builder apply builder object properties to the SharedAnnotation object.
     */
    protected SharedAnnotation(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.withAgents = builder.withAgents;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return the users
     */
    public List<String> getWithAgents() {
        return withAgents;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Annotation.Builder<T>  {
        private String type;
        private List<String> withAgents = Lists.newArrayList();

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(Annotation.Type.SHARED_ANNOTATION.uri());
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
         * @param withAgents
         * @return shared agents.
         */
        public T withAgents(List<String> withAgents) {
            this.withAgents = withAgents;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of SharedAnnotation.
         */
        public SharedAnnotation build() {
            return new SharedAnnotation(this);
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