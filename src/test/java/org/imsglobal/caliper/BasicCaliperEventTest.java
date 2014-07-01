package org.imsglobal.caliper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BasicCaliperEventTest {

	@Test
	public void test() {

		Caliper.initialize(TestUtils.getTestingOptions());

		// Measure test - Send two events
		Caliper.measure(TestUtils.getTestCaliperEvent());
		Caliper.measure(TestUtils.getTestCaliperEvent());

		// There should be two caliperEvents queued
		assertEquals("Expect two caliperEvent to be sent", 2, Caliper
				.getStatistics().getMeasures().getCount());

		// TODO - Describes test - Send five describes

		// There should be four describes queued
		// assertEquals("Expect five describes to be sent", 5, Caliper
		// .getStatistics().getDescribes().getCount());

		// There should be two message successfully sent
		int successes = Caliper.getStatistics().getSuccessful().getCount();
		assertEquals("Expect two messages to be successfuly sent", 2, successes);

		// There should be zero failures
		int failures = Caliper.getStatistics().getFailed().getCount();
		assertEquals("Expect zero messages to fail to be sent", 0, failures);
	}
}
