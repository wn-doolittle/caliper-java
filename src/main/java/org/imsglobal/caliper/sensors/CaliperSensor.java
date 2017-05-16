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

package org.imsglobal.caliper.sensors;

import org.imsglobal.caliper.requestors.Envelope;
import org.imsglobal.caliper.statistics.Statistics;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Map;

public interface CaliperSensor {

    /**
     * Sensor identifier
     * @return id
     */
    String getId();

    /**
     * Register Client.
     * @param client
     */
    void registerClient(Client client);

    /**
     * Unregister Client.
     * @param key
     */
    void unregisterClient(String key);

    /**
     * Retrieve a single Client with a known key.
     * @param key
     * @return
     */
    Client getClient(String key);

    /**
     * Get all Clients.
     * @return
     */
    Map<String, Client> getClients();

    /**
     * Create an Envelope
     * @return
     */
    Envelope create(String id, DateTime sendTime, String dataVersion, List<Object> data);

    /**
     * Send an envelope to all Clients.
     * @param envelope
     */
    void send(Envelope envelope);

    /**
     * Send an envelope to a particular Client.
     * @param envelope
     */
    void send(Client client, Envelope envelope);

    /**
     * Get statistics.
     * @return map
     */
    Map<String, Statistics> getStatistics();

    // Map<String, Statistics> clientStatistics(Client client);
}
