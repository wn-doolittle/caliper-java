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
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.events.Event;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

public abstract class EventStoreRequestor {

    public abstract boolean send(Event caliperEvent);

    public abstract boolean send(Entity caliperEntity);

    /**
     * @param caliperEvent
     * @param id
     *            - OPTIONAL, unique Id for the event
     * @param sendTime
     *            - OPTIONAL, time to record the send of this event
     * @return
     * @throws UnsupportedEncodingException
     */
    protected StringEntity generatePayload(Event caliperEvent,
            String id, DateTime sendTime) throws UnsupportedEncodingException {

        if (id == null) {
            id = "caliper-java_" + UUID.randomUUID().toString();
        }

        if (sendTime == null) {
            sendTime = DateTime.now();
        }

        String jsonPayload = getPayloadJson(caliperEvent, id, sendTime);
        StringEntity payLoad = new StringEntity(jsonPayload);
        payLoad.setContentType("application/json");

        return payLoad;
    }

    /**
     * @param caliperEvent
     * @param id
     * @param sendTime
     * @return
     */
    protected String getPayloadJson(Event caliperEvent, String id, DateTime sendTime) {

        List<EventStoreEnvelope> listPayload = Lists.newArrayList();

        EventStoreEnvelope envelope = new EventStoreEnvelope();
        envelope.setId(id);
        envelope.setType("caliperEvent");
        envelope.setTime(sendTime.toString());
        envelope.setData(caliperEvent);

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

    protected String marshalEvent(Event caliperEvent) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new ISO8601DateFormat());
        mapper.registerModule(new JodaModule());
        return mapper.writeValueAsString(caliperEvent);
    }
}