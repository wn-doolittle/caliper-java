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

import org.apache.commons.lang.StringUtils;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.request.EventStoreRequestor;
import org.imsglobal.caliper.request.HttpRequestor;
import org.imsglobal.caliper.stats.Statistics;

/**
 * The Caliper Client - Instantiate this to use the Caliper Sensor API.
 * 
 * The client is an HTTP wrapper over the Caliper REST API. It will allow you to
 * conveniently consume the API without making any HTTP requests yourself.
 */
public class Client {

    private String id;
    private String apiKey;
    private Options options;
    private EventStoreRequestor eventStoreRequestor;
    private Statistics stats;

    /**
     * Creates a new Caliper client.
     *
     * The client is an HTTP wrapper over the Caliper REST API. It will allow
     * you to conveniently consume the API without making any HTTP requests
     * yourself.
     *
     * This client is designed to be thread-safe and to not block each call in
     * order to make a HTTP request.
     *
     * @param options
     *          Options to configure the behavior of the Caliper client
     */
    public Client(Options options) {

        String errorPrefix = "caliper-java client must be initialized with a valid ";

        if (options == null)
            throw new IllegalArgumentException(errorPrefix + "options.");

        apiKey = options.getApiKey();

        if (StringUtils.isEmpty(apiKey))
            throw new IllegalArgumentException(errorPrefix + "apiKey.");

        if (StringUtils.isEmpty(options.getHost()))
            throw new IllegalArgumentException(errorPrefix + "host.");

        this.options = options;

        eventStoreRequestor = new HttpRequestor(options);

        stats = new Statistics();
    }

    /**
     * Get the Sensor client identifier
     * @return sensor client identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Set the Sensor client identifier
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return API key
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * @param apiKey
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * @return options
     */
    public Options getOptions() {
        return options;
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