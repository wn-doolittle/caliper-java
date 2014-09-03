package org.imsglobal.caliper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BasicCaliperEventTest {

	@Test
	public void test() {

		CaliperSensor.initialize(TestUtils.getTestingOptions());

		// Measure test - Send 50 events
		for (int i = 0 ; i < 50 ; i++) {
			CaliperSensor.measure(TestUtils.getTestCaliperEvent());
		}

		// There should be two caliperEvents queued
		assertEquals("Expect fifty caliperEvent to be sent", 50,
                CaliperSensor.getStatistics().getMeasures().getCount());

		// TODO - Describes test - Send five describes

		// There should be four describes queued
		// assertEquals("Expect five describes to be sent", 5, Caliper
		// .getStatistics().getDescribes().getCount());

		// There should be two message successfully sent
		int successes = CaliperSensor.getStatistics().getSuccessful().getCount();
		assertEquals("Expect fifty messages to be successfully sent", 50, successes);

		// There should be zero failures
		int failures = CaliperSensor.getStatistics().getFailed().getCount();
		assertEquals("Expect zero message failures to be sent", 0, failures);
	}
}
