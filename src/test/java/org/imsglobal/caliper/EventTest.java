package org.imsglobal.caliper;

import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.reading.EpubSubChapter;
import org.imsglobal.caliper.entities.reading.EpubVolume;
import org.imsglobal.caliper.events.NavigationEvent;
import org.imsglobal.caliper.profiles.NavigationProfile;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventTest {

    private LearningContext learningContext;
    private EpubVolume epub;
    private String key;
    private DigitalResource fromResource;
    private EpubSubChapter target;
    private NavigationEvent event;

    @Test
    public void test() {

        Sensor.initialize(TestUtils.getTestingOptions());

        // Build the Learning Context
        learningContext = TestUtils.buildReadiumStudentLearningContext();

        // Build epub
        epub = TestUtils.buildEpubVolume43();

        // Build previous location
        fromResource = TestUtils.buildAmRev101LandingPage();

        // Build target
        target = TestUtils.buildEpubSubChap431();

        // Action
        key = NavigationProfile.Actions.NAVIGATED_TO.key();


        // Fire event test - Send 50 events
        for (int i = 0 ; i < 50 ; i++) {
            Sensor.send(TestUtils.buildEpubNavigationEvent(learningContext, epub, key, fromResource, target));
        }

        // There should be two caliperEvents queued
        assertEquals("Expect fifty Caliper events to be sent", 50,
            Sensor.getStatistics().getMeasures().getCount());

        // TODO - Describes test - Send five describes

        // There should be four describes queued assertEquals("Expect five describes to be sent", 5,
        // Caliper.getStatistics().getDescribes().getCount());

        // There should be two message successfully sent
        int successes = Sensor.getStatistics().getSuccessful().getCount();
        assertEquals("Expect fifty messages to be sent successfully", 50, successes);

        // There should be zero failures
        int failures = Sensor.getStatistics().getFailed().getCount();
        assertEquals("Expect zero message failures to be sent", 0, failures);
    }
}