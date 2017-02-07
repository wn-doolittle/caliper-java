/**
 * This file is part of IMS Caliper Analytics™ and is licensed to
 * IMS Global Learning Consortium, Inc. (http://www.imsglobal.org)
 * under one or more contributor license agreements.  See the NOTICE
 * file distributed with this work for additional information.
 *
 * IMS Caliper is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, version 3 of the License.
 *
 * IMS Caliper is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.imsglobal.caliper.requestors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.imsglobal.caliper.Config;
import org.imsglobal.caliper.Options;
import org.imsglobal.caliper.Sensor;
import org.imsglobal.caliper.databind.JsonFilters;
import org.imsglobal.caliper.databind.JsonObjectMapper;
import org.imsglobal.caliper.databind.JsonSimpleFilterProvider;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpRequestor<T> extends Requestor<T> {
    private static CloseableHttpClient httpClient;
    private static CloseableHttpResponse response = null;
    private Options options;
    private static final Logger log = LoggerFactory.getLogger(HttpRequestor.class);

    /**
     * Constructor instantiates a new HTTPRequestor. The args options provides the host
     * details to the HttpClient.
     * @param options
     */
    public HttpRequestor(Options options) {
        super();
        this.options = options;

        initialize();
    }

    /**
     * Init method
     */
    public static synchronized void initialize() {
        if (httpClient == null) {
            httpClient = HttpClients.createDefault();
        }
    }

    /**
     * Check initialized instance.
     */
    private static void checkInitialized() {
        if (httpClient == null) {
            throw new IllegalStateException("Http Client is not initialized.");
        }
    }

    /**
     * Get the configurable options
     * @return the options
     */
    public Options getOptions() {
        return options;
    }

    /**
     * Set the configurable options.
     * @param options
     */
    public void setOptions(Options options) {
        this.options = options;
    }

    /**
     * Post envelope.
     * @param data
     * @return status
     */
    @Override
    public boolean send(Sensor sensor, T data) {
        List<T> dataList = new ArrayList<>();
        dataList.add(data);

        return send(sensor, dataList);
    }

    /**
     * Post envelope.
     * @param data
     * @return status
     */
    @Override
    public boolean send(Sensor sensor, List<T> data) {
        boolean status = Boolean.FALSE;

        try {

            if (log.isDebugEnabled()) {
                log.debug("Entering send()...");
            }

            // Check if HttpClient is initialized.
            checkInitialized();

            // Create envelope, serialize it as a JSON string.
            Envelope<T> envelope = createEnvelope(sensor, DateTime.now(), Config.REMOTE_CALIPER_JSONLD_CONTEXT.value(), data);

            // Create mapper
            SimpleFilterProvider provider = JsonSimpleFilterProvider.create(JsonFilters.EXCLUDE_CONTEXT);
            ObjectMapper mapper = JsonObjectMapper.create(options.getJsonInclude(), provider);
            String json = mapper.writeValueAsString(envelope);

            // Create an HTTP StringEntity envelopes with the envelope JSON.
            StringEntity payload = generatePayload(json, ContentType.APPLICATION_JSON);

            // Do the post
            HttpPost httpPost = new HttpPost(this.getOptions().getHost());
            httpPost.setHeader("Authorization", this.getOptions().getApiKey());
            httpPost.setHeader(payload.getContentType());
            //httpPost.setHeader("Content-type", "application/json");

            //System.out.println("HEADER: " + httpPost.getHeaders("Content-type"));
            //System.out.println("PAYLOAD CONTENT-TYPE" + envelopes.getContentType().toString());
            //System.out.println("PAYLOAD CONTENT-TYPE.NAME" + envelopes.getContentType().getName());
            //System.out.println("PAYLOAD CONTENT-TYPE.VALUE" + envelopes.getContentType().getValue());

            httpPost.setEntity(payload);
            response = httpClient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() != 200) {
                int statusCode = response.getStatusLine().getStatusCode();
                response.close();
                throw new RuntimeException("Failed : HTTP error code : " + statusCode);
            } else {
                if (log.isDebugEnabled()) {
                    log.debug(response.getStatusLine().toString());
                    log.debug(EntityUtils.toString(response.getEntity()));
                }

                response.close();
                status = Boolean.TRUE;

                if (log.isDebugEnabled()) {
                    log.debug("Exiting send()...");
                }
            }

        } catch (ClientProtocolException cpe) {
            cpe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return status;
    }
}