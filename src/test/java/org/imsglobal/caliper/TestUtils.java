package org.imsglobal.caliper;

import org.imsglobal.caliper.actions.ReadingActions;
import org.imsglobal.caliper.entities.ActivityContext;
import org.imsglobal.caliper.entities.lis.LisPerson;
import org.imsglobal.caliper.entities.reading.EpubVolume;
import org.imsglobal.caliper.entities.WebPage;
import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.events.NavigationEvent;

public class TestUtils {

    public static Options getTestingOptions() {

        String TESTING_HOST = "http://localhost:1080/1.0/event/put";
        String API_KEY = "6xp7jKrOSOWOgy3acxHFWA";

        Options options = new Options();
        options.setHost(TESTING_HOST);
        options.setApiKey(API_KEY);

        return options;

    }

    public static Event getTestNavigationEvent() {

        NavigationEvent event = NavigationEvent.builder()
            .actor(LisPerson.builder().id("uri:/someEdu/user/42").lastModifiedAt(0).build())
            .action(ReadingActions.NAVIGATED_TO.key())
            .object(ActivityContext.builder().id("uri:/someEdu/reading/42").lastModifiedAt(0).build())
            .target(EpubVolume.builder()
                .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3)")
                .name("The Glorious Cause: The American Revolution, 1763-1789 (Oxford History of the United States)")
                .lastModifiedAt(1402965614516l)
                .build())
            .startedAtTime(1402965614516l)
            .fromResource(WebPage.builder()
                .id("AmRev-101-landingPage")
                .name("American Revolution 101 Landing Page").build())
            .build();

        return event;
    }
}
