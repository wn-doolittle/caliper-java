/**
 * 
 */
package org.imsglobal.caliper;

import org.imsglobal.caliper.entities.ActivityContext;
import org.imsglobal.caliper.entities.LISPerson;
import org.imsglobal.caliper.events.CaliperEvent;

/**
 * @author pnayak
 * 
 */
public class TestUtils {

	public static Options getTestingOptions() {

		String TESTING_HOST = "http://localhost:1080/1.0/event/put";
		String API_KEY = "6xp7jKrOSOWOgy3acxHFWA";

		Options options = new Options();
		options.setHost(TESTING_HOST);
		options.setApiKey(API_KEY);

		return options;

	}

	public static CaliperEvent getTestCaliperEvent() {

		CaliperEvent caliperEvent = new CaliperEvent();
		caliperEvent
				.setContext("http://purl.imsglobal.org/ctx/caliper/v1/NavigationEvent");
		caliperEvent.setType("NavigationEvent");
		caliperEvent.setAction("navigate_to");

		LISPerson actor = new LISPerson("uri:/someEdu/user/42");
		caliperEvent.setActor(actor);

		ActivityContext activityContext = new ActivityContext();
		activityContext.setId("uri:/someEdu/reading/42");
		activityContext.setType("reading"); // TODO fix
		caliperEvent.setObject(activityContext);

		caliperEvent.setStartedAt(1402965614516l);

		return caliperEvent;
	}
}
