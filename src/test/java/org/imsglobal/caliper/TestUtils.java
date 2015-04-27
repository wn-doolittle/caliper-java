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

/**
 * Test utilities
 */
public class TestUtils {

    public enum TestDefaults {
        HOST("http://localhost:1080/1.0/event/put"),
        API_KEY("6xp7jKrOSOWOgy3acxHFWA"),
        CONNECTION_REQUEST_TIMEOUT("10000"),
        CONNECTION_TIMEOUT("10000"),
        SOCKET_TIMEOUT("10000");

        private final String value;

        /**
         * Private constructor
         * @param value
         */
        private TestDefaults(final String value) {
            this.value = value;
        }

        /**
         * @return default string
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * Constructor
     */
    public TestUtils() {

    }

    /**
     * @return test options
     */
    public static Options getTestingOptions() {
        Options options = new Options();
        options.setHost(TestDefaults.HOST.getValue());
        options.setApiKey(TestDefaults.API_KEY.getValue());
        options.setConnectionRequestTimeout(Integer.parseInt(TestDefaults.CONNECTION_REQUEST_TIMEOUT.getValue()));
        options.setConnectionTimeout(Integer.parseInt(TestDefaults.CONNECTION_TIMEOUT.getValue()));
        options.setSocketTimeout(Integer.parseInt(TestDefaults.SOCKET_TIMEOUT.getValue()));

        return options;
    }
}