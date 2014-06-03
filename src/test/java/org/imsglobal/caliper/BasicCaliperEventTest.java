package org.imsglobal.caliper;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class BasicCaliperEventTest {

	Client client;

	@Test
	public void test() {

		String TESTING_HOST = "https://dev-null.org";
		String API_KEY = "6xp7jKrOSOWOgy3acxHFWA";

		// Initialize
		Options options = new Options();
		options.setHost(TESTING_HOST);
		Caliper.initialize(API_KEY, options);

		// TODO - Describes test goes here
		
		// Send five describes

		// There should be four describes queued
//		assertEquals("Expect five describes to be queued", 5, Caliper
//				.getStatistics().getDescribes().getCount());

		// TODO - Measure test goes here
		
		// Send two events

		// There should be two event queued
//		assertEquals("Expect two event to be queued", 2, Caliper
//				.getStatistics().getMeasures().getCount());

		// There should be seven message successfully sent
		int successes = Caliper.getStatistics().getSuccessful().getCount();
//		assertEquals("Expect seven messages to be successfuly sent", 7, successes);

		// There should be zero failures
		int failures = Caliper.getStatistics().getFailed().getCount();
//		assertEquals("Expect zero messages to fail to be sent", 0, failures);
	}
}
