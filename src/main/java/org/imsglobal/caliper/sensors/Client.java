package org.imsglobal.caliper.sensors;

import org.imsglobal.caliper.requestors.Requestor;
import org.imsglobal.caliper.statistics.Statistics;
import org.imsglobal.caliper.validators.SensorValidator;

import javax.annotation.Nonnull;

/**
 * This class provides a skeletal implementation of the Sensor Client interface
 * in order to minimize the effort required to implement the interface.
 */
public abstract class Client {
    private String id;
    private Requestor requestor;
    private Statistics statistics;

    /**
     * Constructor
     * @param id
     * @param requestor
     */
    public Client(String id, Requestor requestor) {

        SensorValidator.chkId(id, this.getClass().getSimpleName());
        SensorValidator.chkOptions(requestor.getOptions());

        this.id = id;
        this.requestor = requestor;
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
     * Get the associated requestor.
     * @return requestor
     */
    public Requestor getRequestor() {
        return requestor;
    }

    /**
     * Create and return an Envelope
     * @param id
     * @param sendTime
     * @param dataVersion
     * @param data
     * @return envelope
     */
    /**
    public Envelope create(String id, DateTime sendTime, String dataVersion, List<Object> data) {
        return sensor.create(id, DateTime.now(), dataVersion, data);
    }
     */

    /**
     * Send Envelope.
     * @param envelope
     */
    /**
    public void send(Envelope envelope) {
        sensor.send(this, envelope);
    }
     */

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