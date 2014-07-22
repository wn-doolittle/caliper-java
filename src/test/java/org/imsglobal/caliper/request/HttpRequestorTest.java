package org.imsglobal.caliper.request;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

import org.apache.http.entity.StringEntity;
import org.imsglobal.caliper.Options;
import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.events.CaliperEvent;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test HttpRequestor
 * 
 * @author pnayak
 * @author yoganandp
 */
@Category(org.imsglobal.caliper.UnitTest.class)
public class HttpRequestorTest {

	private static final Logger LOG = LoggerFactory
			.getLogger(HttpRequestorTest.class);

	CaliperEvent caliperEvent;
	Options options;
	HttpRequestor httpRequestor;

	@Before
	public void setup() {

		caliperEvent = TestUtils.getTestCaliperEvent();

		httpRequestor = new HttpRequestor(TestUtils.getTestingOptions());
	}

	@Test
	public void testGeneratePayloadJson() throws Exception {

		String jsonPayload = null;

		jsonPayload = httpRequestor.getPayloadJson(caliperEvent,
				"caliper-java_fccffd9b-68d5-4183-b563-e22136aafaa3",
				DateTime.parse("2014-07-01T14:29:29.858-04:00"));
		
		LOG.debug("JSON payload: " + jsonPayload);

		assertEquals("Test HTTP Requestor payload JSON",
				jsonFixture("fixtures/eventStorePayload.json"), jsonPayload);

	}

	@Test
	public void testGeneratePayloadContentType() throws Exception {

		StringEntity payload = null;

		payload = httpRequestor.generatePayload(caliperEvent,
				"caliper-java_fccffd9b-68d5-4183-b563-e22136aafaa3",
				DateTime.parse("2014-07-01T14:29:29.858-04:00"));

		String expectedContentType = "Content-Type: application/json";
		assertEquals(expectedContentType, payload.getContentType().toString());
	}

	@After
	public void teardown() {
		caliperEvent = null;
		options = null;
	}

}