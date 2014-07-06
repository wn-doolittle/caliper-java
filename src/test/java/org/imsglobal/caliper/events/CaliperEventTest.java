/**
 * 
 */
package org.imsglobal.caliper.events;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

import org.imsglobal.caliper.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pnayak
 * 
 */
@Category(org.imsglobal.caliper.UnitTest.class)
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
	public void caliperEventSerializesToJSON() throws Exception {

		assertEquals("Test if CaliperEvent can be serialized to JSON",
				jsonFixture("fixtures/caliperEvent.json"), asJson(caliperEvent));

	}
}