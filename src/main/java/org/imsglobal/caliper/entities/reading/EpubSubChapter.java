package org.imsglobal.caliper.entities.reading;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.DigitalResourceType;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;

/**
 * Representation of an EPUB 3 Volume
 * 
 * A major sub-division of a chapter
 * http://www.idpf.org/epub/vocab/structure/#subchapter
 */
public class EpubSubChapter extends DigitalResource {

    @JsonProperty("@type")
    private final DigitalResourceType type;

    /**
     * @param builder apply builder object properties to the EpubSubChapter object.
     */
    protected EpubSubChapter(Builder<?> builder) {
        super(builder);

        EntityValidator.checkTypeUri(builder.type, DigitalResourceType.EPUB_SUB_CHAPTER);

        this.type = builder.type;
    }

    /**
     * @return the type
     */
    @Override
    @Nonnull
    public DigitalResourceType getType() {
        return type;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends DigitalResource.Builder<T>  {
        private DigitalResourceType type;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(DigitalResourceType.EPUB_SUB_CHAPTER);
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(DigitalResourceType type) {
            this.type = type;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of EpubSubChapter.
         */
        public EpubSubChapter build() {
            return new EpubSubChapter(this);
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