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

package org.imsglobal.caliper;

import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.request.EventStoreRequestor;
import org.imsglobal.caliper.request.HttpRequestor;
import org.imsglobal.caliper.stats.Statistics;
import org.imsglobal.caliper.validators.SensorValidator;

/**
 * The Caliper Client - Instantiate this to use the Caliper Sensor API.
 * 
 * The client is an HTTP wrapper over the Caliper REST API. It will allow
 * you to conveniently consume the API without making any HTTP requests
 * yourself.
 *
 * This client is designed to be thread-safe and to not block each call in
 * order to make a HTTP request.
 */
public class Client {

    private String id;
    private String sensorId;
    private Options options;
    private EventStoreRequestor eventStoreRequestor;
    private Statistics stats;

    /**
     * Default constructor
     */
    public Client() {

    }

    /**
     * Initialize Client with a Client identifier, Sensor Identifier and configurable options.
     * @param id
     * @param sensor
     * @param options
     */
    public Client(String id, Sensor sensor, Options options) {
        SensorValidator.checkClientId(id);
        // SensorValidator.checkSensorId(sensor.getId());
        SensorValidator.checkOptions(options);
        SensorValidator.checkApiKey(options.getApiKey());
        SensorValidator.checkHost(options.getHost());

        this.id = id;
        this.sensorId = sensor.getId();
        this.options = options;
        this.eventStoreRequestor = new HttpRequestor(sensor, options);
        this.stats = new Statistics();
    }

    /**
     * Get the Client identifier.
     * @return client identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Set the Client identifier.
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the Sensor identifier.
     * @return sensor identifier
     */
    public String getSensorId() {
        return sensorId;
    }

    /**
     * Set the sensor identifier.
     * @param sensor
     */
    public void setSensorId(Sensor sensor) {
        this.sensorId = sensor.getId();
    }

    /**
     * Get the configuration options.
     * @return options
     */
    public Options getOptions() {
        return options;
    }

    /**
     * Set the Configuration options.
     * @param options
     */
    public void setOptions(Options options) {
        this.options = options;
    }

    /**
     * @return statistics
     */
    public Statistics getStatistics() {
        return this.stats;
    }

    /*
     * API Calls
     */

    /**
     * @param entity
     */
    public void describe(Entity entity) {
        boolean status = eventStoreRequestor.send(entity);

        this.stats.updateDescribes(1);

        if (status) {
            stats.updateSuccessful(1);
        } else {
            stats.updateFailed(1);
        }
    }

    /**
     * @param event
     */
    public void send(Event event) {
        boolean status = eventStoreRequestor.send(event);

        this.stats.updateMeasures(1);

        if (status) {
            stats.updateSuccessful(1);
        } else {
            stats.updateFailed(1);
        }
    }
}