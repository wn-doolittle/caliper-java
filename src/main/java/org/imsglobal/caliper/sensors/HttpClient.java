package org.imsglobal.caliper.sensors;

import org.imsglobal.caliper.requestors.HttpRequestor;

/**
 * Provisions the Sensor with an HttpClient that binds to an HttpRequestor.
 */
public class HttpClient extends Client {

    /**
     * Constructor.  Scope is private to force use of the static factory method for instantiating an HttpClient.
     * @param id
     * @param requestor
     */
    private HttpClient(String id, HttpRequestor requestor) {
        super(id, requestor);
    }

    /**
     * Factory method for instantiating an HttpClient.
     * @param id
     * @param requestor
     * @return HttpClient
     */
    public static HttpClient create(String id, HttpRequestor requestor) {
        return new HttpClient(id, requestor);
    }
}