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

package org.imsglobal.caliper.request;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.imsglobal.caliper.Options;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.events.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpRequestor extends EventStoreRequestor {
    private static CloseableHttpClient httpClient;
    private static CloseableHttpResponse response = null;
    private Options options;
    private static final Logger LOG = LoggerFactory.getLogger(HttpRequestor.class);

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

    /*
     * @see org.imsglobal.caliper.request.EventStoreRequestor#send(org.imsglobal.caliper.events.Event)
     */
    @Override
    public boolean send(Event event) {

        boolean status = Boolean.FALSE;

        try {

            LOG.debug("Entering send()...");

            checkInitialized();

            HttpPost httpPost = new HttpPost(this.getOptions().getHost());
            httpPost.setEntity(new StringEntity(marshalEvent(event)));
            httpPost.setHeader("Authorization", this.getOptions().getApiKey());
            httpPost.setHeader("Content-type", "application/json");
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
     * org.imsglobal.caliper.request.EventStoreRequestor#send(DigitalResource)
     */
    @Override
    public boolean send(Entity entity) {
        // TODO Auto-generated method stub
        return Boolean.FALSE;
    }

    /**
     * @return the options
     */
    public Options getOptions() {
        return options;
    }

    /**
     * Options to set.
     * @param options
     */
    public void setOptions(Options options) {
        this.options = options;
    }

    private static void checkInitialized() {
        if (httpClient == null) {
            throw new IllegalStateException("Http Client is not initialized.");
        }
    }
}