package org.imsglobal.caliper.entities.annotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "description",
    "properties",
    "dateCreated",
    "dateModified",
    "bookmarkNotes" })
public class BookmarkAnnotation extends org.imsglobal.caliper.entities.annotation.Annotation {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("bookmarkNotes")
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
        private String type;
        private String bookmarkNotes;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(Annotation.Type.BOOKMARK_ANNOTATION.uri());
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