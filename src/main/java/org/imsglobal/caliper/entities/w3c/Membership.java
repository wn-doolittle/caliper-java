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
import org.imsglobal.caliper.entities.foaf.Agent;

/**
 * Indicates the nature of an Agent's membership in an organization.  A W3C Membership represents
 * an n-ary relationship between an Agent, an Organization and a Role.  A Caliper Membership varies
 * from the W3C Membership class definition by allowing users to specify a set of roles associated
 * with an Agent's membership in an organization.  See http://www.w3.org/TR/vocab-org/#org:Membership
 */
public interface Membership {

    /**
     * @return the Person (or other Agent including Organization) involved in the Membership relationship.
     */
    Agent getMember();

    /**
     * @return the Organization in which the Agent is a member.
     */
    Organization getOrganization();

    /**
     * @return the set of roles that the agent plays in a membership relationship with an organization.  Each
     * string is expected to be in the form of a dereferenceable URI.
     */
    ImmutableList<Role> getRoles();

    /**
     * @return The current status of a membership which applies to all roles.
     */
    Status getStatus();
}