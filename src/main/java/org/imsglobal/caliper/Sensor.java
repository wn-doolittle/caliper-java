package org.imsglobal.caliper;

import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.stats.Statistics;

public class Sensor {

    private static Client defaultClient;

    /**
     * Creates a new Caliper client.  This method is thread-safe.
     * @param options
 *              Options to configure the behavior of the Caliper client.
     */
    public static synchronized void initialize(Options options) {

        if (defaultClient == null)
            defaultClient = new Client(options);
    }

    /**
     * Client initialization check
     */
    private static void isInitialized() {

        if (defaultClient == null) {
            throw new IllegalStateException("Caliper client is "
                    + "not initialized. Please call Caliper.initialize(..); "
                    + "before calling measure or describe");
        }
    }

    /**
     * Sensor API
     *
     * Describe a {@link org.imsglobal.caliper.entities.DigitalResource} that is part of the learning graph.
     *
     * @param entity
     */
    public static void describe(Entity entity) {
        isInitialized();
        defaultClient.describe(entity);
    }

    /**
     * Sensor API
     *
     * Emit a Caliper event to a target event store.
     *
     * @param event
     *            the Caliper Event
     *
     */
    public static void send(Event event) {

        isInitialized();
        defaultClient.send(event);
    }

    /**
     * Returns statistics for the Caliper client
     */
    public static Statistics getStatistics() {
        isInitialized();
        return defaultClient.getStatistics();
    }

    /**
     * Fetches the default Caliper client singleton
     *
     * @return
     */
    public static Client getDefaultClient() {
        return defaultClient;
    }

}