package org.imsglobal.caliper.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Represents response to a multiple choice question that limits options to either 'true or false',
 * 'agree or disagree', etc.
 */
@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "description",
    "properties",
    "dateCreated",
    "dateModified",
    "assignable",
    "actor",
    "attempt",
    "value",
    "startedAtTime",
    "endedAtTime",
    "duration" })
public class TrueFalseResponse extends Response {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("value")
    private final String value;

    /**
     * @param builder apply builder object properties to the Response object.
     */
    protected TrueFalseResponse(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.value = builder.value;
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
     * @return response value
     */
    @Nullable
    public String getValue() {
        return value;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Response.Builder<T>  {
        private String type;
        private String value;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(Type.TRUEFALSE.uri());
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
         * @param value
         * @return builder.
         */
        public T value(String value) {
            this.value = value;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of TrueFalseResponse.
         */
        public TrueFalseResponse build() {
            return new TrueFalseResponse(this);
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