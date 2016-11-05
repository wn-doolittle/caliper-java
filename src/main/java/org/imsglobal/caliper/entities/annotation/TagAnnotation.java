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
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.BaseEntity;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.EntityType;
import org.imsglobal.caliper.entities.foaf.Agent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TagAnnotation extends BaseEntity implements Annotation {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("annotated")
    private DigitalResource annotated;

    @JsonProperty("actor")
    private final Agent actor;

    @JsonProperty("tags")
    private ImmutableList<String> tags;

    /**
     * @param builder apply builder object properties to the TagAnnotation object.
     */
    protected TagAnnotation(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.annotated = builder.annotated;
        this.actor = builder.actor;
        this.tags = ImmutableList.copyOf(builder.tags);
    }

    /**
     * @return the type
     */
    @Override
    @Nonnull
    public String getType() {
        return type;
    }

    /**
     * @return the annotated object's identifier
     */
    @Nonnull
    public DigitalResource getAnnotated() {
        return annotated;
    }

    /**
     * @return the actor
     */
    @Nonnull
    public Agent getActor() {
        return actor;
    }

    /**
     * Return an immutable view of the tags list.
     * @return the tags
     */
    @Nullable
    public ImmutableList<String> getTags() {
        return tags;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseEntity.Builder<T>  {
        private String type;
        private DigitalResource annotated;
        private Agent actor;
        private List<String> tags = Lists.newArrayList();

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(EntityType.TAG_ANNOTATION.getValue());
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
         * @param annotated
         * @return builder.
         */
        public T annotated(DigitalResource annotated) {
            this.annotated = annotated;
            return self();
        }

        /**
         * @param actor
         * @return builder.
         */
        public T actor(Agent actor) {
            this.actor = actor;
            return self();
        }

        /**
         * @param tags
         * @return annotation tags.
         */
        public T tags(List<String> tags) {
            this.tags = tags;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of TagAnnotation.
         */
        public TagAnnotation build() {
            return new TagAnnotation(this);
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