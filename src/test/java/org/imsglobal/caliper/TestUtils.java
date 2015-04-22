package org.imsglobal.caliper;

/**
 * Test utilities
 */
public class TestUtils {

    /**
     * Constructor
     */
    public TestUtils() {

    }

    /**
     * @return test options
     */
    public static Options getTestingOptions() {
        String TESTING_HOST = "http://localhost:1080/1.0/event/put";
        String API_KEY = "6xp7jKrOSOWOgy3acxHFWA";

        Options options = new Options();
        options.setHost(TESTING_HOST);
        options.setApiKey(API_KEY);

        return options;
    }
}