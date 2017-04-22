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

import org.imsglobal.caliper.config.Options;
import org.imsglobal.caliper.requestors.Envelope;
import org.imsglobal.caliper.requestors.HttpRequestor;
import org.imsglobal.caliper.requestors.Requestor;
import org.imsglobal.caliper.stats.Statistics;
import org.imsglobal.caliper.validators.SensorValidator;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * The Caliper Client - Instantiate this to use the Caliper Sensor API.
 * 
 * The client is an HTTP wrapper over the Caliper REST API. It will allow
 * you to conveniently consume the API without making any HTTP requests
 * yourself.
 *
 * This client is designed to be thread-safe and to not block each call in
 * order to make a HTTP requestors.
 */
public class Client {
    private String id;
    private Options options;
    private Envelope envelope;
    private Statistics stats;

    /**
     * Initialize Client with a Client identifier and configurable options.
     * @param options
     */
    public Client(@Nonnull String id, @Nonnull Options options) {
        SensorValidator.chkId(id, this.getClass().getSimpleName());
        SensorValidator.chkOptions(options);

        this.id = id;
        this.options = options;
        this.stats = new Statistics();
    }

    /**
     * Get the Client identifier.
     * @return client identifier
     */
    @Nonnull
    public String getId() {
        return id;
    }

    /**
     * Get the configuration options.
     * @return options
     */
    @Nonnull
    public Options getOptions() {
        return options;
    }


    /**
     * Create the Envelope.
     * @param sensor
     * @param sendTime
     * @param dataVersion
     * @param data
     * @return envelope
     */
    public Envelope create(Sensor sensor, DateTime sendTime, String dataVersion, List<Object> data) {
        envelope = new Envelope(sensor, DateTime.now(), dataVersion, data);
        return envelope;
    }

    /**
     * Send an envelope to a target endpoint.
     * @param envelope
     */
    /**
    public void send(Envelope envelope) {
        send(envelope);
    }
     */

    /**
     * Send a collection of events to a target endpoint.
     * @param envelope
     */
    public void send(Envelope envelope) {
        Requestor requestor = new HttpRequestor(options);
        updateStats(requestor.send(envelope));
    }

    /**
     * Get statistics.
     * @return statistics
     */
    @Nonnull
    public Statistics getStatistics() {
        return this.stats;
    }

    /**
     * Update stats
     * @param status
     */
    private void updateStats(boolean status) {
        this.stats.updateMeasures(1);

        if (status) {
            stats.updateSuccessful(1);
        } else {
            stats.updateFailed(1);
        }
    }
}