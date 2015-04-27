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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.events.Event;
import org.joda.time.DateTime;

@JsonPropertyOrder({
    "@context",
    "@id",
    "@type",
    "sensor",
    "sendTime",
    "data" })
public class EventStoreEnvelope {

    @JsonProperty("@context")
    private Envelope context;

    @JsonProperty("@id")
    private String id;

    @JsonProperty("@type")
    private Envelope type;

    @JsonProperty("sensor")
    private String sensorId;

    @JsonProperty("sendTime")
    private DateTime sendTime;

    @JsonProperty("data")
    private Event data;

    /**
     * Constructor
     */
    public EventStoreEnvelope() {
        setContext(Envelope.CONTEXT);
        setType(Envelope.TYPE);
    }

    /**
     * @return the context
     */
    public Envelope getContext() {
        return context;
    }

    /**
     * Set the context (private scope).
     * @param context
     */
    private void setContext(Envelope context) {
        this.context = context;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the identifier
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the type
     */
    public Envelope getType() {
        return type;
    }

    /**
     * Set the type (private scope).
     * @param type
     */
    private void setType(Envelope type) {
        this.type = type;
    }

    /**
     * Get the sensor
     * @return sensor identifier
     */
    public String getSensorId() {
        return sensorId;
    }

    /**
     * Set the sensor identifier.
     * @param sensorId
     */
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    /**
     * @return the send time.
     */
    public DateTime getSendTime() {
        return sendTime;
    }

    /**
     * Set the time.
     * @param sendTime
     */
    public void setSendTime(DateTime sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return the data
     */
    public Event getData() {
        return data;
    }

    /**
     * Set the data.
     * @param data
     */
    public void setData(Event data) {
        this.data = data;
    }
}