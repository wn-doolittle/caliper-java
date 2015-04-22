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

    /**
     * The API Key (potentially required by Caliper EventSTore)
     */
    private String apiKey;

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
     * @param host
     * @param timeout
     */
    Options(String host, int timeout) {
        setHost(host);
        setTimeout(timeout);
    }

    /**
     * @return host
     */
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

    /**
     * @return the apiKey
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * @param apiKey the apiKey to set
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
