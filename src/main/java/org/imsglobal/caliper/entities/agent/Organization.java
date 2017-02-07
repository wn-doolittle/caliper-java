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
import org.imsglobal.caliper.entities.AbstractEntity;
import org.imsglobal.caliper.entities.EntityType;

import javax.annotation.Nullable;

/**
 * Concrete implementation of a generic Organization.
 */
public class Organization extends AbstractEntity implements Org {

    @JsonProperty("subOrganizationOf")
    private final Org subOrganizationOf;

    /**
     * @param builder apply builder object properties to the object.
     */
    protected Organization(Builder<?> builder) {
        super(builder);
        this.subOrganizationOf = builder.subOrganizationOf;
    }

    /**
     * @return the parent organization.
     */
    @Nullable
    public Org getSubOrganizationOf() {
        return subOrganizationOf;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> extends AbstractEntity.Builder<T> {
        private Org subOrganizationOf;

        /**
         * Constructor
         */
        public Builder() {
            super.type(EntityType.ORGANIZATION);
        }

        /**
         * @param subOrganizationOf
         * @return builder.
         */
        public T subOrganizationOf(Org subOrganizationOf) {
            this.subOrganizationOf = subOrganizationOf;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the Organization.
         */
        public Organization build() {
            return new Organization(this);
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