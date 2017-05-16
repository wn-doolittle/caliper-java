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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.imsglobal.caliper.config.Options;
import org.imsglobal.caliper.databind.JxnCoercibleSimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpRequestor extends Requestor {
    private static CloseableHttpClient httpClient;
    private static CloseableHttpResponse response = null;
    private static final Logger log = LoggerFactory.getLogger(HttpRequestor.class);

    /**
     * Constructor instantiates a new HttpRequestor. The args options provides the host
     * details to the HttpClient.  Scoped private to force use of static factory method
     * for instantiating an HttpRequestor.
     * @param options
     */
    private HttpRequestor(Options options) {
        super(options);

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
     * Post envelope.
     * @param envelope
     * @return status
     */
    @Override
    public boolean send(Envelope envelope) {
        boolean status = Boolean.FALSE;

        try {
            if (log.isDebugEnabled()) {
                log.debug("Entering send()...");
            }

            // Check if HttpClient is initialized.
            checkInitialized();

            // Create mapper and serialize the envelope
            SimpleFilterProvider provider = new SimpleFilterProvider()
                .setFailOnUnknownId(true);

            ObjectMapper mapper = new ObjectMapper()
                .setDateFormat(new ISO8601DateFormat())
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .setFilterProvider(provider)
                .registerModules(new JodaModule(), new JxnCoercibleSimpleModule());

            String json = mapper.writeValueAsString(envelope);

            // Create an HTTP StringEntity.
            StringEntity payload = generatePayload(json, ContentType.APPLICATION_JSON);

            // Prep the post
            HttpPost httpPost = new HttpPost(super.getOptions().getHttpHost());
            httpPost.setHeader("Authorization",super.getOptions().getApiKey());
            httpPost.setHeader("Content-Type", super.getOptions().getHttpContentType());
            httpPost.setEntity(payload);

            // Execute POST
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

    /**
     * Factory Method for instantiating an HttpRequestor.
     * @param opts
     * @return
     */
    public static HttpRequestor create(Options opts) {
        return new HttpRequestor(opts);
    }
}