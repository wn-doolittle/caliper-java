package org.imsglobal.caliper.entities.reading;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.DigitalResourceType;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;

public class WebPage extends DigitalResource implements org.imsglobal.caliper.entities.schemadotorg.WebPage {

    @JsonProperty("@type")
    private final DigitalResourceType type;

    /**
     * @param builder apply builder object properties to the WebPage object.
     */
    protected WebPage(Builder<?> builder) {
        super(builder);

        EntityValidator.checkType(builder.type, DigitalResourceType.WEB_PAGE);

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
            type(DigitalResourceType.WEB_PAGE);
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
         * @return a new instance of WebPage.
         */
        public WebPage build() {
            return new WebPage(this);
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