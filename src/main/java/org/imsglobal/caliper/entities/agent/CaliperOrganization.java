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

import com.google.common.collect.ImmutableList;
import org.imsglobal.caliper.entities.CaliperEntity;

/**
 * The organization interface marks an object type that represents a collection of people organized
 * into a community or other social, commercial or political structure.  The interface allows
 * Caliper to catch errors at compile time for instances of the marked class rather than
 * at runtime if a marker annotation was defined instead.
 */
public interface CaliperOrganization extends CaliperAgent {

    /**
     * Return the parent organization.
     * @return
     */
    CaliperOrganization getSubOrganizationOf();


    /**
     * Return the members.
     * @return
     */
    ImmutableList<CaliperAgent> getMembers();
}