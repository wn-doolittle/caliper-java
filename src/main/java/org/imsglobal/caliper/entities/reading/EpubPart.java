package org.imsglobal.caliper.entities.reading;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.EntityType;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;

/**
 * Representation of an EPUB 3 Volume
 * 
 * A major structural division of a piece of writing, typically
 * encapsulating a set of related chapters.
 * http://www.idpf.org/epub/vocab/structure/#part
 */
public class EpubPart extends DigitalResource {

    @JsonProperty("@type")
    private final EntityType type;

    /**
     * @param builder apply builder object properties to the EpubPart object.
     */
    protected EpubPart(Builder<?> builder) {
        super(builder);

        EntityValidator.checkTypeUri(builder.type, EntityType.EPUB_PART);

        this.type = builder.type;
    }

    /**
     * @return the type
     */
    @Override
    @Nonnull
    public EntityType getType() {
        return type;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends DigitalResource.Builder<T>  {
        private EntityType type;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(EntityType.EPUB_PART);
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(EntityType type) {
            this.type = type;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of EpubPart.
         */
        public EpubPart build() {
            return new EpubPart(this);
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