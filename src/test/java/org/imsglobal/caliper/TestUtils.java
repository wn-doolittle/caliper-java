package org.imsglobal.caliper;

import com.google.common.collect.Iterables;
import org.imsglobal.caliper.actions.AnnotationActions;
import org.imsglobal.caliper.actions.ReadingActions;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.SoftwareApplication;
import org.imsglobal.caliper.entities.WebPage;
import org.imsglobal.caliper.entities.annotation.HighlightAnnotation;
import org.imsglobal.caliper.entities.lis.CourseSection;
import org.imsglobal.caliper.entities.lis.Person;
import org.imsglobal.caliper.entities.reading.EpubVolume;
import org.imsglobal.caliper.entities.reading.Frame;
import org.imsglobal.caliper.events.AnnotationEvent;
import org.imsglobal.caliper.events.NavigationEvent;
import org.imsglobal.caliper.events.ViewEvent;
import org.imsglobal.caliper.profiles.AnnotationProfile;
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

    public static LearningContext buildTestLearningContext() {

        LearningContext context = LearningContext.builder()
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
            .build();

        return context;
    }

    public static AnnotationProfile buildTestAnnotationProfile(LearningContext learningContext) {

        // Initialize profile with default values
        AnnotationProfile profile = AnnotationProfile.builder()
            .learningContext(learningContext)
            .build();

        return profile;
    }

    public static ReadingProfile buildTestReadingProfile(LearningContext learningContext) {

        // Initialize profile with default values
        ReadingProfile profile = ReadingProfile.builder()
            .learningContext(learningContext)
            .reading(EpubVolume.builder()
                    .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3)")
                    .name("The Glorious Cause: The American Revolution, 1763-1789 (Oxford History of the United States)")
                    .lastModifiedTime(1402965614516l)
                    .build())
            .build();

        return profile;
    }

    public static AnnotationProfile addTestHighlightAnnotation(AnnotationProfile profile) {

        profile.getActions().add(AnnotationActions.HIGHLIGHTED.key());
        profile.getAnnotations().add(HighlightAnnotation.builder()
            .id("https://someEduApp.edu/highlights/12345")
            .selectionStart(Integer.toString(455))
            .selectionEnd(Integer.toString(489))
            .selectionText("Life, Liberty and the pursuit of Happiness")
            .target(Frame.builder()
                .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3/1)")
                .name("Key Figures: George Washington")
                .partOf(EpubVolume.builder()
                    .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3)")
                    .name("The Glorious Cause: The American Revolution, 1763-1789 (Oxford History of the United States)")
                    .lastModifiedTime(1402965614516l)
                    .build())
                .lastModifiedTime(1402965614516l)
                .index(1)
                .build())  // TODO: REDUNDANT PROPERTY?
            .lastModifiedTime(1402965614516l)
            .build());
        profile.getTargets().add(Iterables.getLast(profile.getAnnotations()).getTarget());
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


    public static AnnotationEvent buildTestAnnotationEvent(AnnotationProfile profile) {

        // Build Annotation Event
        AnnotationEvent event = AnnotationEvent.builder()
            .edApp(profile.getLearningContext().getEdApp())
            .lisOrganization(profile.getLearningContext().getLisOrganization())
            .actor(profile.getLearningContext().getAgent())
            .action(Iterables.getLast(profile.getActions()))
            .object(Iterables.getLast(profile.getAnnotations()))
            .target(Iterables.getLast(profile.getTargets()))
            .startedAtTime(1402965614516l)
            .build();

        return event;
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
