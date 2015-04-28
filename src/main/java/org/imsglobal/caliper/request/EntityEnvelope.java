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

package org.imsglobal.caliper.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.Sensor;
import org.imsglobal.caliper.entities.Entity;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;

public class EntityEnvelope extends Envelope<Entity> {

    @JsonProperty("@context")
    private EnvelopeContext context;

    @JsonProperty("@type")
    private EnvelopeType type;

    @JsonProperty("data")
    private Entity data;

    /**
     * Constructor
     */
    public EntityEnvelope() {
        this.context = EnvelopeContext.CONTEXT;
        // this.context = EnvelopeContext.ENTITY_CONTEXT;
        this.type = EnvelopeType.ENVELOPE;
        // this.type = EnvelopeType.ENTITY_ENVELOPE;
    }

    /**
     * Constructor
     * @param id
     * @param sendTime
     */
    public EntityEnvelope(String id, Sensor sensor, DateTime sendTime, Entity data) {
        super(id, sensor, sendTime);
        this.context = EnvelopeContext.CONTEXT;
        // this.context = EnvelopeContext.ENTITY_CONTEXT;
        this.type = EnvelopeType.ENVELOPE;
        // this.type = EnvelopeType.ENTITY_ENVELOPE;
        this.data = data;
    }

    /**
     * Get the context.
     * @return the context
     */
    @Override
    @Nonnull
    public EnvelopeContext getContext() {
        return context;
    }

    /**
     * Get the type.
     * @return the type
     */
    @Override
    @Nonnull
    public EnvelopeType getType() {
        return type;
    }

    /**
     * Get the Entity data.
     * @return the data
     */
    @Override
    @Nonnull
    public Entity getData() {
        return data;
    }

    /**
     * Set the Entity data.
     * @param data
     */
    @Override
    public void setData(@Nonnull Entity data) {
        this.data = data;
    }
}