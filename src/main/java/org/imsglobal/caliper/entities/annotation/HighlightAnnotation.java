/**
 * This file is part of IMS Caliper Analytics™ and is licensed to
 * IMS Global Learning Consortium, Inc. (http://www.imsglobal.org)
 * under one or more contributor license agreements.  See the NOTICE
 * file distributed with this work for additional information.
 *
 * IMS Caliper is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, version 3 of the License.
 *
 * IMS Caliper is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.imsglobal.caliper.entities.annotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.AbstractEntity;
import org.imsglobal.caliper.entities.EntityType;
import org.imsglobal.caliper.entities.agent.Agent;
import org.imsglobal.caliper.entities.resource.Resource;
import org.imsglobal.caliper.selectors.Selector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HighlightAnnotation extends AbstractEntity implements Annotation {

    @JsonProperty("annotated")
    private Resource annotated;

    @JsonProperty("annotator")
    private final Agent annotator;

    @JsonProperty("selection")
    private Selector selection;

    @JsonProperty("selectionText")
    private String selectionText;

    /**
     * @param builder apply builder object properties to the object.
     */
    protected HighlightAnnotation(Builder<?> builder) {
        super(builder);

        this.annotated = builder.annotated;
        this.annotator = builder.annotator;
        this.selection = builder.selection;
        this.selectionText = builder.selectionText;
    }

    /**
     * @return the id.
     */
    @Nonnull
    public String getId() {
        return id;
    }

    /**
     * @return the annotated object's identifier
     */
    @Nonnull
    public Resource getAnnotated() {
        return annotated;
    }

    /**
     * @return the actor
     */
    @Nonnull
    public Agent getAnnotator() {
        return annotator;
    }

    /**
     * @return the selection
     */
    @Nullable
    public Selector getSelection() {
        return selection;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> extends AbstractEntity.Builder<T> {
        private Resource annotated;
        private Agent annotator;
        private Selector selection;
        private String selectionText;

        /**
         * Constructor
         */
        public Builder() {
            super.type(EntityType.HIGHLIGHT_ANNOTATION);
        }

        /**
         * @param annotated
         * @return builder.
         */
        public T annotated(Resource annotated) {
            this.annotated = annotated;
            return self();
        }

        /**
         * @param annotator
         * @return builder.
         */
        public T annotator(Agent annotator) {
            this.annotator = annotator;
            return self();
        }

        /**
         * @param selector
         * @return builder
         */
        public T selection(Selector selector) {
            this.selection = selector;
            return self();
        }

        /**
         * @param selectionText
         * @return annotation selection text.
         */
        public T selectionText(String selectionText) {
            this.selectionText = selectionText;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the HighlightAnnotation.
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