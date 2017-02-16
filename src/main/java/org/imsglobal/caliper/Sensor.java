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

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.stats.Statistics;
import org.imsglobal.caliper.validators.SensorValidator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sensor<T> {
    private String id;
    private Map<T, Client> clients = new HashMap<>();

    /**
     * Default Constructor
     */
    public Sensor() {

    }

    /**
     * Constructor. Initialize Sensor with an identifier.
     * @param id
     */
    public Sensor(@Nonnull String id) {
        SensorValidator.chkId(id, this.getClass().getSimpleName());

        this.id = id;
    }

    /**
     * Get the Sensor identifier
     * @return identifier
     */
    @Nonnull
    public String getId() {
        return id;
    }

    /**
     * Set the Sensor Identifier
     * @param id
     */
    public void setId(String id) {


        this.id = id;
    }

    /**
     * Register a client that will get invoked whenever an event is sent through this sensor
     * @param key the identifier of this client
     * @param client the client object
     */
    public void registerClient(T key, Client client){
        this.clients.put(key, client);
    }

    /**
     * Unregister a client. unregistered clients will be removed from the client map & no longer be referenceable
     * @param key
     * @return
     */
    public Client unRegisterClient(T key){
        return this.clients.remove(key);
    }

    /**
     * Send an entity describe to all configured clients.
     * @param sensor
     * @param data
     */
    public void describe(Sensor sensor, Entity data){
        for(Client client: clients.values()){
            client.describe(sensor, data);
        }
    }

    /**
     * Send a collection of entity describes to all configured clients.
     * @param sensor
     * @param data
     */
    public void describe(Sensor sensor, List<Entity> data){
        for(Client client: clients.values()){
            client.describe(sensor, data);
        }
    }

    /**
     * Send an event to all configured clients.
     * @param sensor
     * @param data
     */
    public void send(Sensor sensor, Event data){
        for(Client client: clients.values()){
            client.send(sensor, data);
        }
    }

    /**
     * Send a collection of events to all configured clients.
     * @param sensor
     * @param data
     */
    public void send(Sensor sensor, List<Event> data){
        for(Client client: clients.values()){
            client.send(sensor, data);
        }
    }

    /**
     * Returns
     * @return a map where the keys are the identifying objects and the values are the corresponding statistics
     * for that key's Client.
     */
    public Map<T, Statistics> getStatistics(){
        return Maps.transformValues(clients, new Function<Client, Statistics>() {
            @Nullable
            @Override
            public Statistics apply(@Nullable Client client) {
                return client.getStatistics();
            }
        });
    }
}