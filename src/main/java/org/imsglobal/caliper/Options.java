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

package org.imsglobal.caliper;

import org.apache.commons.lang.StringUtils;

/**
 * Caliper client options
 */
public class Options {
    private String host;
    private String apiKey;
    private int connectionRequestTimeout;
    private int connectionTimeout;
    private int soTimeout;

    /**
     * Constructor.  Set default options other than apiKey.
     */
    public Options() {
        this(Defaults.HOST.getValue(),
            Integer.parseInt(Defaults.CONNECTION_REQUEST_TIMEOUT.getValue()),
            Integer.parseInt(Defaults.CONNECTION_TIMEOUT.getValue()),
            Integer.parseInt(Defaults.SO_TIMEOUT.getValue()));
    }

    /**
     * Constructor. Creates an option with the provided settings.
     * @param host
     * @param connectionRequestTimeout
     * @param connectionTimeout
     * @param soTimeout
     */
    public Options(String host, int connectionRequestTimeout, int connectionTimeout, int soTimeout) {
        setHost(host);
        setConnectionRequestTimeout(connectionRequestTimeout);
        setConnectionTimeout(connectionTimeout);
        setSoTimeout(soTimeout);
    }

    /**
     *The REST API endpoint (with scheme).
     * @return host
     */
    public String getHost() {
        return host;
    }

    /**
     * Set the REST API endpoint.
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
     * The API Key (potentially required by a Caliper EventStore).
     * @return the apiKey
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Set the apiKey
     * @param apiKey
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Get the Connection timeout.
     * @return connection timeout
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * Sets the Connection timeout in milliseconds to wait before a flush is marked as timed out.
     * @param connectionTimeout
     * @return connection timeout
     */
    public Options setConnectionTimeout(int connectionTimeout) {
        if (connectionTimeout < 1000) {
            throw new IllegalArgumentException("Caliper#option#connectionTimeout must be at least 1000 milliseconds.");
        }

        this.connectionTimeout = connectionTimeout;
        return this;
    }

    /**
     * Get the Connection request timeout.
     * @return the connection request timeout
     */
    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    /**
     * Sets the Connection request timeout in milliseconds to wait before a flush is marked as timed out.
     * @param connectionRequestTimeout
     * @return connection request timeout
     */
    public Options setConnectionRequestTimeout(int connectionRequestTimeout) {
        if (connectionRequestTimeout < 1000) {
            throw new IllegalArgumentException("Caliper#option#connectionRequestTimeout must be at least 1000 milliseconds.");
        }

        this.connectionRequestTimeout = connectionRequestTimeout;
        return this;
    }

    /**
     * Get the SO timeout.
     * @return the SO timeout
     */
    public int getSoTimeout() {
        return soTimeout;
    }

    /**
     * Sets the SO Connection timeout in milliseconds to wait before a flush is marked as timed out.
     * @param soTimeout
     * @return SO timeout
     */
    public Options setSoTimeout(int soTimeout) {
        if (soTimeout < 1000) {
            throw new IllegalArgumentException("Caliper#option#soTimeout must be at least 1000 milliseconds.");
        }

        this.soTimeout = soTimeout;
        return this;
    }
}