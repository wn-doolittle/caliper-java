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
import org.imsglobal.caliper.context.Context;
import org.imsglobal.caliper.validators.EntityValidator;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * This class provides a skeletal implementation of the Entity interface in order to minimize the effort
 * required to implement the interface.  BaseEntity specifies an additional set of optional properties that together
 * provide a richer representation of a Caliper Entity, including a map for adding additonal custom extensions.
 */
public abstract class BaseEntity implements Entity {

    @JsonProperty("@context")
    protected final String context;

    @JsonProperty("@id")
    protected final String id;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("dateCreated")
    private final DateTime dateCreated;

    @JsonProperty("dateModified")
    private final DateTime dateModified;

    @JsonProperty("extensions")
    private final Object extensions;

    /**
     * @param builder apply builder object properties to the Entity object.
     */
    protected BaseEntity(Builder<?> builder) {

        EntityValidator.checkContext(builder.context, Context.CONTEXT);
        EntityValidator.checkId("id", builder.id);
        EntityValidator.checkType(builder.type, EntityType.ENTITY);

        this.context = builder.context;
        this.id = builder.id;
        this.type = builder.type;
        this.name = builder.name;
        this.description = builder.description;
        this.dateCreated = builder.dateCreated;
        this.dateModified = builder.dateModified;
        this.extensions = builder.extensions;
    }

    /**
     * @return the context.
     */
    @Nonnull
    public String getContext() {
        return context;
    }

    /**
     * @return the id.
     */
    @Nonnull
    public String getId() {
        return id;
    }

    /**
     * @return the type.
     */
    @Nonnull
    public String getType() {
        return type;
    }

    /**
     * @return name.
     */
    @Nullable
    public String getName() {
        return name;
    }

    /**
     * @return description.
     */
    @Nullable
    public String getDescription() {
        return description;
    }

    /**
     * @return date created.
     */
    @Nullable
    public DateTime getDateCreated()
    {
        return dateCreated;
    }

    /**
     * @return the date modified.
     */
    @Nullable
    public DateTime getDateModified() {
        return dateModified;
    }

    /**
     * @return custom extensions object.
     */
    @Nullable
    public Object getExtensions() {
        return extensions;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> {
        private String context;
        private String id;
        private String type;
        private String name;
        private String description;
        private DateTime dateCreated;
        private DateTime dateModified;
        private Object extensions;

        protected abstract T self();

        /**
         * Constructor
         */
        public Builder() {
            context(Context.CONTEXT.getValue());
            type(EntityType.ENTITY.getValue());
        }

        /**
         * @param context
         * @return builder.
         */
        private T context(String context) {
            this.context = context;
            return self();
        }

        /**
         * @param id
         * @return builder.
         */
        public T id(String id) {
            this.id = id;
            return self();
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
         * @param name
         * @return builder.
         */
        public T name(String name) {
            this.name = name;
            return self();
        }

        /**
         * @param description
         * @return builder.
         */
        public T description(String description) {
            this.description = description;
            return self();
        }

        /**
         * @param dateCreated
         * @return builder.
         */
        public T dateCreated(DateTime dateCreated) {
            this.dateCreated = dateCreated;
            return self();
        }

        /**
         * @param dateModified
         * @return builder.
         */
        public T dateModified(DateTime dateModified) {
            this.dateModified = dateModified;
            return self();
        }

        /**
         * @param extensions
         * @return builder
         */
        public T extensions(Object extensions) {
            this.extensions = extensions;
            return self();
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
}