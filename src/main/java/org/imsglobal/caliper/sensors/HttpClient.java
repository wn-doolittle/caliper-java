package org.imsglobal.caliper.sensors;

/**
 * Provisions the Sensor with an HttpClient that binds to one or more Requestors.
 */
public class HttpClient extends Client {

    /**
     * Constructor.  Scope is private to force use of the static factory method for instantiating an HttpClient.
     * @param id
     */
    private HttpClient(String id) {
        super(id);
    }

    /**
     * Factory method for instantiating an HttpClient.
     * @param id
     * @return HttpClient
     */
    public static HttpClient create(String id) {
        return new HttpClient(id);
    }
}