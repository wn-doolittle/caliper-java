package org.imsglobal.caliper;

import com.google.common.collect.Iterables;
import org.imsglobal.caliper.actions.ReadingActions;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.SoftwareApplication;
import org.imsglobal.caliper.entities.WebPage;
import org.imsglobal.caliper.entities.lis.CourseSection;
import org.imsglobal.caliper.entities.lis.Person;
import org.imsglobal.caliper.entities.reading.EpubVolume;
import org.imsglobal.caliper.entities.reading.Frame;
import org.imsglobal.caliper.events.NavigationEvent;
import org.imsglobal.caliper.events.ViewEvent;
import org.imsglobal.caliper.profiles.ReadingProfile;

public class TestUtils {

    public static Options getTestingOptions() {

        String TESTING_HOST = "http://localhost:1080/1.0/event/put";
        String API_KEY = "6xp7jKrOSOWOgy3acxHFWA";

        Options options = new Options();
        options.setHost(TESTING_HOST);
        options.setApiKey(API_KEY);

        return options;
    }

    public static ReadingProfile buildTestReadingProfile() {

        // Initialize profile with default values
        ReadingProfile profile = ReadingProfile.builder()
            .learningContext(LearningContext.builder()
                .edApp(SoftwareApplication.builder()
                    .id("https://github.com/readium/readium-js-viewer")
                    .name("Readium")
                    .lastModifiedTime(1402965614516l)
                    .build())
                .lisOrganization(CourseSection.builder()
                    .id("https://some-university.edu/politicalScience/2014/american-revolution-101")
                    .semester("Spring-2014")
                    .courseNumber("AmRev-101")
                    .label("Am Rev 101")
                    .name("American Revolution 101")
                    .lastModifiedTime(1402965614516l)
                    .build())
                .agent(Person.builder()
                    .id("https://some-university.edu/user/554433")
                    .lastModifiedTime(1402965614516l)
                    .build())
                .build())
            .reading(EpubVolume.builder()
                .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3)")
                .name("The Glorious Cause: The American Revolution, 1763-1789 (Oxford History of the United States)")
                .lastModifiedTime(1402965614516l)
                .build())
            .build();

        return profile;
    }

    public static ReadingProfile addTestReadingProfileNavigationTarget(ReadingProfile profile) {

        // Add navigation-related properties to profile
        profile.getActions().add(ReadingActions.NAVIGATED_TO.key());
        profile.getTargets().add(Frame.builder()
            .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3/1)")
            .name("Key Figures: George Washington")
            .partOf(profile.getReading())
            .lastModifiedTime(profile.getReading().getLastModifiedTime())
            .index(1)
            .build());
        profile.getFromResources().add(WebPage.builder()
            .id("AmRev-101-landingPage")
            .name("American Revolution 101 Landing Page")
            .partOf(profile.getLearningContext().getLisOrganization())
            .lastModifiedTime(profile.getLearningContext().getLisOrganization().getLastModifiedTime())
            .build());

        return profile;
    }

    public static ReadingProfile addTestReadingProfileViewTarget(ReadingProfile profile) {

        // Add navigation-related properties to profile
        profile.getActions().add(ReadingActions.VIEWED.key());
        profile.getTargets().add(Frame.builder()
            .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3/1)")
            .name("Key Figures: George Washington")
            .partOf(profile.getReading())
            .lastModifiedTime(profile.getReading().getLastModifiedTime())
            .index(1)
            .build());

        return profile;
    }

    public static NavigationEvent buildTestNavigationEvent(ReadingProfile profile) {

        // Build NavigationEvent
        NavigationEvent event = NavigationEvent.builder()
            .edApp(profile.getLearningContext().getEdApp())
            .lisOrganization(profile.getLearningContext().getLisOrganization())
            .actor(profile.getLearningContext().getAgent())
            .action(Iterables.getLast(profile.getActions()))
            .object(profile.getReading())
            .target(Iterables.getLast(profile.getTargets()))
            .fromResource(Iterables.getLast(profile.getFromResources()))
            .startedAtTime(1402965614516l)
            .build();

        return event;
    }

    public static ViewEvent buildTestViewEvent(ReadingProfile profile) {

        // Build NavigationEvent
        ViewEvent event = ViewEvent.builder()
            .edApp(profile.getLearningContext().getEdApp())
            .lisOrganization(profile.getLearningContext().getLisOrganization())
            .actor(profile.getLearningContext().getAgent())
            .action(Iterables.getLast(profile.getActions()))
            .object(profile.getReading())
            .target(Iterables.getLast(profile.getTargets()))
            .startedAtTime(1402965614516l)
            .build();

        return event;
    }
}
