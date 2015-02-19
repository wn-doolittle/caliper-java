package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;

public class WebPage extends DigitalResource implements org.imsglobal.caliper.entities.schemadotorg.WebPage {

    @JsonProperty("@type")
    private final String type;

    /**
     * @param builder apply builder object properties to the WebPage object.
     */
    protected WebPage(Builder<?> builder) {
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
    public static abstract class Builder<T extends Builder<T>> extends DigitalResource.Builder<T>  {
        private String type;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(DigitalResource.Type.WEB_PAGE.uri());
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