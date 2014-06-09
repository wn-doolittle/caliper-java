/**
 * 
 */
package org.imsglobal.caliper;

/**
 * @author pnayak
 *
 */
public class TestUtils {

	public static Options getTestingOptions() {
		
		String TESTING_HOST = "http://requestb.in/p6nc7zp6";
		String API_KEY = "6xp7jKrOSOWOgy3acxHFWA";

		Options options = new Options();
		options.setHost(TESTING_HOST);
		options.setApiKey(API_KEY);
		
		return options;
		
	}

}
