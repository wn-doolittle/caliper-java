package org.imsglobal.caliper.entities.reading;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.validators.ValidatorResult;
import org.imsglobal.caliper.validators.entities.DigitalResourceValidator;

import javax.annotation.Nonnull;

/**
 * Representation of an EPUB 3 Volume.
 * 
 * A major structural division of a piece of writing
 * http://www.idpf.org/epub/vocab/structure/#chapter
 */
public class EpubChapter extends DigitalResource {

    @JsonProperty("@type")
    private final String type;

    /**
     * @param builder apply builder object properties to the EpubChapter object.
     */
    protected EpubChapter(Builder<?> builder) {
        super(builder);
        this.type = builder.type;

        ValidatorResult result = new DigitalResourceValidator<EpubChapter>().validate(this);
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
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends DigitalResource.Builder<T>  {
        private String type;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(DigitalResource.Type.EPUB_CHAPTER.uri());
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
         * @return a new instance of EpubChapter.
         */
        public EpubChapter build() {
            return new EpubChapter(this);
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