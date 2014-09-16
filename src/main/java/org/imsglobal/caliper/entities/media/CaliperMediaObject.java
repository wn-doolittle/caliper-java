package org.imsglobal.caliper.entities.media;

import org.imsglobal.caliper.entities.CaliperDigitalResource;

/**
 * An image, video, or audio object embedded in a web page.
 */
public abstract class CaliperMediaObject extends CaliperDigitalResource {

    private final String type;

    /**
     * @param builder apply builder object properties to the CaliperMediaObject object.
     */
    protected CaliperMediaObject(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).  Given the abstract nature
     * of BaseProfile, the builder's .build() method is omitted.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperDigitalResource.Builder<T> {
        private static final String MEDIAOBJECT_TYPE = "http://purl.imsglobal.org/caliper/v1/CaliperMediaObject";
        private String type;

        protected abstract T self();

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(MEDIAOBJECT_TYPE);
        }

        /**
         * @param type
         * @return builder
         */
        @Override
        public T type(String type) {
            if (type.equals(MEDIAOBJECT_TYPE)) {
                this.type = type;
            } else {
                this.type = MEDIAOBJECT_TYPE;
            }
            return self();
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
}
