package org.imsglobal.caliper.entities.annotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.entities.EntityType;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "description",
    "extensions",
    "dateCreated",
    "dateModified",
    "annotatedId",
    "selection",
    "selectionText" })
public class HighlightAnnotation extends org.imsglobal.caliper.entities.annotation.Annotation {

    @JsonProperty("@type")
    private final EntityType type;

    @JsonProperty("selection")
    private TextPositionSelector selection;

    @JsonProperty("selectionText")
    private String selectionText;

    /**
     * @param builder apply builder object properties to the HighlightAnnotation object.
     */
    protected HighlightAnnotation(Builder<?> builder) {
        super(builder);

        EntityValidator.checkTypeUri(builder.type, EntityType.HIGHLIGHT_ANNOTATION);

        this.type = builder.type;
        this.selection = builder.selection;
        this.selectionText = builder.selectionText;
    }

    /**
     * @return the type
     */
    @Override
    @Nonnull
    public EntityType getType() {
        return type;
    }

    /**
     * @return the selection
     */
    @Nullable
    public TextPositionSelector getSelection() {
        return selection;
    }

    /**
     * @return the selectionText
     */
    @Nullable
    public String getSelectionText() {
        return selectionText;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Annotation.Builder<T>  {
        private EntityType type;
        private TextPositionSelector selection;
        private String selectionText;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(EntityType.HIGHLIGHT_ANNOTATION);
            selection = new TextPositionSelector();
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(EntityType type) {
            this.type = type;
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