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

package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class CaliperCollection extends BaseEntity {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("isPartOf")
    private final Entity isPartOf;

    @JsonProperty("items")
    private final ImmutableList<Entity> items;

    /**
     * @param builder apply builder object properties to the object.
     */
    protected CaliperCollection(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.isPartOf = builder.isPartOf;
        this.items = ImmutableList.copyOf(builder.items);
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
     * @return the parent reference.
     */
    @Nullable
    public Entity getIsPartOf() {
        return isPartOf;
    }

    /**
     * Return an immutable list of the Collection's items.
     * @return the items
     */
    @Nullable
    public ImmutableList<Entity> getItems() {
        return items;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseEntity.Builder<T>  {
        private String type;
        private Entity isPartOf;
        private List<Entity> items = Lists.newArrayList();

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(EntityType.COLLECTION.getValue());
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
         * @param isPartOf
         * @return builder.
         */
        public T isPartOf(Entity isPartOf) {
            this.isPartOf = isPartOf;
            return self();
        }

        /**
         * @param items
         * @return builder.
         */
        public T items(List<Entity> items) {
            this.items = items;
            return self();
        }

        /**
         * @param item
         * @return builder.
         */
        public T item(Entity item) {
            this.items.add(item);
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new Collection instance.
         */
        public CaliperCollection build() {
            return new CaliperCollection(this);
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
     * @return a new Builder instance.
     */
    public static Builder<?> builder() {
        return new Builder2();
    }
}