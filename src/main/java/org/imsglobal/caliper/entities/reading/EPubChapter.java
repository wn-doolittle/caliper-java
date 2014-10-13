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

    /**
     * @param builder apply builder object properties to the EPubChapter object.
     */
    protected EPubChapter(Builder<?> builder) {
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
            type(CaliperDigitalResource.Type.EPUB_CHAPTER.uri());
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
