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
import org.imsglobal.caliper.events.*;
import org.imsglobal.caliper.response.FillinBlankResponse;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class TestUtils {

    /**
     * Constructor
     */
    public TestUtils() {

    }

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
            .dateCreated(getDefaultDateCreated())
            .dateModified(getDefaultDateModified())
            .build();
    }

    /**
     * Sample Am-Rev 101 landing page.
     * @return web page
     */
    public static final WebPage buildAmRev101LandingPage() {
        return WebPage.builder()
            .id("https://some-university.edu/politicalScience/2014/american-revolution-101/index.html")
            .name("American Revolution 101 Landing Page")
            .isPartOf(buildAmRev101CourseSection())
            .dateCreated(getDefaultDateCreated())
            .dateModified(getDefaultDateModified())
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
            .actor((Person) learningContext.getAgent())
            .action(actionKey)
            .object(annotation)
            .target(Frame.builder()
                .id(target.getId())
                .name(target.getName())
                .isPartOf(target.getIsPartOf())
                .dateCreated(getDefaultDateCreated())
                .dateModified(getDefaultDateModified())
                .index(index)
                .build())
            .startedAtTime(getDefaultStartedAtTime())
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
            .isPartOf("https://some-university.edu/politicalScience/2014/american-revolution-101")
            .dateCreated(getDefaultDateModified())
            .datePublished(getDefaultDatePublished())
            .dateToActivate(getDefaultDateToActivate())
            .dateToShow(getDefaultDateToShow())
            .dateToStartOn(getDefaultDateToStartOn())
            .dateToSubmit(getDefaultDateToSubmit())
            .maxAttempts(2)
            .maxSubmits(2)
            .maxScore(3) // WARN original value "5.0d"
            .assessmentItems(buildAssessmentItems())
            .dateCreated(getDefaultDateCreated())
            .dateModified(getDefaultDateModified())
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
            .actor((Person) learningContext.getAgent())
            .action(actionKey)
            .object(assessment)
            .generated(Attempt.builder()
                    .id(assessment.getId() + "/attempt1")
                    .assignableId(assessment.getId())
                    .actorId(((Person) learningContext.getAgent()).getId())
                    .count(1)
                    .dateCreated(getDefaultDateCreated())
                    .startedAtTime(getDefaultStartedAtTime())
                    .build())
            .startedAtTime(getDefaultStartedAtTime())
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
            .assignableId(assessment.getId())
            .actorId(((Person) learningContext.getAgent()).getId())
            .count(1)
            .dateCreated(getDefaultDateCreated())
            .startedAtTime(getDefaultStartedAtTime())
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
            .actor((Person) learningContext.getAgent())
            .action(actionKey)
            .object(assessment)
            .generated(attempt)
            .startedAtTime(getDefaultStartedAtTime())
            .build();
    }

    /**
     * @param learningContext
     * @param assessment
     * @return assessment attempt.
     */
    public static Attempt buildAssessmentItemAttempt(LearningContext learningContext, Assessment assessment) {
        return Attempt.builder()
            .id(assessment.getId() + "/item1/attempt1")
            .assignableId(assessment.getId())
            .actorId(((Person) learningContext.getAgent()).getId())
            .count(1)
            .dateCreated(getDefaultDateCreated())
            .startedAtTime(getDefaultStartedAtTime())
            .build();
    }

    /**
     * @param learningContext
     * @param item
     * @param actionKey
     * @return assessment item event
     */
    public static AssessmentItemEvent buildAssessmentItemStartedEvent(LearningContext learningContext,
                                                               AssessmentItem item,
                                                               String actionKey,
                                                               Attempt attempt) {
        return AssessmentItemEvent.builder()
            .edApp(learningContext.getEdApp())
            .lisOrganization(learningContext.getLisOrganization())
            .actor((Person) learningContext.getAgent())
            .action(actionKey)
            .object(item)
            .generated(attempt)
            .startedAtTime(getDefaultStartedAtTime())
            .build();
    }

    /**
     * @param learningContext
     * @param item
     * @param actionKey
     * @param response
     * @retur item event
     */
    public static AssessmentItemEvent buildFillinBlankCompletedEvent(LearningContext learningContext,
                                                                     AssessmentItem item,
                                                                     String actionKey,
                                                                     FillinBlankResponse response) {
        return AssessmentItemEvent.builder()
            .edApp(learningContext.getEdApp())
            .lisOrganization(learningContext.getLisOrganization())
            .actor((Person) learningContext.getAgent())
            .action(actionKey)
            .object(item)
            .generated(response)
            .startedAtTime(getDefaultStartedAtTime())
            .build();
    }

    /**
     * @param attempt
     * @param value
     * @return response
     */
    public static FillinBlankResponse buildFillinBlankResponse(Attempt attempt, String value) {
        return FillinBlankResponse.builder()
            .id("https://some-university.edu/politicalScience/2014/american-revolution-101/assessment1/item1/response1")
            .actorId(attempt.getActorId())
            .assignableId(attempt.getAssignableId())
            .attempt(attempt)
            .dateCreated(getDefaultDateCreated())
            .startedAtTime(getDefaultStartedAtTime())
            .value(value)
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
                .isPartOf("https://some-university.edu/politicalScience/2014/american-revolution-101/assessment1")
                .maxAttempts(2)
                .maxSubmits(2)
                .maxScore(1)
                .isTimeDependent(false)
                .build())
            .add(AssessmentItem.builder()
                .id("https://some-university.edu/politicalScience/2014/american-revolution-101/assessment1/item2")
                .name("Assessment Item 2")
                .isPartOf("https://some-university.edu/politicalScience/2014/american-revolution-101/assessment1")
                .maxAttempts(2)
                .maxSubmits(2)
                .maxScore(1)
                .isTimeDependent(false)
                .build())
            .add(AssessmentItem.builder()
                .id("https://some-university.edu/politicalScience/2014/american-revolution-101/assessment1/item3")
                .name("Assessment Item 3")
                .isPartOf("https://some-university.edu/politicalScience/2014/american-revolution-101/assessment1")
                .maxAttempts(2)
                .maxSubmits(2)
                .maxScore(1)
                .isTimeDependent(false)
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
            .startedAtTime(getDefaultStartedAtTime())
            .build();
    }

    /**
     * @param attempt
     * @return assessment result.
     */
    public static Result buildAssessmentResult(Attempt attempt, Agent scoredBy) {
        return Result.builder()
            .id(attempt.getId() + "/result")
            .assignableId(attempt.getAssignableId())
            .actorId(attempt.getActorId())
            .dateCreated(getDefaultDateCreated())
            .normalScore(3.0d)
            .penaltyScore(0.0d)
            .extraCreditScore(0.0d)
            .totalScore(3.0d)
            .curvedTotalScore(3.0d)
            .curveFactor(0.0d)
            .comment("Well done.")
            .scoredBy((SoftwareApplication) scoredBy)
            .build();
    }

    /**
     * Build assessment tool.
     */
    public static SoftwareApplication buildAssessmentTool() {
        return SoftwareApplication.builder()
            .id("https://com.sat/super-assessment-tool")
            .name("Super Assessment Tool")
            .dateCreated(getDefaultDateCreated())
            .build();
    }

    /**
     * Sample Assessment Tool learning context where the actor is a student.
     * @return
     */
    public static final LearningContext buildAssessmentStudentLearningContext() {
        return LearningContext.builder()
            .edApp(buildAssessmentTool())
            .lisOrganization(buildAmRev101CourseSection())
            .agent(buildStudent554433())
            .build();
    }

    /**
     * Sample Assessment Tool learning context where the actor is the app itself.
     * @return
     */
    public static final LearningContext buildAssessmentToolLearningContext() {
        return LearningContext.builder()
            .edApp(buildAssessmentTool())
            .lisOrganization(buildAmRev101CourseSection())
            .agent(buildAssessmentTool())
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
            .dateCreated(getDefaultDateCreated())
            .dateModified(getDefaultDateModified())
            .target(target)
            .build();
    }

    /**
     * @param learningContext
     * @param actionKey
     * @param target
     * @param generated
     * @return session event
     */
    public static SessionEvent buildEpubLoginEvent(LearningContext learningContext,
                                                           String actionKey,
                                                           EpubSubChapter target,
                                                           Session generated) {
        return SessionEvent.builder()
            .edApp(learningContext.getEdApp())
            .lisOrganization(learningContext.getLisOrganization())
            .actor((Person) learningContext.getAgent())
            .action(actionKey)
            .object(learningContext.getEdApp())
            .target(Frame.builder()
                .id(target.getId())
                .name(target.getName())
                .isPartOf(target.getIsPartOf())
                .dateCreated(getDefaultDateCreated())
                .dateModified(getDefaultDateModified())
                .index(1)
                .build())
            .generated(generated)
            .startedAtTime(getDefaultStartedAtTime())
            .build();
    }

    /**
     * @param learningContext
     * @param actionKey
     * @param target
     * @return session event
     */
    public static SessionEvent buildEpubLogoutEvent(LearningContext learningContext,
                                                   String actionKey,
                                                   Session target) {
        return SessionEvent.builder()
            .edApp(learningContext.getEdApp())
            .lisOrganization(learningContext.getLisOrganization())
            .actor((Person) learningContext.getAgent())
            .action(actionKey)
            .object(learningContext.getEdApp())
            .target(target)
            .startedAtTime(getDefaultStartedAtTime())
            .endedAtTime(getDefaultEndedAtTime())
            .duration("PT3000S")
            .build();
    }

    /**
     * @param learningContext
     * @param actionKey
     * @param target
     * @return session event
     */
    public static SessionEvent buildEpubTimeoutEvent(LearningContext learningContext,
                                                    String actionKey,
                                                    Session target) {
        return SessionEvent.builder()
            .edApp(learningContext.getEdApp())
            .lisOrganization(learningContext.getLisOrganization())
            .actor((SoftwareApplication) learningContext.getEdApp())
            .action(actionKey)
            .object(learningContext.getEdApp())
            .target(target)
            .startedAtTime(getDefaultStartedAtTime())
            .endedAtTime(getDefaultEndedAtTime())
            .duration("PT3000S")
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
            .actor((Person) learningContext.getAgent())
            .action(actionKey)
            .object(epub)
            .target(Frame.builder()
                .id(target.getId())
                .name(target.getName())
                .isPartOf(epub)
                .dateCreated(getDefaultDateCreated())
                .dateModified(getDefaultDateModified())
                .index(1)
                .build())
            .fromResource(fromResource)
                .startedAtTime(getDefaultStartedAtTime())
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
            .dateCreated(getDefaultDateCreated())
            .dateModified(getDefaultDateModified())
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
            .isPartOf(buildEpubVolume43())
            .dateCreated(getDefaultDateCreated())
            .dateModified(getDefaultDateModified())
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
            .dateCreated(getDefaultDateCreated())
            .dateModified(getDefaultDateModified())
            .isPartOf(buildEpubVolume43())
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
            .dateCreated(getDefaultDateCreated())
            .dateModified(getDefaultDateModified())
            .isPartOf(buildEpubVolume43())
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
            .isPartOf(buildEpubVolume43())
            .dateCreated(getDefaultDateCreated())
            .dateModified(getDefaultDateModified())
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
            .actor((Person) learningContext.getAgent())
            .action(actionKey)
            .object(epub)
            .target(Frame.builder()
                .id(target.getId())
                .name(target.getName())
                .isPartOf(epub)
                .dateCreated(getDefaultDateCreated())
                .dateModified(getDefaultDateModified())
                .index(1)
                .build())
                .startedAtTime(getDefaultStartedAtTime())
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
            .dateCreated(getDefaultDateCreated())
            .dateModified(getDefaultDateModified())
            .build();
    }

    /**
     * Build Readium App
     * @return
     */
    public static SoftwareApplication buildReadiumApp() {
        return SoftwareApplication.builder()
            .id("https://github.com/readium/readium-js-viewer")
            .name("Readium")
            .dateCreated(getDefaultDateCreated())
            .dateModified(getDefaultDateModified())
            .build();
    }

    /**
     * Build Readium App context
     * @return Readium App learning context
     */
    public static LearningContext buildReadiumAppLearningContext() {
        return LearningContext.builder()
            .edApp(buildReadiumApp())
            .lisOrganization(buildAmRev101CourseSection())
            .agent(buildReadiumApp())
            .build();
    }

    /**
     * @return Readium Viewer learning context
     */
    public static LearningContext buildReadiumStudentLearningContext() {

        return LearningContext.builder()
            .edApp(buildReadiumApp())
            .lisOrganization(buildAmRev101CourseSection())
            .agent(buildStudent554433())
            .build();
    }

    /**
     * @return Session
     */
    public static Session buildSessionStart() {
        return Session.builder()
            .id("https://github.com/readium/session-123456789")
            .name("session-123456789")
            .actor(buildStudent554433())
            .dateCreated(getDefaultDateCreated())
            .dateModified(getDefaultDateModified())
            .startedAtTime(getDefaultStartedAtTime())
            .build();
    }

    /**
     * @return Session
     */
    public static Session buildSessionEnd() {
        return Session.builder()
            .id("https://github.com/readium/session-123456789")
            .name("session-123456789")
            .actor(buildStudent554433())
            .dateCreated(getDefaultDateCreated())
            .dateModified(getDefaultDateModified())
            .startedAtTime(getDefaultStartedAtTime())
            .endedAtTime(getDefaultEndedAtTime())
            .duration("PT3000S")
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
                        .dateCreated(getDefaultDateCreated())
                        .dateModified(getDefaultDateModified())
                        .build(),
                    Person.builder()
                        .id("https://some-university.edu/students/667788")
                        .dateCreated(getDefaultDateCreated())
                        .dateModified(getDefaultDateModified())
                        .build()))
                .dateCreated(getDefaultDateCreated())
                .dateModified(getDefaultDateModified())
            .build();
    }

    /**
     * Sample student.
     * @return agent
     */
    public static final Agent buildStudent554433() {
        return Person.builder()
            .id("https://some-university.edu/user/554433")
            .dateCreated(getDefaultDateCreated())
            .dateModified(getDefaultDateModified())
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
                .dateCreated(getDefaultDateCreated())
                .dateModified(getDefaultDateModified())
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
            .dateCreated(getDefaultDateCreated())
            .dateModified(getDefaultDateModified())
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
            .actor((Person) learningContext.getAgent())
            .object(video)
            .action(actionKey)
            .target(location)
            .startedAtTime(getDefaultStartedAtTime())
            .build();
    }

    /**
     * @return media location.
     */
    public static final MediaLocation buildVideoMediaLocation() {
        return MediaLocation.builder()
            .id(buildVideoWithLearningObjective().getId())
            .dateCreated(getDefaultDateCreated())
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
                .dateCreated(getDefaultDateCreated())
                .build())
            .dateCreated(getDefaultDateCreated())
            .dateModified(getDefaultDateModified())
            .duration(1420)
            .build();
    }

    /**
     * January 1, 2015, 06:00:00.000 GMT
     * @return return date created
     */
    public static DateTime getDefaultDateCreated() {
        return new DateTime(2015, 1, 1, 6, 0, 0, 0, DateTimeZone.UTC);
    }

    /**
     * February 2, 2015, 11:30:00.000 GMT
     * @return return date modified
     */
    public static DateTime getDefaultDateModified() {
        return new DateTime(2015, 2, 2, 11, 30, 0, 0, DateTimeZone.UTC);
    }

    /**
     * January 15, 2015, 09:30:00.000 GMT
     * @return return date published
     */
    public static DateTime getDefaultDatePublished(){
        return new DateTime(2015, 1, 15, 9, 30, 0, 0, DateTimeZone.UTC);
    }

    /**
     * January 16, 2015, 05:00:00.000 GMT
     * @return date to activate
     */
    public static DateTime getDefaultDateToActivate(){
        return new DateTime(2015, 1, 16, 5, 0, 0, 0, DateTimeZone.UTC);
    }

    /**
     * Same date as activate date
     * @return date to show
     */
    public static DateTime getDefaultDateToShow() {
        return getDefaultDateToActivate();
    }

    /**
     * Same date as activate date
     * @return date to start on
     */
    public static DateTime getDefaultDateToStartOn() {
        return getDefaultDateToActivate();
    }

    /**
     * February 28, 2015, 11:59:59.000 GMT
     * @return date to submit
     */
    public static DateTime getDefaultDateToSubmit() {
        return new DateTime(2015, 2, 28, 11, 59, 59, 0, DateTimeZone.UTC);
    }

    /**
     * February 15, 2015, 10:15:00.000 GMT
     * @return started at time
     */
    public static DateTime getDefaultStartedAtTime() {
        return new DateTime(2015, 2, 15, 10, 15, 0, 0, DateTimeZone.UTC);
    }

    /**
     * February 15, 2015, 11:05:00.000 GMT
     * @return ended at time
     */
    public static DateTime getDefaultEndedAtTime() {
        return new DateTime(2015, 2, 15, 11, 05, 0, 0, DateTimeZone.UTC);
    }
}