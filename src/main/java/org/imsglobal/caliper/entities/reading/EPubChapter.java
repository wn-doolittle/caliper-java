package org.imsglobal.caliper.entities.reading;

import org.imsglobal.caliper.entities.CaliperDigitalResource;
import org.imsglobal.caliper.entities.schemadotorg.CreativeWork;

/**
 * Representation of an EPUB 3 Volume.
 * 
 * A major structural division of a piece of writing
 * http://www.idpf.org/epub/vocab/structure/#chapter
 */
public class EPubChapter extends CaliperDigitalResource implements CreativeWork {

    private final String type;

    /**
     * @param builder apply builder object properties to the EPubChapter object.
     */
    protected EPubChapter(Builder<?> builder) {
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
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperDigitalResource.Builder<T>  {
        private static final String EPUBCHAPTER_TYPE = "http://www.idpf.org/epub/vocab/structure/#chapter";
        private String type;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(EPUBCHAPTER_TYPE);
        }

        /**
         * @param type
         * @return the IMS Global type reference URI.
         */
        @Override
        public T type(String type) {
            if (type.equals(EPUBCHAPTER_TYPE)) {
                this.type = type;
            } else {
                this.type = EPUBCHAPTER_TYPE;
            }
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of EPubPart.
         */
        public EPubChapter build() {
            return new EPubChapter(this);
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
