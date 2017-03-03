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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BookmarkAnnotation extends AbstractEntity implements Annotation {

    @JsonProperty("annotated")
    private Resource annotated;

    @JsonProperty("annotator")
    private final Agent annotator;

    @JsonProperty("bookmarkNotes")
    private String bookmarkNotes;

    /**
     * @param builder apply builder object properties to the object.
     */
    protected BookmarkAnnotation(Builder<?> builder) {
        super(builder);

        this.annotated = builder.annotated;
        this.annotator = builder.annotator;
        this.bookmarkNotes = builder.bookmarkNotes;
    }

    /**
     * @return the annotated object's identifier
     */
    @Nonnull
    public Resource getAnnotated() {
        return annotated;
    }

    /**
     * @return the Annotator
     */
    @Nonnull
    public Agent getAnnotator() {
        return annotator;
    }

    /**
     * @return the bookmarkNotes
     */
    @Nullable
    public String getBookmarkNotes() {
        return bookmarkNotes;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> extends AbstractEntity.Builder<T> {
        private Resource annotated;
        private Agent annotator;
        private String bookmarkNotes;

        /**
         * Constructor
         */
        public Builder() {
            super.type(EntityType.BOOKMARK_ANNOTATION);
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
         * @param bookmarkNotes
         * @return annotation bookmark notes.
         */
        public T bookmarkNotes(String bookmarkNotes) {
            this.bookmarkNotes = bookmarkNotes;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the BookmarkAnnotation.
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