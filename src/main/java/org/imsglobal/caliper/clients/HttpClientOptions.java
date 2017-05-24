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

package org.imsglobal.caliper.clients;

import org.imsglobal.caliper.config.Timeout;
import org.imsglobal.caliper.validators.SensorValidator;

/**
 * Caliper client options.  Review default constants and update placeholder entries (e.g., HTTP_HOST).
 */
public class HttpClientOptions implements ClientOptions {
    private final String apiKey;
    private final int connectionTimeout;
    private final String contentType;
    private final String host;
    private final int socketTimeout;

    /**
     * Default timeout settings.
     */
    public static final int CONNECTION_TIMEOUT = Timeout.CONNECTION_TIMEOUT.value();
    public static final int SOCKET_TIMEOUT = Timeout.SOCKET_TIMEOUT.value();

    /**
     * HTTP Request Header field values.  Update faux Host value.
     */
    public static final String HTTP_CONTENT_TYPE = "application/json";
    public static final String HTTP_HOST = "https://example.org";

    /**
     * Constructor
     * @param builder
     */
    private HttpClientOptions(OptionsBuilder builder) {

        SensorValidator.chkApiKey(builder.apiKey);

        this.apiKey = builder.apiKey;
        this.connectionTimeout = SensorValidator.chkIntValue(builder.connectionTimeout, CONNECTION_TIMEOUT);
        this.contentType = SensorValidator.chkStrValue(builder.contentType, HTTP_CONTENT_TYPE);
        this.host = SensorValidator.chkStrValue(builder.host, HTTP_HOST);
        this.socketTimeout = SensorValidator.chkIntValue(builder.socketTimeout, SOCKET_TIMEOUT);
    }

    /**
     * Get the API key for unlocking the Caliper endpoint.
     * @return the apiKey
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Get the Connection timeout.
     * @return connection timeout
     */
    public int getConnTimeout() {
        return connectionTimeout;
    }

    /**
     * Get the HTTP Content-Type value.
     * @return HTTP Content-Type value
     */
    public String getContentType() {
        return contentType;
    }

    /**
     *The REST API endpoint.
     * @return host
     */
    public String getHost() {
        return host;
    }

    /**
     * Get the Socket timeout.
     * @return the Socket timeout
     */
    public int getSocketTimeout() {
        return socketTimeout;
    }

    /**
     * Builder class provides a fluid interface for setting options properties.
     */
    public static class OptionsBuilder {
        private String apiKey;
        private int connectionTimeout = 0;
        private String contentType;
        private String host;
        private int socketTimeout = 0;

        /**
         * Constructor
         */
        public OptionsBuilder() {

        }

        /**
         * @param apiKey
         * @return builder
         */
        public OptionsBuilder apiKey(final String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        /**
         * @param connectionTimeout
         * @return builder
         */
        public OptionsBuilder connectionTimeout(final int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        /**
         * @param contentType
         * @return builder
         */
        public OptionsBuilder contentType(final String contentType) {
            this.contentType = contentType;
            return this;
        }

        /**
         * @param host
         * @return builder
         */
        public OptionsBuilder host(final String host) {
            this.host = host;
            return this;
        }

        /**
         * @param socketTimeout
         * @return builder
         */
        public OptionsBuilder socketTimeout(final int socketTimeout) {
            this.socketTimeout = socketTimeout;
            return this;
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of Options.
         */
        public HttpClientOptions build() {
            return new HttpClientOptions(this);
        }
    }

    /**
     * Static Factory method.
     * @return new builder instance
     */
    public static OptionsBuilder builder() {
        return new OptionsBuilder();
    }
}