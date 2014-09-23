package org.imsglobal.caliper.entities.annotation;

public class HighlightAnnotation extends Annotation {

    private final String type;
    private TextPositionSelector selection;
    private String selectionText;

    /**
     * @param builder apply builder object properties to the HighlightAnnotation object.
     */
    protected HighlightAnnotation(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.selection = builder.selection;
        this.selectionText = builder.selectionText;
    }

    /**
     * Original constructor
    public HighlightAnnotation(String id) {
        super(id);
        setType("http://purl.imsglobal.org/caliper/v1/HighlightAnnotation");
        selection = new TextPositionSelector();
    }
    */

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return the selection
     */
    public TextPositionSelector getSelection() {
        return selection;
    }

    /**
     * @return the selectionText
     */
    public String getSelectionText() {
        return selectionText;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Annotation.Builder<T>  {
        private static final String HIGHLIGHTANNOTATON_TYPE = "http://purl.imsglobal.org/caliper/v1/HighlightAnnotation";
        private String type;
        private TextPositionSelector selection;
        private String selectionText;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(HIGHLIGHTANNOTATON_TYPE);
        }

        /**
         * @param type
         * @return the IMS Global type reference URI.
         */
        @Override
        public T type(String type) {
            if (type.equals(HIGHLIGHTANNOTATON_TYPE)) {
                this.type = type;
            } else {
                this.type = HIGHLIGHTANNOTATON_TYPE;
            }
            return self();
        }

        /**
         * @param start
         * @return text position selector.
         */
        public T selectionStart(String start) {
            this.selection.setStart(start);
            return self();
        }

        /**
         * @param end
         * @return text position selector.
         */
        public T selectionEnd(String end) {
            this.selection.setEnd(end);
            return self();
        }

        /**
         * TODO does the builder need to instantiate a new TextPositionSelector
         * per the original constructor or let the user do it?
         * @param selectionText
         * @return annotation selection text.
         */
        public T selectionText(String selectionText) {
            this.selectionText = selectionText;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of HighlightAnnotation.
         */
        public HighlightAnnotation build() {
            return new HighlightAnnotation(this);
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
