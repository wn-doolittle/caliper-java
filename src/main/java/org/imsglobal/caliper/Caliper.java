package org.imsglobal.caliper;

import org.imsglobal.caliper.entities.Agent;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.events.CaliperEvent;
import org.imsglobal.caliper.stats.CaliperStatistics;

public class Caliper {

	private static Client defaultClient;

	/**
	 * Creates a new Caliper client.
	 * 
	 * Creates a new Caliper client.
	 * 
	 * This method is thread-safe.
	 * 
	 * @param apiKey
	 *            Your Caliper EventStore apiKey
	 * 
	 * @param options
	 *            Options to configure the behavior of the Caliper client
	 * 
	 * 
	 */
	public static synchronized void initialize(Options options) {

		if (defaultClient == null)
			defaultClient = new Client(options);
	}

	private static void isInitialized() {

		if (defaultClient == null) {
			throw new IllegalStateException("Caliper client is "
					+ "not initialized. Please call Caliper.iniitalize(..); "
					+ "before calling measure or describe");
		}
	}

	//
	// API Calls
	//

	/**
	 * Caliper Sensor API
	 * 
	 * Measure a CaliperEvent
	 * 
	 * @param caliperEvent
	 *            the Caliper CaliperEvent
	 * 
	 */
	public static void measure(CaliperEvent caliperEvent) {

		isInitialized();
		defaultClient.measure(caliperEvent);
	}

	/**
	 * Caliper Sensor API
	 * 
	 * Describe a {@link DigitalResource} that is part of the learning graph
	 * 
	 * @param digitalResource
	 *            the DigitalResource
	 * 
	 */
	public static void describe(DigitalResource digitalResource) {
		isInitialized();
		defaultClient.describe(digitalResource);
	}

	/**
	 * Caliper Sensor API
	 * 
	 * Describe a {@link Agent} that is part of the learning graph
	 * 
	 * @param agent
	 *            the Agent
	 * 
	 */
	public static void describe(Agent agent) {
		isInitialized();
		defaultClient.describe(agent);
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
