package org.imsglobal.caliper;

import org.apache.commons.lang.StringUtils;
import org.imsglobal.caliper.entities.Agent;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.events.CaliperEvent;
import org.imsglobal.caliper.request.EventStoreRequestor;
import org.imsglobal.caliper.request.HttpRequestor;
import org.imsglobal.caliper.stats.CaliperStatistics;

/**
 * The Caliper Client - Instantiate this to use the Caliper Sensor API.
 * 
 * The client is an HTTP wrapper over the Caliper REST API. It will allow you to
 * conveniently consume the API without making any HTTP requests yourself.
 * 
 */
public class Client {

	private String apiKey;
	private Options options;
	private EventStoreRequestor eventStoreRequestor;
	private CaliperStatistics stats;

	/**
	 * Creates a new Caliper client.
	 * 
	 * The client is an HTTP wrapper over the Caliper REST API. It will allow
	 * you to conveniently consume the API without making any HTTP requests
	 * yourself.
	 * 
	 * This client should be designed to be thread-safe and to not block each
	 * call in order to make a HTTP request
	 * 
	 * 
	 * @param apiKey
	 *            Your Caliper EventStore apiKey
	 * 
	 */
	public Client(String apiKey) {

		this(apiKey, new Options());
	}

	/**
	 * Creates a new Caliper client.
	 * 
	 * The client is an HTTP wrapper over the Caliper REST API. It will allow
	 * you to conveniently consume the API without making any HTTP requests
	 * yourself.
	 * 
	 * This client should be designed to be thread-safe and to not block each
	 * call in order to make a HTTP request
	 * 
	 * 
	 * @param apiKey
	 *            Your Caliper EventStore apiKey.
	 * @param options
	 *            Options to configure the behavior of the Caliper client
	 * 
	 * 
	 */
	public Client(String apiKey, Options options) {

		String errorPrefix = "caliper-java client must be initialized with a valid ";

		if (StringUtils.isEmpty(apiKey))
			throw new IllegalArgumentException(errorPrefix + "apiKey.");

		if (options == null)
			throw new IllegalArgumentException(errorPrefix + "options.");

		this.apiKey = apiKey;
		this.options = options;

		eventStoreRequestor = new HttpRequestor();

		stats = new CaliperStatistics();
	}

	//
	// API Calls
	//

	public void measure(CaliperEvent caliperEvent) {

		eventStoreRequestor.send(caliperEvent);

		stats.updateMeasures(1);

	}

	public void describe(DigitalResource digitalResource) {

		eventStoreRequestor.send(digitalResource);

		stats.updateDescribes(1);

	}

	public void describe(Agent agent) {

		eventStoreRequestor.send(agent);

		stats.updateDescribes(1);

	}

	//
	// Getters and Setters
	//

	public String getapiKey() {
		return apiKey;
	}

	public void setapiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Options getOptions() {
		return options;
	}

	public CaliperStatistics getStatistics() {
		return this.stats;
	}
}
