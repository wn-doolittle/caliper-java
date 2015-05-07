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

package org.imsglobal.caliper.entities.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.EntityType;
import org.imsglobal.caliper.entities.w3c.Role;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "description",
    "roles",
    "extensions",
    "dateCreated",
    "dateModified" })
public abstract class Agent extends Entity implements org.imsglobal.caliper.entities.foaf.Agent {

    @JsonProperty("@type")
    private final EntityType type;

    @JsonProperty("roles")
    private final List<Role> roles;

    /**
     * @param builder apply builder object properties to the Person object.
     */
    protected Agent(Builder<?> builder) {
        super(builder);

        EntityValidator.checkType(builder.type, EntityType.AGENT);

        this.type = builder.type;
        this.roles = ImmutableList.copyOf(builder.roles);
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
     * @return roles
     */
    @Nullable
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Entity.Builder<T>  {
        private EntityType type;
        private List<Role> roles = Lists.newArrayList();

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(EntityType.AGENT);
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
         *
         * @param roles
         * @return builder.
         */
        public T roles(List<Role> roles) {
            this.roles = roles;
            return self();
        }

        /**
         *
         * @param role
         * @return builder.
         */
        public T role(Role role) {
            this.roles.add(role);
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