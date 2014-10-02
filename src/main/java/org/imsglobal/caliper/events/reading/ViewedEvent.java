package org.imsglobal.caliper.events.reading;

import org.imsglobal.caliper.actions.ReadingActions;
import org.imsglobal.caliper.events.CaliperEvent;

import java.util.ResourceBundle;

public class ViewedEvent extends CaliperEvent {

    public enum Identifier {
        CONTEXT("http://purl.imsglobal.org/ctx/caliper/v1/ViewedEvent"),
        TYPE("http://purl.imsglobal.org/caliper/v1/ViewedEvent");

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
     * Viewed action
     */
    private final String action;

    /**
     * @param builder apply builder object properties to the ViewedEvent object.
     */
    protected ViewedEvent (Builder<?> builder) {
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
         * Initialize type with default valueS.  Required if .builder() properties are not set by user.
         */
        public Builder() {
            context(ViewedEvent.Identifier.CONTEXT.uri());
            type(ViewedEvent.Identifier.TYPE.uri());
            action(ReadingActions.VIEWED.key());
        }

        /**
         * @param key
         * @return builder
         */
        @Override
        public T action(String key) {
            if (ReadingActions.hasKey(key)) {
                this.action = ResourceBundle.getBundle("actions").getString(key);
                return self();
            } else {
                throw new IllegalArgumentException("Unrecognized constant: " + key);
            }
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of ViewedEvent.
         */
        public ViewedEvent build() {
            return new ViewedEvent (this);
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