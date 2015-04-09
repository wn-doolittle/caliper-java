package org.imsglobal.caliper.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 *  Represents a response that identifies text from a presented paragraph or list.
 *  The response is the identified string or a mapping to a logical identifier;
 */
@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "description",
    "extensions",
    "dateCreated",
    "dateModified",
    "assignable",
    "actor",
    "attempt",
    "values",
    "startedAtTime",
    "endedAtTime",
    "duration" })
public class SelectTextResponse extends Response {

    @JsonProperty("@type")
    private final ResponseType type;

    @JsonProperty("values")
    private ImmutableList<String> values;

    /**
     * @param builder apply builder object properties to the Response object.
     */
    protected SelectTextResponse(Builder<?> builder) {
        super(builder);

        EntityValidator.checkTypeUri(builder.type, ResponseType.SELECTTEXT);

        this.type = builder.type;
        this.values = ImmutableList.copyOf(builder.values);
    }

    /**
     * @return the type
     */
    @Override
    @Nonnull
    public ResponseType getType() {
        return type;
    }

    /**
     * @return response values
     */
    @Nullable
    public List<String> getValues() {
        return values;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Response.Builder<T>  {
        private ResponseType type;
        private List<String> values = Lists.newArrayList();

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(ResponseType.SELECTTEXT);
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(ResponseType type) {
            this.type = type;
            return self();
        }

        /**
         * @param values
         * @return builder.
         */
        public T values(List<String> values) {
            this.values = values;
            return self();
        }

        /**
         * @param value
         * @return builder.
         */
        public T value(String value) {
            this.values.add(value);
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of SelectTextResponse.
         */
        public SelectTextResponse build() {
            return new SelectTextResponse(this);
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