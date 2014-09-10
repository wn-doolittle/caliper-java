package org.imsglobal.caliper.entities.annotation;

public class BookmarkAnnotation extends Annotation {

    private final String type;
    private String bookmarkNotes;

    /**
     * @param builder apply builder object properties to the BookmarkAnnotation object.
     */
    protected BookmarkAnnotation(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.bookmarkNotes = builder.bookmarkNotes;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return the bookmarkNotes
     */
    public String getBookmarkNotes() {
        return bookmarkNotes;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Annotation.Builder<T>  {
        private static final String BOOKMARKANNOTATON_TYPE = "http://purl.imsglobal.org/caliper/v1/BookmarkAnnotation";
        private String type;
        private String bookmarkNotes;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(BOOKMARKANNOTATON_TYPE);
        }

        /**
         * @param type
         * @return the IMS Global type reference URI.
         */
        @Override
        public T type(String type) {
            if (type.equals(BOOKMARKANNOTATON_TYPE)) {
                this.type = type;
            } else {
                this.type = BOOKMARKANNOTATON_TYPE;
            }
            return self();
        }

        /**
         * @param bookmarkNotes
         * @return annotation bookmark notes.
         */
        public T bookmarkNotes(String bookmarkNotes) {
            this.bookmarkNotes = bookmarkNotes;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of BookmarkAnnotation.
         */
        public BookmarkAnnotation build() {
            return new BookmarkAnnotation(this);
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