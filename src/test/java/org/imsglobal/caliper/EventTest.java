package org.imsglobal.caliper;

import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.profiles.ReadingProfile;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventTest {

    @Test
    public void test() {

        Sensor.initialize(TestUtils.getTestingOptions());

        // Build the Learning Context
        LearningContext learningContext = TestUtils.buildTestLearningContext();

        // Build Reading Profile
        ReadingProfile profile = TestUtils.buildTestReadingProfile(learningContext);

        // Add navigation-related properties to profile
        profile = TestUtils.addTestReadingProfileNavigationTarget(profile);

        // Fire event test - Send 50 events
        for (int i = 0 ; i < 50 ; i++) {
            Sensor.send(TestUtils.buildTestNavigationEvent(profile));
        }

        // There should be two caliperEvents queued
        assertEquals("Expect fifty Caliper events to be sent", 50,
            Sensor.getStatistics().getMeasures().getCount());

        // TODO - Describes test - Send five describes

        // There should be four describes queued
        // assertEquals("Expect five describes to be sent", 5, Caliper
        // .getStatistics().getDescribes().getCount());

        // There should be two message successfully sent
        int successes = Sensor.getStatistics().getSuccessful().getCount();
        assertEquals("Expect fifty messages to be sent successfully", 50, successes);

        // There should be zero failures
        int failures = Sensor.getStatistics().getFailed().getCount();
        assertEquals("Expect zero message failures to be sent", 0, failures);
    }
}