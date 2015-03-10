package org.imsglobal.caliper;

import org.apache.commons.lang.StringUtils;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.request.EventStoreRequestor;
import org.imsglobal.caliper.request.HttpRequestor;
import org.imsglobal.caliper.stats.Statistics;

/**
 * The Caliper Client - Instantiate this to use the Caliper Sensor API.
 * 
 * The client is an HTTP wrapper over the Caliper REST API. It will allow you to
 * conveniently consume the API without making any HTTP requests yourself.
 */
public class Client {

    private String apiKey;
    private Options options;
    private EventStoreRequestor eventStoreRequestor;
    private Statistics stats;

    /**
     * Creates a new Caliper client.
     *
     * The client is an HTTP wrapper over the Caliper REST API. It will allow
     * you to conveniently consume the API without making any HTTP requests
     * yourself.
     *
     * This client is designed to be thread-safe and to not block each call in
     * order to make a HTTP request.
     *
     * @param options
     *          Options to configure the behavior of the Caliper client
     */
    public Client(Options options) {

        String errorPrefix = "caliper-java client must be initialized with a valid ";

        if (options == null)
            throw new IllegalArgumentException(errorPrefix + "options.");

        apiKey = options.getApiKey();

        if (StringUtils.isEmpty(apiKey))
            throw new IllegalArgumentException(errorPrefix + "apiKey.");

        if (StringUtils.isEmpty(options.getHost()))
            throw new IllegalArgumentException(errorPrefix + "host.");

        this.options = options;

        eventStoreRequestor = new HttpRequestor(options);

        stats = new Statistics();
    }

    /**
     * @return API key
     */
    public String getapiKey() {
        return apiKey;
    }

    /**
     * @param apiKey
     */
    public void setapiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * @return options
     */
    public Options getOptions() {
        return options;
    }

    /**
     * @return statistics
     */
    public Statistics getStatistics() {
        return this.stats;
    }

    /*
     * API Calls
     */

    /**
     * @param entity
     */
    public void describe(Entity entity) {

        boolean status = eventStoreRequestor.send(entity);

        this.stats.updateDescribes(1);

        if (status) {
            stats.updateSuccessful(1);
        } else {
            stats.updateFailed(1);
        }
    }

    /**
     * @param event
     */
    public void send(Event event) {

        boolean status = eventStoreRequestor.send(event);

        this.stats.updateMeasures(1);

        if (status) {
            stats.updateSuccessful(1);
        } else {
            stats.updateFailed(1);
        }
    }
}
