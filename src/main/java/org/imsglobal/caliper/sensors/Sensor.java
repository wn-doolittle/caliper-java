package org.imsglobal.caliper.sensors;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.imsglobal.caliper.requestors.Envelope;
import org.imsglobal.caliper.statistics.Statistics;
import org.joda.time.DateTime;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Concrete implementation of the Caliper Sensor interface.
 */
public class Sensor implements CaliperSensor {
    private String id;
    private Map<String, Client> clients = new HashMap<>();
    //private Envelope envelope;

    /**
     * Constructor. Scope is private to force use of the static factory method for instantiating a Sensor.
     */
    private Sensor(String id) {
        this.id = id;
    }

    /**
     * Get identifier.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Create the Envelope.
     * @param id
     * @param sendTime
     * @param dataVersion
     * @param data
     * @return envelope
     */
    public Envelope create(String id, DateTime sendTime, String dataVersion, List<Object> data) {
        return new Envelope(id, DateTime.now(), dataVersion, data);
    }

    /**
     * Register a Sensor client.
     * @param client the client object
     */
    public void registerClient(Client client) {
        this.clients.put(client.getId(), client);
    }

    /**
     * Unregister a Sensor client.
     * @param key
     * @return
     */
    public void unregisterClient(String key) {
        this.clients.remove(key);
    }

    /**
     * Retrieve a client.
     * @param key
     * @return
     */
    public Client getClient(String key) {
        return this.clients.get(key);
    }

    /**
     * Retrieve Map of clients.
     * @return clients
     */
    public Map<String, Client> getClients() {
        return clients;
    }

    /**
     * Send an envelope to all registered Clients.
     * @param envelope
     */
    public void send(Envelope envelope) {
        if (clients.size() > 0) {
            for(Client client: clients.values()){
                client.getRequestor().send(envelope);
            }
        } else {
            throw new IllegalStateException("No Clients have been registered.");
        }
    }

    /**
     * Send an envelope to a particular registered Client.
     * @param envelope
     */
    public void send(Client client, Envelope envelope) {
        if (clients.containsKey(client.getId())) {
            client.getRequestor().send(envelope);
        } else {
            throw new IllegalArgumentException(client.getId() + " is not listed among the registered clients.");
        }
    }

    /**
     * Returns a map where the keys are the identifying objects and the values are the corresponding statistics
     * for that key's Client.
     * @return a map
     */
    public Map<String, Statistics> getStatistics() {
        return Maps.transformValues(clients, new Function<Client, Statistics>() {
            @Nullable
            @Override
            public Statistics apply(@Nullable Client client) {
                return client.getStatistics();
            }
        });
    }

    /**
     * Factory method for creating Sensors.
     * @return Sensors
     */
    public static Sensor create(String id) {
        return new Sensor(id);
    }
}