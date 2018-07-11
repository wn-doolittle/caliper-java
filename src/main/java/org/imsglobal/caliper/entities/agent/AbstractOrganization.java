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
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.AbstractEntity;
import org.imsglobal.caliper.entities.EntityType;

import javax.annotation.Nullable;
import java.util.List;

/**
 * This class provides a skeletal implementation of the Organization interface
 * in order to minimize the effort required to implement the interface.
 */
public abstract class AbstractOrganization extends AbstractEntity implements CaliperOrganization {

    @JsonProperty("subOrganizationOf")
    private final CaliperOrganization subOrganizationOf;

    @JsonProperty("members")
    private final ImmutableList<CaliperAgent> members;

    /**
     * @param builder apply builder object properties to the object.
     */
    protected AbstractOrganization(Builder<?> builder) {
        super(builder);
        this.subOrganizationOf = builder.subOrganizationOf;
        this.members = ImmutableList.copyOf(builder.members);
    }

    /**
     * @return the parent organization.
     */
    @Nullable
    public CaliperOrganization getSubOrganizationOf() {
        return subOrganizationOf;
    }

    /**
     * Return an immutable list of the Collection's items.
     * @return the items
     */
    @Override
    @Nullable
    public ImmutableList<CaliperAgent> getMembers() {
        return members;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> extends AbstractEntity.Builder<T> {
        private CaliperOrganization subOrganizationOf;
        private List<CaliperAgent> members = Lists.newArrayList();

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
        public T subOrganizationOf(CaliperOrganization subOrganizationOf) {
            this.subOrganizationOf = subOrganizationOf;
            return self();
        }

        /**
         * @param members
         * @return builder.
         */
        public T members(List<CaliperAgent> members) {
            if(members != null){
                this.members.addAll(members);
            }
            return self();
        }

        /**
         * @param member
         * @return builder.
         */
        public T member(CaliperAgent member) {
            this.members.add(member);
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