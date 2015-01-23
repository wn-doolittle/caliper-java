package org.imsglobal.caliper;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.*;
import org.imsglobal.caliper.entities.annotation.*;
import org.imsglobal.caliper.entities.assessment.Assessment;
import org.imsglobal.caliper.entities.assessment.AssessmentItem;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.lis.CourseSection;
import org.imsglobal.caliper.entities.lis.Person;
import org.imsglobal.caliper.entities.media.MediaLocation;
import org.imsglobal.caliper.entities.media.VideoObject;
import org.imsglobal.caliper.entities.outcome.Result;
import org.imsglobal.caliper.entities.reading.EpubSubChapter;
import org.imsglobal.caliper.entities.reading.EpubVolume;
import org.imsglobal.caliper.entities.reading.Frame;
import org.imsglobal.caliper.entities.Session;
import org.imsglobal.caliper.events.*;

public class TestUtils {

    /**
     * @return test options
     */
    public static Options getTestingOptions() {

        String TESTING_HOST = "http://localhost:1080/1.0/event/put";
        String API_KEY = "6xp7jKrOSOWOgy3acxHFWA";

        Options options = new Options();
        options.setHost(TESTING_HOST);
        options.setApiKey(API_KEY);

        return options;
    }

    /**
     * @return Am Rev 101 course section.
     */
    public static final CourseSection buildAmRev101CourseSection() {
        return CourseSection.builder()
            .id("https://some-university.edu/politicalScience/2014/american-revolution-101")
            .semester("Spring-2014")
            .courseNumber("AmRev-101")
            //.sectionNumber("001") TODO add section number property?
            .label("Am Rev 101")
            .name("American Revolution 101")
            .lastModifiedTime(1402965614516l)
            .build();
    }

    /**
     * Sample Am-Rev 101 landing page.
     * @return web page
     */
    public static final WebPage buildAmRev101LandingPage() {
        return WebPage.builder()
            .id("AmRev-101-landingPage")
            .name("American Revolution 101 Landing Page")
            .partOf(buildAmRev101CourseSection())
            .lastModifiedTime(1402965614516l)
            .build();
    }

    /**
     * @param learningContext
     * @param annotation
     * @param actionKey
     * @param target
     * @return annotation event.
     */
    public static AnnotationEvent buildAnnotationEvent(LearningContext learningContext,
                                                       Annotation annotation,
                                                       String actionKey,
                                                       DigitalResource target,
                                                       int index) {
        return AnnotationEvent.builder()
            .edApp(learningContext.getEdApp())
            .lisOrganization(learningContext.getLisOrganization())
            .actor(learningContext.getAgent())
            .action(actionKey)
            .object(annotation)
            .target(Frame.builder()
                    .id(target.getId())
                    .name(target.getName())
                    .partOf(target.getPartOf())
                    .lastModifiedTime(1402965614516l)
                    .index(index)
                    .build())
            .startedAtTime(1402965614516l)
            .build();
    }

    /**
     * Sample Assessment.
     * @return assessment
     */
    public static final Assessment buildAssessment() {
        return Assessment.builder()
            .id("https://some-university.edu/politicalScience/2014/american-revolution-101/assessment1")
            .name("American Revolution - Key Figures Assessment")
            .partOf("https://some-university.edu/politicalScience/2014/american-revolution-101")
            .dateCreated(1402965614516l)
            .datePublished(1402965614516l)
            .dateToActivate(1402965614516l)
            .dateToShow(1402965614516l)
            .dateToStartOn(1402965614516l)
            .dateToSubmit(1402965614516l)
            .maxAttempts(2)
            .maxSubmits(2)
            .maxScore(3) // WARN original value "5.0d"
            .assessmentItems(buildAssessmentItems())
            .lastModifiedTime(1402965614516l)
            .build();
    }

    /**
     * @param learningContext
     * @param assessment
     * @param actionKey
     * @return assignable event
     */
    public static AssignableEvent buildAssessmentAssignableEvent(LearningContext learningContext,
                                                                 Assessment assessment,
                                                                 String actionKey) {
        return AssignableEvent.builder()
            .edApp(learningContext.getEdApp())
            .lisOrganization(learningContext.getLisOrganization())
            .actor(learningContext.getAgent())
            .action(actionKey)
            .object(assessment)
            .generated(Attempt.builder()
                    .id(assessment.getId() + "/attempt1")
                    .assignable(assessment)
                    .actor(learningContext.getAgent())
                    .count(1)
                    .build())
            .startedAtTime(1402965614516l)
            .build();
    }

    /**
     * @param learningContext
     * @param assessment
     * @return assessment attempt.
     */
    public static Attempt buildAssessmentAttempt(LearningContext learningContext, Assessment assessment) {
        return Attempt.builder()
            .id(assessment.getId() + "/attempt1")
            .assignable(assessment)
            .actor(learningContext.getAgent())
            .count(1)
            .build();
    }

    /**
     * @param learningContext
     * @param assessment
     * @param actionKey
     * @param attempt
     * @return assessment event
     */
    public static AssessmentEvent buildAssessmentEvent(LearningContext learningContext,
                                                       Assessment assessment,
                                                       String actionKey,
                                                       Attempt attempt) {
        return AssessmentEvent.builder()
            .edApp(learningContext.getEdApp())
            .lisOrganization(learningContext.getLisOrganization())
            .actor(learningContext.getAgent())
            .action(actionKey)
            .object(assessment)
            .generated(attempt)
            .startedAtTime(1402965614516l)
            .build();
    }

    /**
     * @param learningContext
     * @param item
     * @param actionKey
     * @return assessment item event
     */
    public static AssessmentItemEvent buildAssessmentItemEvent(LearningContext learningContext,
                                                               AssessmentItem item,
                                                               String actionKey) {
        return AssessmentItemEvent.builder()
            .edApp(learningContext.getEdApp())
            .lisOrganization(learningContext.getLisOrganization())
            .actor(learningContext.getAgent())
            .action(actionKey)
            .object(item)
            //.generated(Response.builder() . . .) TODO:Add item response
            .startedAtTime(1402965614516l)
            .build();
    }

    /**
     * Sample assessment items
     * @return immutable list
     */
    public static final ImmutableList<AssessmentItem> buildAssessmentItems() {
        return ImmutableList.<AssessmentItem>builder()
            .add(AssessmentItem.builder()
                    .id("https://some-university.edu/politicalScience/2014/american-revolution-101/assessment1/item1")
                    .name("Assessment Item 1")
                    .partOf("https://some-university.edu/politicalScience/2014/american-revolution-101/assessment1")
                    .maxAttempts(2)
                    .maxSubmits(2)
                    .maxScore(1)
                    .build())
            .add(AssessmentItem.builder()
                    .id("https://some-university.edu/politicalScience/2014/american-revolution-101/assessment1/item2")
                    .name("Assessment Item 2")
                    .partOf("https://some-university.edu/politicalScience/2014/american-revolution-101/assessment1")
                    .maxAttempts(2)
                    .maxSubmits(2)
                    .maxScore(1)
                    .build())
            .add(AssessmentItem.builder()
                    .id("https://some-university.edu/politicalScience/2014/american-revolution-101/assessment1/item3")
                    .name("Assessment Item 3")
                    .partOf("https://some-university.edu/politicalScience/2014/american-revolution-101/assessment1")
                    .maxAttempts(2)
                    .maxSubmits(2)
                    .maxScore(1)
                    .build())
            .build();
    }

    /**
     * @param learningContext
     * @param attempt
     * @param actionKey
     * @param result
     * @return outcome event.
     */
    public static OutcomeEvent buildAssessmentOutcomeEvent(LearningContext learningContext,
                                                           Attempt attempt,
                                                           String actionKey,
                                                           Result result) {
        return OutcomeEvent.builder()
            .edApp(learningContext.getEdApp())
            .lisOrganization(learningContext.getLisOrganization())
            .actor(learningContext.getAgent())
            .action(actionKey)
            .object(attempt)
            .generated(result)
            .startedAtTime(1402965614516l)
            .build();
    }

    /**
     * @param attempt
     * @return assessment result.
     */
    public static Result buildAssessmentResult(Attempt attempt) {
        return Result.builder()
            .id(attempt.getId() + "/result")
            .lastModifiedTime(1402965614516l)
            .normalScore(3.0d)
            .penaltyScore(0.0d)
            .extraCreditScore(0.0d)
            .totalScore(3.0d)
            .curvedTotalScore(3.0d)
            .curveFactor(0.0d)
            .comment("Well done.")
            //.scoredBy(someAgent)  TODO add agent
            .build();
    }

    /**
     * Sample Assessment Tool learning context.
     * @return
     */
    public static final LearningContext buildAssessmentToolLearningContext() {
        return LearningContext.builder()
            .edApp(SoftwareApplication.builder()
                    .id("https://com.sat/super-assessment-tool")
                    .name("Super Assessment Tool")
                    .lastModifiedTime(1402965614516l)
                    .build())
            .lisOrganization(buildAmRev101CourseSection())
            .agent(buildStudent554433())
            .build();
    }

    /**
     * @param target
     * @return bookmark annotation.
     */
    public static BookmarkAnnotation buildBookmarkAnnotation(DigitalResource target)  {
        return BookmarkAnnotation.builder()
            .id("https://someEduApp.edu/bookmarks/00001")
            .bookmarkNotes("The Intolerable Acts (1774)--bad idea Lord North")
            .lastModifiedTime(1402965614516l)
            .target(target)
            .build();
    }

    /**
     * @param learningContext
     * @param edApp
     * @param actionKey
     * @param target
     * @param generated
     * @return session event
     */
    public static SessionEvent buildEpubLoginEvent(LearningContext learningContext,
                                                           SoftwareApplication edApp,
                                                           String actionKey,
                                                           EpubSubChapter target,
                                                           Session generated) {
        return SessionEvent.builder()
            .edApp(learningContext.getEdApp())
            .lisOrganization(learningContext.getLisOrganization())
            .actor(learningContext.getAgent())
            .action(actionKey)
            .object(edApp)
            .target(Frame.builder()
                    .id(target.getId())
                    .name(target.getName())
                    .partOf(target.getPartOf())
                    .lastModifiedTime(1402965614516l)
                    .index(1)
                    .build())
            .generated(generated)
            .startedAtTime(1402965614516l)
            .build();
    }

    /**
     * @param learningContext
     * @param epub
     * @param actionKey
     * @param fromResource
     * @param target
     * @return navigation event
     */
    public static NavigationEvent buildEpubNavigationEvent(LearningContext learningContext,
                                                           DigitalResource epub,
                                                           String actionKey,
                                                           DigitalResource fromResource,
                                                           EpubSubChapter target) {
        return NavigationEvent.builder()
            .edApp(learningContext.getEdApp())
            .lisOrganization(learningContext.getLisOrganization())
            .actor(learningContext.getAgent())
            .action(actionKey)
            .object(epub)
            .target(Frame.builder()
                    .id(target.getId())
                    .name(target.getName())
                    .partOf(epub)
                    .lastModifiedTime(1402965614516l)
                    .index(1)
                    .build())
            .fromResource(fromResource)
            .startedAtTime(1402965614516l)
            .build();
    }

    /**
     * Sample EPUB fragment.
     * @return digital resource
     */
    public static final EpubVolume buildEpubVolume43() {
        return EpubVolume.builder()
            .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3)")
            .name("The Glorious Cause: The American Revolution, 1763-1789 (Oxford History of the United States)")
            .lastModifiedTime(1402965614516l)
            .build();
    }

    /**
     * Sample EPUB fragment.
     * @return digital resource
     */
    public static final EpubSubChapter buildEpubSubChap431() {
        return EpubSubChapter.builder()
            .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3/1)")
            .name("Key Figures: George Washington")
            .partOf(buildEpubVolume43())
            .lastModifiedTime(1402965614516l)
            .build();
    }

    /**
     * Sample EPUB fragment.
     * @return digital resource
     */
    public static final EpubSubChapter buildEpubSubChap432() {
        return EpubSubChapter.builder()
            .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3/2)")
            .name("Key Figures: Lord North")
            .lastModifiedTime(1402965614516l)
            .partOf(buildEpubVolume43())
            .build();
    }

    /**
     * Sample EPUB fragment.
     * @return digital resource
     */
    public static final EpubSubChapter buildEpubSubChap433() {
        return EpubSubChapter.builder()
            .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3/3)")
            .name("Key Figures: John Adams")
            .lastModifiedTime(1402965614516l)
            .partOf(buildEpubVolume43())
            .build();
    }

    /**
     * Sample EPUB fragment.
     * @return digital resource
     */
    public static final EpubSubChapter buildEpubSubChap434() {
        return EpubSubChapter.builder()
            .id("https://github.com/readium/readium-js-viewer/book/34843#epubcfi(/4/3/4)")
            .name("The Stamp Act Crisis")
            .partOf(buildEpubVolume43())
            .lastModifiedTime(1402965614516l)
            .build();
    }

    /**
     * @param learningContext
     * @param epub
     * @param actionKey
     * @param target
     * @return EPUB view event
     */
    public static ViewEvent buildEpubViewEvent(LearningContext learningContext,
                                               DigitalResource epub,
                                               String actionKey,
                                               DigitalResource target) {
        return ViewEvent.builder()
            .edApp(learningContext.getEdApp())
            .lisOrganization(learningContext.getLisOrganization())
            .actor(learningContext.getAgent())
            .action(actionKey)
            .object(epub)
            .target(Frame.builder()
                    .id(target.getId())
                    .name(target.getName())
                    .partOf(epub)
                    .lastModifiedTime(1402965614516l)
                    .index(1)
                    .build())
            .startedAtTime(1402965614516l)
            .build();
    }

    /**
     * @param target
     * @return highlight annotation.
     */
    public static HighlightAnnotation buildHighlightAnnotation(DigitalResource target) {
        return HighlightAnnotation.builder()
            .id("https://someEduApp.edu/highlights/12345")
            .selectionStart(Integer.toString(455))
            .selectionEnd(Integer.toString(489))
            .selectionText("Life, Liberty and the pursuit of Happiness")
            .target(target)
            .lastModifiedTime(1402965614516l)
            .build();
    }

    /**
     * @return Readium Viewer learning context
     */
    public static LearningContext buildReadiumLearningContext() {

        return LearningContext.builder()
            .edApp(SoftwareApplication.builder()
                    .id("https://github.com/readium/readium-js-viewer")
                    .name("Readium")
                    .lastModifiedTime(1402965614516l)
                    .build())
            .lisOrganization(buildAmRev101CourseSection())
            .agent(buildStudent554433())
            .build();
    }

    /**
     * @return Session
     */
    public static Session buildSession() {
        return Session.builder()
            .id("https://github.com/readium/session-123456789")
            .name("session-123456789")
            .actor(buildStudent554433())
            .lastModifiedTime(1402965614516l)
            .startedAtTime(1402965614516l)
            .build();
    }

    /**
     * @param target
     * @return shared annotation.
     */
    public static SharedAnnotation buildSharedAnnotation(DigitalResource target) {
        return SharedAnnotation.builder()
            .id("https://someEduApp.edu/shared/9999")
            .withAgents(Lists.<Agent>newArrayList(
                Person.builder()
                    .id("https://some-university.edu/students/657585")
                    .lastModifiedTime(1402965614516l)
                    .build(),
                Person.builder()
                    .id("https://some-university.edu/students/667788")
                    .lastModifiedTime(1402965614516l)
                    .build()))
            .lastModifiedTime(1402965614516l)
            .build();
    }

    /**
     * Sample student.
     * @return agent
     */
    public static final Agent buildStudent554433() {
        return Person.builder()
            .id("https://some-university.edu/user/554433")
            .lastModifiedTime(1402965614516l)
            .build();
    }

    /**
     * Sample Media Tool learning context composed of an edApp (Media Tool), organization (AmRev-101) and agent
     * @return learning context
     */
    public static final LearningContext buildSuperMediaToolLearningContext() {
        return LearningContext.builder()
            .edApp(SoftwareApplication.builder()
                .id("https://com.sat/super-media-tool")
                .name("Super Media Tool")
                .lastModifiedTime(1402965614516l)
                .build())
            .lisOrganization(buildAmRev101CourseSection())
            .agent(buildStudent554433())
            .build();
    }

    /**
     * @param target
     * @return tag annotation.
     */
    public static TagAnnotation buildTagAnnotation(DigitalResource target) {
        return TagAnnotation.builder()
            .id("https://someEduApp.edu/tags/7654")
            .tags(Lists.newArrayList("to-read", "1765", "shared-with-project-team"))
            .target(target)
            .lastModifiedTime(1402965614516l)
            .build();
    }

    /**
     * @param learningContext
     * @param video
     * @param location
     * @param actionKey
     * @return Video media event
     */
    public static MediaEvent buildVideoMediaEvent(LearningContext learningContext,
                                                  VideoObject video,
                                                  MediaLocation location,
                                                  String actionKey) {
        return MediaEvent.builder()
            .edApp(learningContext.getEdApp())
            .lisOrganization(learningContext.getLisOrganization())
            .actor(learningContext.getAgent())
            .object(video)
            .action(actionKey)
            .target(location)
            .startedAtTime(1402965614516l)
            .build();
    }

    /**
     * @return media location.
     */
    public static final MediaLocation buildVideoMediaLocation() {
        return MediaLocation.builder()
            .id(buildVideoWithLearningObjective().getId())
            .currentTime(710)
            .build();
    }

    /**
     * Sample Video with learning objective.
     * @return video
     */
    public static final VideoObject buildVideoWithLearningObjective() {
        return VideoObject.builder()
            .id("https://com.sat/super-media-tool/video/video1")
            .name("American Revolution - Key Figures Video")
            .learningObjective(LearningObjective.builder()
                .id("http://americanrevolution.com/personalities/learn")
                .build())
            .duration(1420)
            .lastModifiedTime(1402965614516l)
            .build();
    }
}