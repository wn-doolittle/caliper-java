package org.imsglobal.caliper;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.LearningObjective;
import org.imsglobal.caliper.entities.Session;
import org.imsglobal.caliper.entities.SoftwareApplication;
import org.imsglobal.caliper.entities.WebPage;
import org.imsglobal.caliper.entities.annotation.Annotation;
import org.imsglobal.caliper.entities.annotation.BookmarkAnnotation;
import org.imsglobal.caliper.entities.annotation.HighlightAnnotation;
import org.imsglobal.caliper.entities.annotation.SharedAnnotation;
import org.imsglobal.caliper.entities.annotation.TagAnnotation;
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
import org.imsglobal.caliper.events.AnnotationEvent;
import org.imsglobal.caliper.events.AssessmentEvent;
import org.imsglobal.caliper.events.AssessmentItemEvent;
import org.imsglobal.caliper.events.AssignableEvent;
import org.imsglobal.caliper.events.MediaEvent;
import org.imsglobal.caliper.events.NavigationEvent;
import org.imsglobal.caliper.events.OutcomeEvent;
import org.imsglobal.caliper.events.ReadingEvent;
import org.imsglobal.caliper.events.SessionEvent;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
            .id("AmRev-101-landingPage")
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
            .actor((Person) learningContext.getAgent())
            .action(actionKey)
            .object(item)
            //.generated(Response.builder() . . .) TODO:Add item response
            .startedAtTime(getDefaultStartedAtTime())
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
                .build())
            .add(AssessmentItem.builder()
                .id("https://some-university.edu/politicalScience/2014/american-revolution-101/assessment1/item2")
                .name("Assessment Item 2")
                .isPartOf("https://some-university.edu/politicalScience/2014/american-revolution-101/assessment1")
                .maxAttempts(2)
                .maxSubmits(2)
                .maxScore(1)
                .build())
            .add(AssessmentItem.builder()
                .id("https://some-university.edu/politicalScience/2014/american-revolution-101/assessment1/item3")
                .name("Assessment Item 3")
                .isPartOf("https://some-university.edu/politicalScience/2014/american-revolution-101/assessment1")
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
    public static ReadingEvent buildEpubViewEvent(LearningContext learningContext,
                                               DigitalResource epub,
                                               String actionKey,
                                               DigitalResource target) {
        return ReadingEvent.builder()
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

    public static Date getDefaultDateCreated(){
        //January 1, 2015, 06:00:00.000 GMT
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 6);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        return cal.getTime();
    }

    public static Date getDefaultDateModified(){
        //February 2, 2015, 11:30:00.000 GMT
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal.set(Calendar.DATE, 2);
        cal.set(Calendar.HOUR_OF_DAY, 11);
        cal.set(Calendar.MINUTE,30);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        return cal.getTime();
    }

    /**
     * Generate published date.
     * @return ISO-8601 date
     */
    public static Date getDefaultDatePublished(){
        //January 15, 2015, 09:30:00.000 GMT
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 15);
        cal.set(Calendar.HOUR_OF_DAY, 9);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * Generate activated date
     * @return ISO-8601 date
     */
    public static Date getDefaultDateToActivate(){
        //January 16, 2015, 05:00:00.000 GMT
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 16);
        cal.set(Calendar.HOUR_OF_DAY, 5);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * Generate shown date
     * @return ISO-8601 date
     */
    public static Date getDefaultDateToShow() {
        return getDefaultDateToActivate();
    }

    /**
     * Generate date to start on
     * @return
     */
    public static Date getDefaultDateToStartOn() {
        return getDefaultDateToActivate();
    }

    /**
     * Generate submit date
     * @return ISO-8601 date
     */
    public static Date getDefaultDateToSubmit() {
        //February 28, 2015, 11:59:59.000 GMT
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal.set(Calendar.DATE, 28);
        cal.set(Calendar.HOUR_OF_DAY, 11);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * Generate started at time.
     * @return ISO-8601 date
     */
    public static Date getDefaultStartedAtTime() {
        //February 15, 2015, 10:15:00.000 GMT
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal.set(Calendar.DATE, 15);
        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 15);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * Generate ended at time.
     * @return ISO-8601 date
     */
    public static Date getDefaultEndedAtTime() {
        //February 15, 2015, 11:05:00.000 GMT
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal.set(Calendar.DATE, 15);
        cal.set(Calendar.HOUR_OF_DAY, 11);
        cal.set(Calendar.MINUTE, 05);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}