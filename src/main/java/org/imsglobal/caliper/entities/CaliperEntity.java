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

import org.imsglobal.caliper.CaliperSendable;
import org.imsglobal.caliper.context.JsonldContext;
import org.joda.time.DateTime;

/**
 * The Entity interface provides the minimal set of properties and behaviors required of a Caliper Entity.  For an
 * Entity to be linkable, dereferencing the identifier should result in a representation of the node.
 */
public interface CaliperEntity extends CaliperSendable {

    /**
     * The JSON-LD context provides a mapping of terms to IRIs.  The identifier
     * should be expressed as a unique IRI in conformance with the JSON-LD specification.
     * @return the context IRI.
     */
    JsonldContext getContext();

    /**
     * Each Entity (or node in the graph as defined by JSON-LD) requires an identifier.
     * The identifier should be expressed as a unique IRI in conformance with the
     * JSON-LD specification.
     * @return the identifier IRI
     */
    String getId();

    /**
     * Specifies the type of Entity or node in the graph as defined by JSON-LD.  The type should be
     * expressed as a unique IRI in conformance with the JSON-LD specification.
     * @return the type IRI
     */
    CaliperEntityType getType();

    /**
     * The name of the Entity.  Optional.
     * @return
     */
    String getName();

    /**
     * A short description of the entity.  Optional
     * @return the description
     */
    String getDescription();

    /**
     * A combined date and time representation (including milliseconds) of when an entity was created
     * in accordance with the ISO 8601 standard.  Optional.
     * @return the event time
     */
    DateTime getDateCreated();

    /**
     * A combined date and time representation (including milliseconds) of when an entity was modified
     * in accordance with the ISO 8601 standard.  Optional.
     * @return the event time
     */
    DateTime getDateModified();

    /**
     * Additional custom properties provided that are germane to the Event.  Optional.
     * @return extensions
     */
    Object getExtensions();
}