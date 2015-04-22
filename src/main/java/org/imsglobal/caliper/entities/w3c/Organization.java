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

package org.imsglobal.caliper.entities.w3c;

import com.google.common.collect.ImmutableList;

/**
 * A collection of people organized together into a community or other social, commercial or political structure.
 * The group has some common purpose or reason for existence which goes beyond the set of people belonging to it
 * and can act as an Agent. Organizations are often decomposable into hierarchical structures.
 */
public interface Organization {

    /**
     * An identifier that can be used to used to uniquely identify the organization.  Equivalent to org:identifier.
     * @return the identifier.
     */
    String getId();

    /**
     * A membership entity that records the role of some Agent within this Organization.
     * @return membership
     */
    ImmutableList<Membership> getMembership();

    /**
     * The container holding the Memberships for this Organization.
     * @return memberships;
     */
    // ImmutableList<MembershipContainer> getMemberships();

    /**
     * Equivalent of W3C Organization Ontology org:subOrganizationOf property.  Represents hierarchical containment
     * of Organizations or OrganizationalUnits; indicates an Organization which contains this Organization.
     * @return parent organization
     */
    Organization getSubOrganizationOf();
}