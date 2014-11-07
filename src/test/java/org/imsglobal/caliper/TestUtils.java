package org.imsglobal.caliper;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.actions.AnnotationActions;
import org.imsglobal.caliper.actions.MediaActions;
import org.imsglobal.caliper.actions.ReadingActions;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.LearningObjective;
import org.imsglobal.caliper.entities.SoftwareApplication;
import org.imsglobal.caliper.entities.WebPage;
import org.imsglobal.caliper.entities.annotation.BookmarkAnnotation;
import org.imsglobal.caliper.entities.annotation.HighlightAnnotation;
import org.imsglobal.caliper.entities.annotation.SharedAnnotation;
import org.imsglobal.caliper.entities.annotation.TagAnnotation;
import org.imsglobal.caliper.entities.lis.CourseSection;
import org.imsglobal.caliper.entities.lis.Person;
import org.imsglobal.caliper.entities.media.MediaLocation;
import org.imsglobal.caliper.entities.media.VideoObject;
import org.imsglobal.caliper.entities.reading.EpubVolume;
import org.imsglobal.caliper.entities.reading.Frame;
import org.imsglobal.caliper.events.AnnotationEvent;
import org.imsglobal.caliper.events.MediaEvent;
import org.imsglobal.caliper.events.NavigationEvent;
import org.imsglobal.caliper.events.ViewEvent;
import org.imsglobal.caliper.profiles.AnnotationProfile;
import org.imsglobal.caliper.profiles.MediaProfile;
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

    public static MediaProfile buildTestVideoMediaProfile(LearningContext learningContext) {

        // Initialize profile with default values
        MediaProfile profile = MediaProfile.builder()
            .learningContext(learningContext)
            .mediaObject(VideoObject.builder()
                .id("https://com.sat/super-media-tool/video/video1")
                .name("American Revolution - Key Figures Video")
                .learningObjective(LearningObjective.builder()
                    .id("http://americanrevolution.com/personalities/learn")
                    .build())
                .duration(1420)
                .lastModifiedTime(1402965614516l)
                .build())
            .mediaLocation(MediaLocation.builder()
                .id("https://com.sat/super-media-tool/video/video1")
                .currentTime(0)
                .build())
            .build();

        return profile;
    }

    public static AnnotationProfile addTestBookmarkAnnotation(AnnotationProfile profile) {

        profile.getActions().add(AnnotationActions.BOOKMARKED.key());
        profile.getAnnotations().add(BookmarkAnnotation.builder()
            .id("https://someEduApp.edu/bookmarks/00001")
            .bookmarkNotes("The Intolerable Acts (1774)--bad idea Lord North")
            .target(Frame.builder()
                .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3/2)")
                .name("Key Figures: Lord North")
                .partOf(EpubVolume.builder()
                    .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3)")
                    .name("The Glorious Cause: The American Revolution, 1763-1789 (Oxford History of the United States)")
                    .lastModifiedTime(1402965614516l)
                    .build())
                .lastModifiedTime(1402965614516l)
                .index(2)
                .build())  // TODO: REDUNDANT PROPERTY?
            .lastModifiedTime(1402965614516l)
            .build());
        profile.getTargets().add(Iterables.getLast(profile.getAnnotations()).getTarget());

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

    public static AnnotationProfile addTestSharedAnnotation(AnnotationProfile profile) {

        profile.getActions().add(AnnotationActions.SHARED.key());
        profile.getAnnotations().add(SharedAnnotation.builder()
            .id("https://someEduApp.edu/shared/9999")
            .withAgents(Lists.newArrayList(
                "https://some-university.edu/students/657585",
                "https://some-university.edu/students/667788"))
            .target(Frame.builder()
                .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3/3)")
                .name("Key Figures: John Adams")
                .partOf(EpubVolume.builder()
                        .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3)")
                        .name("The Glorious Cause: The American Revolution, 1763-1789 (Oxford History of the United States)")
                        .lastModifiedTime(1402965614516l)
                        .build())
                .lastModifiedTime(1402965614516l)
                .index(3)
                .build())  // TODO: REDUNDANT PROPERTY?
            .lastModifiedTime(1402965614516l)
            .build());
        profile.getTargets().add(Iterables.getLast(profile.getAnnotations()).getTarget());

        return profile;
    }

    public static AnnotationProfile addTestTagAnnotation(AnnotationProfile profile) {

        profile.getActions().add(AnnotationActions.SHARED.key());
        profile.getAnnotations().add(TagAnnotation.builder()
            .id("https://someEduApp.edu/tags/7654")
            .tags(Lists.newArrayList("to-read", "1765", "shared-with-project-team"))
            .target(Frame.builder()
                .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3/4)")
                .name("The Stamp Act Crisis")
                .partOf(EpubVolume.builder()
                        .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3)")
                        .name("The Glorious Cause: The American Revolution, 1763-1789 (Oxford History of the United States)")
                        .lastModifiedTime(1402965614516l)
                        .build())
                .lastModifiedTime(1402965614516l)
                .index(4)
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

    public static MediaProfile pauseVideo(MediaProfile profile) {

        profile.getActions().add(MediaActions.PAUSED.key());
        profile.getMediaLocations().add(MediaLocation.builder()
            .id(((VideoObject) profile.getMediaObject()).getId())
            .currentTime(710)
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

    public static MediaEvent buildTestMediaEvent(MediaProfile profile) {

        MediaEvent event = MediaEvent.builder()
            .edApp(profile.getLearningContext().getEdApp())
            .lisOrganization(profile.getLearningContext().getLisOrganization())
            .actor(profile.getLearningContext().getAgent())
            .action(Iterables.getLast(profile.getActions()))
            .object(profile.getMediaObject())
            .mediaLocation(Iterables.getLast(profile.getMediaLocations()))
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
