package org.imsglobal.caliper.entities.reading;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.validators.ValidatorResult;
import org.imsglobal.caliper.validators.entities.DigitalResourceValidator;

import javax.annotation.Nonnull;

@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "description",
    "objectType",
    "alignedLearningObjective",
    "keywords",
    "isPartOf",
    "properties",
    "dateCreated",
    "dateModified",
    "datePublished",
    "index" })
public class Frame extends DigitalResource implements Targetable {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("index")
    private int index;

    /**
     * @param builder apply builder object properties to the Frame object.
     */
    protected Frame(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.index = builder.index;

        ValidatorResult result = new DigitalResourceValidator<Frame>().validate(this);
        if (!result.isValid()) {
            throw new IllegalStateException(result.errorMessage().toString());
        }
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
     * @return numeric index of the location relative to sibling locations in the content
     */
    @Nonnull
    public int getIndex() {
        return index;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends DigitalResource.Builder<T>  {
        private String type;
        private int index;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(DigitalResource.Type.FRAME.uri());
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
         * @param index
         * @return builder.
         */
        public T index(int index) {
            this.index = index;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of Frame.
         */
        public Frame build() {
            return new Frame(this);
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

