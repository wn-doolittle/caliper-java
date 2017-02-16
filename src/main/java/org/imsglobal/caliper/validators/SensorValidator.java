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

package org.imsglobal.caliper.validators;

import com.google.common.base.Strings;
import org.imsglobal.caliper.config.Options;

import static com.google.common.base.Preconditions.checkArgument;

public class SensorValidator {
    /**
     * Constructor
     */
    public SensorValidator() {

    }

    /**
     * Check if apiKey is null or empty.
     *
     * @param apiKey
     * @throws IllegalArgumentException
     */
    public static void chkApiKey(String apiKey) throws IllegalArgumentException {
        checkArgument(!(Strings.isNullOrEmpty(apiKey)), "API key must be specified.");
    }

    /**
     * Check if identifier is null or empty.
     *
     * @param id
     * @throws IllegalArgumentException
     */
    public static void chkId(String id, String className) throws IllegalArgumentException {
        checkArgument(!(Strings.isNullOrEmpty(id)), className + " instance id property must be specified.");
    }

    /**
     * Check options
     *
     * @param options
     * @throws IllegalArgumentException
     */
    public static void chkOptions(Options options) throws IllegalArgumentException {
        checkArgument(options != null, "Sensor client configuration options must be specified.");
    }

    /**
     * Check if Sensor identifier is null or empty.
     *
     * @param id
     * @throws IllegalArgumentException
     */
    public static void chkSensorId(String id) throws IllegalArgumentException {
        checkArgument(!(Strings.isNullOrEmpty(id)), "Sensor identifier must be specified");
    }

    /**
     * Check user-supplied integer value against default config value
     * and return the larger of the two values compared.
     * @param builderValue
     * @param defaultValue
     * @return
     */
    public static int chkIntValue(int builderValue, int defaultValue) {
        return (builderValue > defaultValue) ? builderValue : defaultValue;
    }

    /**
     * Check user-supplied integer value against default config value;
     * if null or empty return default config value.
     * @param builderValue
     * @param defaultValue
     * @return
     */
    public static String chkStrValue(String builderValue, String defaultValue) {
        return !(Strings.isNullOrEmpty(builderValue)) ? builderValue : defaultValue;
    }
}