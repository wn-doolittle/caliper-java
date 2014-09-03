package org.imsglobal.caliper;

import org.imsglobal.caliper.entities.CaliperEntity;
import org.imsglobal.caliper.entities.CaliperDigitalResource;
import org.imsglobal.caliper.events.CaliperEvent;
import org.imsglobal.caliper.stats.CaliperStatistics;

public class CaliperSensor {

	private static Client defaultClient;

	/**
	 * Creates a new Caliper client.  This method is thread-safe.
	 * 
	 * @param apiKey
	 *            Your Caliper EventStore apiKey.
	 * 
	 * @param options
	 *            Options to configure the behavior of the Caliper client.
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
	 * CaliperSensor API
	 * 
	 * Measure a CaliperEvent
	 * 
	 * @param caliperEvent
	 *            the Caliper CaliperEvent
	 * 
	 */
	public static void send(CaliperEvent caliperEvent) {

		isInitialized();
		defaultClient.send(caliperEvent);
	}

	/**
	 * CaliperSensor API
	 * 
	 * Describe a {@link CaliperDigitalResource} that is part of the learning graph
	 * 
	 * @param digitalResource
	 *            the CaliperDigitalResource
	 * 
	 */
	public static void describe(CaliperEntity caliperEntity) {
		isInitialized();
		defaultClient.describe(caliperEntity);
	}

	/**
	 * Returns statistics for the Caliper client
	 */
	public static CaliperStatistics getStatistics() {
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