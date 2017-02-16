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

package org.imsglobal.caliper.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.imsglobal.caliper.validators.SensorValidator;

/**
 * Caliper client options.  Review default constants and update placeholder entries (e.g., HTTP_HOST).
 */
public class Options {
    private final String apiKey;
    private final int connRequestorTimeout;
    private final int connTimeout;
    private final DataFormat dataFormat;
    private final String dataVersion;
    private final String httpContentType;
    private final String httpHost;
    private final JsonInclude.Include jacksonJsonInclude;
    private final String jsonldExternalCaliperContext;
    private final int socketTimeout;
    private final String testFixturesBaseDir;
    private final int uuidVersion;

    /**
     * Default timeout settings.
     */
    public static final int CONNECTION_REQUESTOR_TIMEOUT = Timeout.CONNECTION_REQUESTOR_TIMEOUT.value();
    public static final int CONNECTION_TIMEOUT = Timeout.CONNECTION_TIMEOUT.value();
    public static final int SOCKET_TIMEOUT = Timeout.SOCKET_TIMEOUT.value();

    /**
     * Default data format and version.
     */
    public static final DataFormat DATA_FORMAT = DataFormat.CALIPER_JSONLD;
    public static final String DATA_VERSION = "http://purl.imsglobal.org/ctx/caliper/v1p1";

    /**
     * HTTP Request Header field values.  Update faux Host value.
     */
    public static final String HTTP_CONTENT_TYPE = "application/ld+json";
    public static final String HTTP_HOST = "http://example.org";

    /**
     * Enumeration used with Jackson's JsonInclude that defines which properties are to be serialized.
     */
    public static final JsonInclude.Include JACKSON_JSON_INCLUDE = JsonInclude.Include.NON_EMPTY;

    /**
     * Default IMS Caliper external context document IRI.
     */
    public static final String JSONLD_EXTERNAL_CALIPER_CONTEXT = "http://purl.imsglobal.org/ctx/caliper/v1p1";

    /**
     * Default test fixtures directory path
     */
    public static final String TEST_FIXTURES_BASE_DIR = "../caliper-common-fixtures/src/test/resources/fixtures/";

    /**
     * Default UUID version to use when minting Event UUIDs.
     */
    public static final int UUID_VERSION = 4;

    /**
     * Constructor
     * @param builder
     */
    private Options(OptionsBuilder builder) {

        SensorValidator.chkApiKey(builder.apiKey);

        this.apiKey = builder.apiKey;
        this.connRequestorTimeout = SensorValidator.chkIntValue(builder.connRequestorTimeout, CONNECTION_REQUESTOR_TIMEOUT);
        this.connTimeout = SensorValidator.chkIntValue(builder.connTimeout, CONNECTION_TIMEOUT);
        this.dataFormat = (builder.dataFormat != null) ? builder.dataFormat : DATA_FORMAT;
        this.dataVersion = SensorValidator.chkStrValue(builder.dataVersion, DATA_VERSION);
        this.httpContentType = SensorValidator.chkStrValue(builder.httpContentType, HTTP_CONTENT_TYPE);
        this.httpHost = SensorValidator.chkStrValue(builder.httpHost, HTTP_HOST);
        this.jacksonJsonInclude = (builder.jacksonJsonInclude != null) ? builder.jacksonJsonInclude : JACKSON_JSON_INCLUDE;
        this.jsonldExternalCaliperContext = SensorValidator.chkStrValue(builder.jsonldExternalCaliperContext, JSONLD_EXTERNAL_CALIPER_CONTEXT);
        this.socketTimeout = SensorValidator.chkIntValue(builder.socketTimeout, SOCKET_TIMEOUT);
        this.testFixturesBaseDir = SensorValidator.chkStrValue(builder.testFixturesBaseDir, TEST_FIXTURES_BASE_DIR);
        this.uuidVersion = SensorValidator.chkIntValue(builder.uuidVersion, UUID_VERSION);
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
        return connTimeout;
    }

    /**
     * Get the Connection requestor timeout.
     * @return the connection requestor timeout
     */
    public int getConnRequestorTimeout() {
        return connRequestorTimeout;
    }

    /**
     * Get the data format.
     * @return data format
     */
    public DataFormat getDataFormat() {
        return dataFormat;
    }

    /**
     * Get the data version.
     * @return data version
     */
    public String getDataVersion() {
        return dataVersion;
    }

    /**
     * Get the HTTP Content-Type value.
     * @return HTTP Content-Type value
     */
    public String getHttpContentType() {
        return httpContentType;
    }

    /**
     *The REST API endpoint.
     * @return host
     */
    public String getHttpHost() {
        return httpHost;
    }

    /**
     * Get JsonInclude enumeration.
     * @return JsonInclude enum
     */
    public JsonInclude.Include getJacksonJsonInclude() {
        return jacksonJsonInclude;
    }

    /**
     * Get the external Caliper JSON-LD context URI.
     * @return the external Caliper JSON-LD context URI
     */
    public String getJsonldExternalCaliperContext() {
        return jsonldExternalCaliperContext;
    }

    /**
     * Get the Socket timeout.
     * @return the Socket timeout
     */
    public int getSocketTimeout() {
        return socketTimeout;
    }

    /**
     * Get the test fixtures base directory path.
     * @return test fixtures base directory path
     */
    public String getTestFixturesBaseDir() {
        return testFixturesBaseDir;
    }

    /**
     * Get the UUID version to be used when minting Event UUIDs.
     * @return UUID version
     */
    private int getUuidVersion() {
        return uuidVersion;
    }

    /**
     * Builder class provides a fluid interface for setting options properties.
     */
    public static class OptionsBuilder {
        private String apiKey;
        private int connRequestorTimeout = 0;
        private int connTimeout = 0;
        private DataFormat dataFormat;
        private String dataVersion;
        private String httpContentType;
        private String httpHost;
        private JsonInclude.Include jacksonJsonInclude;
        private String jsonldExternalCaliperContext;
        private int socketTimeout = 0;
        private String testFixturesBaseDir;
        private int uuidVersion = 0;

        /**
         * Constructor
         * @param apiKey
         */
        public OptionsBuilder(final String apiKey) {
            this.apiKey = apiKey;
        }

        /**
         * @param connRequestorTimeout
         * @return builder
         */
        public OptionsBuilder connRequestorTimeout(final int connRequestorTimeout) {
            this.connRequestorTimeout = connRequestorTimeout;
            return this;
        }

        /**
         * @param connTimeout
         * @return builder
         */
        public OptionsBuilder connTimeout(final int connTimeout) {
            this.connTimeout = connTimeout;
            return this;
        }

        /**
         * @param dataFormat
         * @return builder
         */
        public OptionsBuilder dataFormat(final DataFormat dataFormat) {
            this.dataFormat = dataFormat;
            return this;
        }

        /**
         * @param dataVersion
         * @return builder
         */
        public OptionsBuilder dataVersion(final String dataVersion) {
            this.dataVersion = dataVersion;
            return this;
        }

        /**
         * @param httpContentType
         * @return builder
         */
        public OptionsBuilder httpContentType(final String httpContentType) {
            this.httpContentType = httpContentType;
            return this;
        }

        /**
         * @param httpHost
         * @return builder
         */
        public OptionsBuilder httpHost(final String httpHost) {
            this.httpHost = httpHost;
            return this;
        }

        /**
         * @param jacksonJsonInclude
         * @return builder
         */
        public OptionsBuilder jacksonJsonInclude(final JsonInclude.Include jacksonJsonInclude) {
            this.jacksonJsonInclude = jacksonJsonInclude;
            return this;
        }

        /**
         * @param jsonldExternalCaliperContext
         * @return builder
         */
        public OptionsBuilder jsonldExternalCaliperContext(final String jsonldExternalCaliperContext) {
            this.jsonldExternalCaliperContext = jsonldExternalCaliperContext;
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
         * @param testFixturesBaseDir
         * @return builder
         */
        public OptionsBuilder testFixturesBaseDir(final String testFixturesBaseDir) {
            this.testFixturesBaseDir = testFixturesBaseDir;
            return this;
        }

        /**
         * @param uuidVersion
         * @return builder
         */
        public OptionsBuilder uuidVersion(final int uuidVersion) {
            this.uuidVersion = uuidVersion;
            return this;
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of Options.
         */
        public Options build() {
            return new Options(this);
        }
    }

    /**
     * Static Factory method.
     * @param apiKey
     * @return new builder instance
     */
    public static OptionsBuilder builder(String apiKey) {
        return new OptionsBuilder(apiKey);
    }
}