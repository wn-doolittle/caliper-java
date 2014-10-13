package org.imsglobal.caliper.entities.media;

import org.imsglobal.caliper.entities.schemadotorg.ImageObject;

/**
 * An image object embedded in a web page.
 */
public class CaliperImageObject extends CaliperMediaObject implements ImageObject {

    /**
     * @param builder apply builder object properties to the CaliperImageObject object.
     */
    protected CaliperImageObject(Builder<?> builder) {
        super(builder);
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperMediaObject.Builder<T>  {

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(CaliperMediaObject.Type.CALIPER_IMAGE_OBJECT.uri());
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of CaliperImageObject.
         */
        public CaliperImageObject build() {
            return new CaliperImageObject(this);
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