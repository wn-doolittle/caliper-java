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
import org.apache.http.entity.StringEntity;
import org.imsglobal.caliper.Sensor;
import org.joda.time.DateTime;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public abstract class EventStoreRequestor<T> {

    /**
     * Constructor
     */
    public EventStoreRequestor() {

    }

    /**
     * Send Caliper data to a target event store.
     * @param sensor
     * @param data
     * @return true/false boolean on success/failure
     */
    public abstract boolean send(Sensor sensor, T data);

    /**
     * Send a collection of Caliper data to a target event store.
     * @param sensor
     * @param data
     * @return true/false boolean on success/failure
     */
    public abstract boolean send(Sensor sensor, List<T> data);

    /**
     * Generate the Caliper data JSON payload.
     * @param sensor
     * @param data
     * @return JSON string
     * @throws UnsupportedEncodingException
     */
    protected StringEntity generatePayload(Sensor sensor, T data) throws UnsupportedEncodingException {
        List<T> dataList = new ArrayList<>();
        dataList.add(data);

        return generatePayload(sensor, dataList);
    }

    /**
     * Generate the Caliper data JSON payload.
     * @param sensor
     * @param data
     * @return JSON string
     * @throws UnsupportedEncodingException
     */
    protected StringEntity generatePayload(Sensor sensor, List<T> data) throws UnsupportedEncodingException {
        String jsonPayload = getPayloadJson(sensor, data);
        StringEntity payLoad = new StringEntity(jsonPayload);
        payLoad.setContentType("application/json");

        return payLoad;
    }

    /**
     * Get the Caliper event JSON.
     * @param sensor
     * @param data
     * @return jsonPayload;
     */
    protected String getPayloadJson(Sensor sensor, T data) {
        List<T> dataList = new ArrayList<>();
        dataList.add(data);

        return getPayloadJson(sensor, dataList);
    }

    /**
     * Get the Caliper event JSON.
     * @param sensor
     * @param data
     * @return jsonPayload;
     */
    protected String getPayloadJson(Sensor sensor, List<T> data) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new ISO8601DateFormat());
        mapper.registerModule(new JodaModule());
        String jsonPayload = null;
        try {
            jsonPayload = mapper.writeValueAsString(createEnvelope(sensor, data));
        } catch (JsonProcessingException e) {
            // LOG.error("Json Parse Exception", e);
            e.printStackTrace();
        }

        return jsonPayload;
    }

    /**
     * Write Caliper event as JSON.
     * @param data
     * @return mapper
     * @throws JsonProcessingException
     */
    protected String marshalData(List<T> data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new ISO8601DateFormat());
        mapper.registerModule(new JodaModule());
        return mapper.writeValueAsString(data);
    }

    /**
     * Create Event envelope
     * @param sensor
     * @param data
     * @return event envelope
     */
    private Envelope createEnvelope(Sensor sensor, List<T> data) {
        Envelope<T> envelope = new Envelope<>();
        envelope.setSensorId(sensor);
        envelope.setSendTime(DateTime.now());
        envelope.setData(data);

        return envelope;
    }
}