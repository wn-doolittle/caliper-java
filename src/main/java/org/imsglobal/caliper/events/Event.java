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
import org.imsglobal.caliper.entities.foaf.Agent;
import org.joda.time.DateTime;

/**
 * The Event interface provides the minimal set of properties and behaviors required of a Caliper Event.
 * Note that inclusion of the learning context within which learning activities occur is not required.
 * However, events that are generated without reference to context will generally fail to reflect the
 * Event model defined by most Metric Profiles as well as Level 1+ conformance requirements.
 */
public interface Event {

    /**
     * The JSON-LD context provides a mapping of terms to IRIs.  The identifier
     * should be expressed as a unique IRI in conformance with the JSON-LD specification.
     * @return the context IRI.
     */
    String getContext();

    /**
     * Specifies the type of event or node in the graph as defined by JSON-LD.  The type should be
     * expressed as a unique IRI in conformance with the JSON-LD specification.
     * @return the event type IRI
     */
    String getType();

    /**
     * The actor engaged in the interaction.  Analogous to a subject.
     * @return
     */
    Agent getActor();

    /**
     * The action undertaken by the actor.  Analogous to a predicate or verb.  The action should be
     * expressed as a unique IRI in conformance with the JSON-LD specification.
     * @return the action undertaken by the actor
     */
    String getAction();

    /**
     *  The object of the interaction.  The object should be expressed as a unique IRI in conformance
     *  with the JSON-LD specification.
     * @return the object of the interaction
     */
    Entity getObject();

    /**
     * A combined date and time representation (including milliseconds) of when an event occurred
     * formatted in accordance with the ISO 8601 standard.
     * @return the event time
     */
    DateTime getEventTime();
}