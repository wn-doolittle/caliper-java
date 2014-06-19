/**
 * 
 */
package org.imsglobal.caliper.events;

import static org.junit.Assert.assertEquals;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.ActivityContext;
import org.imsglobal.caliper.entities.Agent;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * @author pnayak
 * 
 */
public class CaliperEventTest {

	private static final Logger LOG = LoggerFactory
			.getLogger(CaliperEventTest.class);

	private CaliperEvent caliperEvent;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		caliperEvent = TestUtils.getTestCaliperEvent();

	}

	@Test
	public void test() {

		String expectedCaliperEventJson = "{\"@context\":\"http://purl.imsglobal.org/ctx/caliper/v1/NavigationEvent\",\"@type\":\"NavigationEvent\",\"action\":\"navigate_to\",\"agent\":{\"@id\":\"uri:/someEdu/user/42\",\"@type\":\"agent\"},\"object\":{\"@id\":\"uri:/someEdu/reading/42\",\"@type\":\"reading\",\"startedAtTime\":1402965614516}";
		
		Gson gson = new Gson();

		String caliperEventJSON = gson.toJson(caliperEvent);

//		assertEquals("CaliperEvent JSON does not match expected JSON",
//				caliperEventJSON, expectedCaliperEventJson);

		// LOG.debug("Caliper Event JSON = " + caliperEventJSON);
	}
}
