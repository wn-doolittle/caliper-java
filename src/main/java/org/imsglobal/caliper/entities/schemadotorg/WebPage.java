package org.imsglobal.caliper.entities.schemadotorg;

import org.imsglobal.caliper.entities.CaliperDigitalResource;

public class WebPage extends CaliperDigitalResource implements CreativeWork {

    /**
     * @param builder apply builder object properties to the WebPage object.
     */
    protected WebPage(Builder<?> builder) {
        super(builder);
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperDigitalResource.Builder<T>  {

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(CaliperDigitalResource.Type.WEB_PAGE.uri());
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