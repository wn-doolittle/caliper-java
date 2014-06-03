package org.imsglobal.caliper;

import org.apache.commons.lang.StringUtils;

/**
 * IntelliSense client options
 * 
 */
public class Options {

	/**
	 * The REST API endpoint (with scheme)
	 */
	private String host;

	/**
	 * The amount of milliseconds that passes before a request is marked as
	 * timed out
	 */
	private int timeout;

	/**
	 * Creates a default options
	 */
	public Options() {
		this(Defaults.HOST, Defaults.CONNECTION_TIMEOUT);
	}

	/**
	 * Creates an option with the provided settings
	 * 
	 * @param flushAt
	 * @param flushAfter
	 * @param maxQueueSize
	 * @param httpConfig
	 */
	Options(String host, int timeout) {
		setHost(host);
		setTimeout(timeout);
	}

	public String getHost() {
		return host;
	}

	/**
	 * Sets the REST API endpoint
	 * 
	 * @param host
	 */
	public Options setHost(String host) {
		if (StringUtils.isEmpty(host))
			throw new IllegalArgumentException(
					"Caliper#option#host must be a valid host, like 'https://api.caliper.org'.");

		this.host = host;
		return this;
	}

	/**
	 * Sets the milliseconds to wait before a flush is marked as timed out.
	 * 
	 * @param timeout
	 *            timeout in milliseconds.
	 */
	public Options setTimeout(int timeout) {
		if (timeout < 1000)
			throw new IllegalArgumentException(
					"Caliper#option#timeout must be at least 1000 milliseconds.");

		this.timeout = timeout;
		return this;
	}
}
