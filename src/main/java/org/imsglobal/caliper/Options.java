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

import com.fasterxml.jackson.annotation.JsonInclude;
import org.imsglobal.caliper.validators.SensorValidator;

/**
 * Caliper client options
 */
public class Options {
    private String host;
    private String apiKey;
    private int connectionRequestTimeout;
    private int connectionTimeout;
    private int socketTimeout;
    private JsonInclude.Include jsonInclude;

    /**
     * Constructor.  Set default options other than apiKey.
     */
    public Options() {
        this.host = Defaults.HOST.getValue();
        this.connectionRequestTimeout = Integer.parseInt(Defaults.CONNECTION_REQUEST_TIMEOUT.getValue());
        this.connectionTimeout = Integer.parseInt(Defaults.CONNECTION_TIMEOUT.getValue());
        this.socketTimeout = Integer.parseInt(Defaults.SOCKET_TIMEOUT.getValue());
        this.jsonInclude = JsonInclude.Include.valueOf(Defaults.JSON_INCLUDE.getValue());
    }

    /**
     * Constructor. Creates an option with the provided settings.
     * @param host
     * @param apiKey
     * @param connectionRequestTimeout
     * @param connectionTimeout
     * @param soTimeout
     */
    public Options(String host, String apiKey, int connectionRequestTimeout, int connectionTimeout, int soTimeout, JsonInclude.Include include) {
        setHost(host);
        setApiKey(apiKey);
        setConnectionRequestTimeout(connectionRequestTimeout);
        setConnectionTimeout(connectionTimeout);
        setSocketTimeout(socketTimeout);
        setJsonInclude(jsonInclude);
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
        SensorValidator.checkHost(host);
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
        SensorValidator.checkApiKey(apiKey);
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
        SensorValidator.checkConnectionTimeout(connectionTimeout);
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
        SensorValidator.checkConnectionRequestTimeout(connectionRequestTimeout);
        this.connectionRequestTimeout = connectionRequestTimeout;
        return this;
    }

    /**
     * Get the Socket timeout.
     * @return the Socket timeout
     */
    public int getSocketTimeout() {
        return socketTimeout;
    }

    /**
     * Sets the Socket timeout in milliseconds to wait before a flush is marked as timed out.
     * @param socketTimeout
     * @return socket timeout
     */
    public Options setSocketTimeout(int socketTimeout) {
        SensorValidator.checkSocketTimeout(socketTimeout);
        this.socketTimeout = socketTimeout;
        return this;
    }

    /**
     * Get JsonInclude enumeration.
     * @return JsonInclude enum
     */
    public JsonInclude.Include getJsonInclude() {
        return jsonInclude;
    }

    /**
     * Specify enumeration defining which properties should be included/excluded in serialized JSON.
     * @param jsonInclude
     * @return
     */
    public Options setJsonInclude(JsonInclude.Include jsonInclude) {
        //SensorValidator.checkJsonInclude(jsonInclude);
        this.jsonInclude = jsonInclude;
        return this;
    }
}