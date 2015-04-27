package org.imsglobal.caliper.validators;

import com.google.common.base.Strings;
import org.imsglobal.caliper.Options;

import static com.google.common.base.Preconditions.checkArgument;

public class SensorValidator {
    /**
     * Constructor
     */
    public SensorValidator() {

    }

    /**
     * Check if Client identifier is null or empty.
     * @param id
     * @throws IllegalArgumentException
     */
    public static void checkClientId(String id) throws IllegalArgumentException {
        checkArgument(!(Strings.isNullOrEmpty(id)), "Client identifier must be specified");
    }

    /**
     * Check if Sensor identifier is null or empty.
     * @param id
     * @throws IllegalArgumentException
     */
    public static void checkSensorId(String id) throws IllegalArgumentException {
        checkArgument(!(Strings.isNullOrEmpty(id)), "Sensor identifier must be specified");
    }

    /**
     * Check if apiKey is null or empty.
     * @param apiKey
     * @throws IllegalArgumentException
     */
    public static void checkApiKey(String apiKey) throws IllegalArgumentException {
        checkArgument(!(Strings.isNullOrEmpty(apiKey)), "Sensor client API key must be specified");
    }

    /**
     * Check if Host is null or empty.
     * @param host
     * @throws IllegalArgumentException
     */
    public static void checkHost(String host) throws IllegalArgumentException {
        checkArgument(!(Strings.isNullOrEmpty(host)), "Sensor client host must be specified");
    }

    /**
     * Check options
     * @param options
     * @throws IllegalArgumentException
     */
    public static void checkOptions(Options options) throws IllegalArgumentException {
        checkArgument(options != null, "Sensor client configuration options must be specified");
    }

    /**
     * check connection request timeout
     * @param connectionRequestTimeout
     * @throws IllegalArgumentException
     */
    public static void checkConnectionRequestTimeout(int connectionRequestTimeout) throws IllegalArgumentException {
        checkArgument(connectionRequestTimeout >= 1000, "Sensor connection request timeout must be at least 1000 milliseconds");
    }

    /**
     * check connection timeout
     * @param connectionTimeout
     * @throws IllegalArgumentException
     */
    public static void checkConnectionTimeout(int connectionTimeout) throws IllegalArgumentException {
        checkArgument(connectionTimeout >= 1000, "Sensor connection timeout must be at least 1000 milliseconds");
    }

    /**
     * check socket timeout
     * @param socketTimeout
     * @throws IllegalArgumentException
     */
    public static void checkSocketTimeout(int socketTimeout) throws IllegalArgumentException {
        checkArgument(socketTimeout >= 1000, "Sensor socket timeout must be at least 1000 milliseconds");
    }
}