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

package org.imsglobal.caliper.events;

import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.agent.SoftwareApplication;
import org.imsglobal.caliper.entities.session.Session;
import org.imsglobal.caliper.entities.w3c.Membership;
import org.imsglobal.caliper.entities.w3c.Organization;

public interface EventContext {

    /**
     * An Entity that represents the target of a learning interaction.  Navigating to a resource while engaged in a
     * learning activity is an example of a target entity.
     * @return target entity
     */
    Targetable getTarget();

    /**
     * An Entity generated as a result of the learning interaction.  Example entities typed as Generatable include
     * assignable attempts, assessment item responses and graded outcome results.
     * @return generated entity
     */
    Generatable getGenerated();

    /**
     * Represents the entity from where the navigation commenced.
     * @return the referring context
     */
     Entity getReferrer();

    /**
     * The module, application, platform, system and/or service that provides the technological context within which
     * the learning activity occurs.
     * @return the edApp context
     */
    SoftwareApplication getEdApp();

    /**
     * The group represents a collection of people organized together into a community or other social, commercial
     * or political structure.  The group has some common purpose or reason for existence which goes beyond the set
     * of people belonging to it and can act as an Agent.  For learning interactions, a typical group context would
     * comprise the course context within which the learning activity occurs.
     * @return the group context
     */
    Organization getGroup();

    /**
     * The Membership context defines an actor's roles and status as a member of an organization or group.
     * @return the membership context.
     */
    Membership getMembership();

    /**
     * Represents the current user session.
     * @return the current session context
     */
    Session getSession();

    /**
     * An LTI-scoped identifier representing the originating Tool Consumer user session during which one or more launch
     * requests to external Tool Providers are initiated.  Provision of a federated session identifier helps to
     * correlate Tool Provider learning activities with those of the Tool Consumer during the same "logical" session.
     * @return the originating tool consumer context
     */
    Session getFederatedSession();

    /**
     * Additional custom properties provided that are germane to the Event.
     * @return extensions
     */
    Object getExtensions();
}