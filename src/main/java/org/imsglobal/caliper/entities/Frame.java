package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Frame extends CaliperDigitalResource {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("index")
    private int index;

    /**
     * @param builder apply builder object properties to the ActivityContext object.
     */
    protected Frame(Builder<?> builder) {
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
     * @return numeric index of the location relative to sibling locations in the content
     */
    public int getIndex() {
        return index;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperDigitalResource.Builder<T>  {
        private String type;
        private int index;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(CaliperEntity.Type.FRAME.uri());
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
         * @param index
         * @return builder.
         */
        public T index(int index) {
            this.index = index;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of ActivityContext.
         */
        public Frame build() {
            return new Frame(this);
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