package org.imsglobal.caliper.events.annotation;

import org.imsglobal.caliper.actions.AnnotationActions;
import org.imsglobal.caliper.events.CaliperEvent;

import java.util.ResourceBundle;

public class AnnotationEvent extends CaliperEvent {

    public enum Identifier {
        CONTEXT("http://purl.imsglobal.org/ctx/caliper/v1/AnnotationEvent"),
        TYPE("http://purl.imsglobal.org/caliper/v1/AnnotationEvent");

        private final String uri;

        /**
         * Private constructor
         * @param uri
         */
        private Identifier(final String uri) {
            this.uri = uri;
        }

        /**
         * @return URI string
         */
        public String uri() {
            return uri;
        }
    }

    /**
     * Annotation action
     */
    private final String action;

    /**
     * @param builder apply builder object properties to the AnnotationEvent object.
     */
    protected AnnotationEvent(Builder<?> builder) {
        super(builder);
        this.action = builder.action;
    }

    /**
     * @return the action
     */
    @Override
    public String getAction() {
        return action;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperEvent.Builder<T>  {
        private String action;

        /**
         * Initialize with default values.
         */
        public Builder() {
            context(AnnotationEvent.Identifier.CONTEXT.uri());
            type(AnnotationEvent.Identifier.TYPE.uri());
        }

        /**
         * @param key
         * @return builder
         */
        @Override
        public T action(String key) {
            if (AnnotationActions.hasKey(key)) {
                this.action = ResourceBundle.getBundle("actions").getString(key);
                return self();
            } else {
                throw new IllegalArgumentException("Unrecognized constant: " + key);
            }
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of AnnotationEvent.
         */
        public AnnotationEvent build() {
            return new AnnotationEvent(this);
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