/**
 * 
 */
package org.imsglobal.caliper.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.imsglobal.caliper.Options;
import org.imsglobal.caliper.entities.Agent;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.events.CaliperEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * @author pnayak
 * 
 */
public class HttpRequestor implements EventStoreRequestor {

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
	public void send(CaliperEvent caliperEvent) {

		try {

			LOG.debug("Entering send()...");

			checkInitialized();

			HttpPost httpPost = new HttpPost(this.getOptions().getHost());
			httpPost.setEntity(generatePayload(caliperEvent));
			response = httpClient.execute(httpPost);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			LOG.debug("----------------------------------------");
			LOG.debug(response.getStatusLine().toString());
			LOG.debug(EntityUtils.toString(response.getEntity()));

			response.close();

			LOG.debug("Exiting send()...");

		} catch (ClientProtocolException cpe) {
			cpe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * @param caliperEvent
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	protected StringEntity generatePayload(CaliperEvent caliperEvent)
			throws UnsupportedEncodingException {
		
		Gson gson = new Gson();
		StringEntity payLoad = new StringEntity(gson.toJson(caliperEvent));
		payLoad.setContentType("application/json");
		
		return payLoad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imsglobal.caliper.request.EventStoreRequestor#send(org.imsglobal.
	 * caliper.entities.DigitalResource)
	 */
	@Override
	public void send(DigitalResource digitalResource) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imsglobal.caliper.request.EventStoreRequestor#send(org.imsglobal.
	 * caliper.entities.Agent)
	 */
	@Override
	public void send(Agent agent) {
		// TODO Auto-generated method stub

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
