/**
 * 
 */
package org.imsglobal.caliper.request;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.imsglobal.caliper.Options;
import org.imsglobal.caliper.entities.CaliperEntity;
import org.imsglobal.caliper.events.CaliperEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pnayak
 * 
 */
public class HttpRequestor extends EventStoreRequestor {

	private static final Logger LOG = LoggerFactory
			.getLogger(HttpRequestor.class);

	private static CloseableHttpClient httpClient;
	private static CloseableHttpResponse response = null;
	private Options options;

	/**
	 * Instantiates a new http requestor. The args options provides the host
	 * details to the HttpClient.
	 * 
	 * @param options
	 *            the options
	 */
	public HttpRequestor(Options options) {

		super();

		this.options = options;
		initialize();
	}

	public static synchronized void initialize() {
		if (httpClient == null) {
			httpClient = HttpClients.createDefault();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imsglobal.caliper.request.EventStoreRequestor#send(org.imsglobal.
	 * caliper.events.CaliperEvent)
	 */
	@Override
	public boolean send(CaliperEvent caliperEvent) {

		boolean status = Boolean.FALSE;

		try {

			LOG.debug("Entering send()...");

			checkInitialized();

			HttpPost httpPost = new HttpPost(this.getOptions().getHost());
			httpPost.setEntity(generatePayload(caliperEvent, null, null));
			response = httpClient.execute(httpPost);

			if (response.getStatusLine().getStatusCode() != 200) {
				int statusCode = response.getStatusLine().getStatusCode();
				response.close();
				status = Boolean.FALSE;
				throw new RuntimeException("Failed : HTTP error code : "
						+ statusCode);
			} else {

				LOG.debug("----------------------------------------");
				LOG.debug(response.getStatusLine().toString());
				LOG.debug(EntityUtils.toString(response.getEntity()));

				response.close();

				status = Boolean.TRUE;

				LOG.debug("Exiting send()...");
			}

		} catch (ClientProtocolException cpe) {
			status = Boolean.FALSE;
			cpe.printStackTrace();
		} catch (IOException ioe) {
			status = Boolean.FALSE;
			ioe.printStackTrace();
		}

		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imsglobal.caliper.request.EventStoreRequestor#send(org.imsglobal.
	 * caliper.entities.DigitalResource)
	 */
	@Override
	public boolean send(CaliperEntity caliperEntity) {
		// TODO Auto-generated method stub
		return Boolean.FALSE;
	}

	private static void checkInitialized() {
		if (httpClient == null) {
			throw new IllegalStateException("Http Client is not initialized.");
		}
	}

	/**
	 * @return the options
	 */
	public Options getOptions() {
		return options;
	}

	/**
	 * @param options
	 *            the options to set
	 */
	public void setOptions(Options options) {
		this.options = options;
	}

}
