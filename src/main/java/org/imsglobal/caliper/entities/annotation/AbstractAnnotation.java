package org.imsglobal.caliper.entities.annotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.AbstractEntity;
import org.imsglobal.caliper.entities.EntityType;
import org.imsglobal.caliper.entities.agent.CaliperAgent;
import org.imsglobal.caliper.entities.resource.CaliperDigitalResource;

import javax.annotation.Nonnull;

public abstract class AbstractAnnotation extends AbstractEntity implements CaliperAnnotation {

    @JsonProperty("annotated")
    private CaliperDigitalResource annotated;

    @JsonProperty("annotator")
    private final CaliperAgent annotator;

    /**
     * @param builder apply builder object properties to the object.
     */
    protected AbstractAnnotation(Builder<?> builder) {
        super(builder);

        this.annotated = builder.annotated;
        this.annotator = builder.annotator;
    }

    /**
     * @return the annotated object's identifier
     */
    @Nonnull
    public CaliperDigitalResource getAnnotated() {
        return annotated;
    }

    /**
     * @return the Annotator
     */
    @Nonnull
    public CaliperAgent getAnnotator() {
        return annotator;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> extends AbstractEntity.Builder<T> {
        private CaliperDigitalResource annotated;
        private CaliperAgent annotator;

        /**
         * Constructor
         */
        public Builder() {
            super.type(EntityType.ANNOTATION);
        }

        /**
         * @param annotated
         * @return builder.
         */
        public T annotated(CaliperDigitalResource annotated) {
            this.annotated = annotated;
            return self();
        }

        /**
         * @param annotator
         * @return builder.
         */
        public T annotator(CaliperAgent annotator) {
            this.annotator = annotator;
            return self();
        }
    }

    /**
     *
     */
    private static class Builder2 extends AbstractAnnotation.Builder<Builder2> {
        @Override
        protected Builder2 self() {
            return this;
        }
    }
}