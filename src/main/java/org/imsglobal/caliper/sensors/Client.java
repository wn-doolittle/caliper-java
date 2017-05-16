package org.imsglobal.caliper.sensors;

import org.imsglobal.caliper.requestors.Envelope;
import org.imsglobal.caliper.requestors.Requestor;
import org.imsglobal.caliper.statistics.Statistics;
import org.imsglobal.caliper.validators.SensorValidator;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * This class provides a skeletal implementation of the Sensor Client interface
 * in order to minimize the effort required to implement the interface.
 */
public abstract class Client {
    private String id;
    private Map<String, Requestor> requestors = new HashMap<>();
    private Statistics statistics;

    /**
     * Constructor
     * @param id
     */
    protected Client(String id) {
        SensorValidator.chkId(id, this.getClass().getSimpleName());
        //SensorValidator.chkOptions(requestor.getOptions());

        this.id = id;
        this.statistics = new Statistics();
    }

    /**
     * Get identifier.
     * @return id
     */
    @Nonnull
    public String getId() {
        return id;
    }

    /**
     * Register a Requestor.
     * @param requestor the requestor
     */
    public void registerRequestor(Requestor requestor) {
        requestors.put(requestor.getId(), requestor);
    }

    /**
     * Unregister a Sensor client.
     * @param key
     * @return
     */
    public void unregisterClient(String key) {
        requestors.remove(key);
    }

    /**
     * Retrieve a requestor.
     * @param key
     * @return
     */
    public Requestor getRequestor(String key) {
        return requestors.get(key);
    }

    /**
     * Retrieve Map of clients.
     * @return clients
     */
    public Map<String, Requestor> getRequestors() {
        return requestors;
    }

    /**
     * Delegate serialization and transmission of the Envelope to all registered Requestors.
     * @param envelope
     */
    public void send(Envelope envelope) {
        if (requestors.size() > 0) {
            for(Requestor requestor: requestors.values()){
                requestor.send(envelope);
            }
        } else {
            throw new IllegalStateException("No Requestors have been registered.");
        }
    }

    /**
     * Delegate serialization and transmission of the Envelope to a particular registered Requestor.
     * @param envelope
     */
    public void send(Requestor requestor, Envelope envelope) {
        if (requestors.containsKey(requestor.getId())) {
            requestor.send(envelope);
        } else {
            throw new IllegalArgumentException(requestor.getId() + " is not a registered Requestor.");
        }
    }

    /**
    public Map<T, Statistics> statistics() {
        return sensor.statistics(this);
    }
     */

    /**
     * Get statistics.
     * @return statistics
     */
    @Nonnull
    public Statistics getStatistics() {
        return this.statistics;
    }

    /**
     * Update statistics
     * @param status
     */
    private void updateStatistics(boolean status) {
        this.statistics.updateMeasures(1);

        if (status) {
            statistics.updateSuccessful(1);
        } else {
            statistics.updateFailed(1);
        }
    }
}