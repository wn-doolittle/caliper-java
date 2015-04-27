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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.collect.Lists;
import org.apache.http.entity.StringEntity;
import org.imsglobal.caliper.Sensor;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.events.Event;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

public abstract class EventStoreRequestor {
    private String sensorId;

    /**
     * Constructor
     */
    public EventStoreRequestor(Sensor sensor) {
        this.sensorId = sensor.getId();
    }

    /**
     * Send event.
     * @param event
     * @return true/false boolean on success/failure
     */
    public abstract boolean send(Event event);

    /**
     * Send describes
     * @param entity
     * @return true/false boolean on success/failure
     */
    public abstract boolean send(Entity entity);

    /**
     * Generate the Caliper Event JSON payload.
     * @param envelopeId
     * @param sendTime
     * @param event
     * @return
     * @throws UnsupportedEncodingException
     */
    protected StringEntity generatePayload(String envelopeId, DateTime sendTime, Event event)
                                           throws UnsupportedEncodingException {

        if (envelopeId == null || envelopeId.isEmpty()) {
            envelopeId = "caliper-envelope-" + UUID.randomUUID().toString();
        }

        if (sendTime == null) {
            sendTime = DateTime.now();
        }

        String jsonPayload = getPayloadJson(envelopeId, sendTime, event);
        StringEntity payLoad = new StringEntity(jsonPayload);
        payLoad.setContentType("application/json");

        return payLoad;
    }

    /**
     * Get the Caliper event JSON.
     * @param envelopeId
     * @param sendTime
     * @param event
     * @return
     */
    protected String getPayloadJson(String envelopeId, DateTime sendTime, Event event) {

        List<EventStoreEnvelope> listPayload = Lists.newArrayList();

        EventStoreEnvelope envelope = new EventStoreEnvelope();
        envelope.setId(envelopeId);
        envelope.setSensorId(sensorId);
        envelope.setSendTime(sendTime);
        envelope.setData(event);

        listPayload.add(envelope);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new ISO8601DateFormat());
        mapper.registerModule(new JodaModule());
        String jsonPayload = null;
        try {
            jsonPayload = mapper.writeValueAsString(listPayload);
        } catch (JsonProcessingException e) {
            // LOG.error("Json Parse Exception", e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return jsonPayload;
    }

    /**
     * Write Caliper event as JSON.
     * @param event
     * @return mapper
     * @throws JsonProcessingException
     */
    protected String marshalEvent(Event event) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new ISO8601DateFormat());
        mapper.registerModule(new JodaModule());
        return mapper.writeValueAsString(event);
    }
}