package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EntityType {
    AGENT("http://purl.imsglobal.org/caliper/v1/Agent"),
    ATTEMPT("http://purl.imsglobal.org/caliper/v1/Attempt"),
    COURSE_OFFERING("http://purl.imsglobal.org/caliper/v1/lis/CourseOffering"),
    COURSE_SECTION("http://purl.imsglobal.org/caliper/v1/lis/CourseSection"),
    DEPARTMENT("http://purl.imsglobal.org/caliper/v1/w3c/OrganizationalUnit"),
    DIGITAL_RESOURCE("http://purl.imsglobal.org/caliper/v1/DigitalResource"),
    ENTITY("http://purl.imsglobal.org/caliper/v1/Entity"),
    GENERATED("http://purl.imsglobal.org/caliper/v1/Generated"),
    GROUP("http://purl.imsglobal.org/caliper/v1/lis/Group"),
    LEARNING_OBJECTIVE("http://purl.imsglobal.org/caliper/v1/LearningObjective"),
    MEDIA_OBJECT("http://purl.imsglobal.org/caliper/v1/MediaObject"),
    MEMBERSHIP("http://purl.imsglobal.org/caliper/v1/lis/Membership"),
    PERSON("http://purl.imsglobal.org/caliper/v1/lis/Person"),
    ORGANIZATION("http://purl.imsglobal.org/caliper/v1/w3c/Organization"),
    RESPONSE("http://purl.imsglobal.org/caliper/v1/Response"),
    RESULT("http://purl.imsglobal.org/caliper/v1/Result"),
    SESSION("http://purl.imsglobal.org/caliper/v1/Session"),
    SOFTWARE_APPLICATION("http://purl.imsglobal.org/caliper/v1/SoftwareApplication"),
    TARGET("http://purl.imsglobal.org/caliper/v1/Target"),
    VIEW("http://purl.imsglobal.org/caliper/v1/View"),

    ANNOTATION("http://purl.imsglobal.org/caliper/v1/Annotation"),
    BOOKMARK_ANNOTATION("http://purl.imsglobal.org/caliper/v1/BookmarkAnnotation"),
    HIGHLIGHT_ANNOTATION("http://purl.imsglobal.org/caliper/v1/HighlightAnnotation"),
    SHARED_ANNOTATION("http://purl.imsglobal.org/caliper/v1/SharedAnnotation"),
    TAG_ANNOTATION("http://purl.imsglobal.org/caliper/v1/TagAnnotation"),

    ASSESSMENT("http://purl.imsglobal.org/caliper/v1/Assessment"),
    ASSESSMENT_ITEM("http://purl.imsglobal.org/caliper/v1/AssessmentItem"),

    ASSIGNABLE_DIGITAL_RESOURCE("http://purl.imsglobal.org/caliper/v1/AssignableDigitalResource"),
    EPUB_CHAPTER("http://www.idpf.org/epub/vocab/structure/#chapter"),
    EPUB_PART("http://www.idpf.org/epub/vocab/structure/#part"),
    EPUB_SUB_CHAPTER("http://www.idpf.org/epub/vocab/structure/#subchapter"),
    EPUB_VOLUME("http://www.idpf.org/epub/vocab/structure/#volume"),
    FRAME("http://purl.imsglobal.org/caliper/v1/Frame"),
    READING("http://www.idpf.org/epub/vocab/structure"),
    WEB_PAGE("http://purl.imsglobal.org/caliper/v1/WebPage"),

    AUDIO_OBJECT("http://purl.imsglobal.org/caliper/v1/AudioObject"),
    IMAGE_OBJECT("http://purl.imsglobal.org/caliper/v1/ImageObject"),
    VIDEO_OBJECT("http://purl.imsglobal.org/caliper/v1/VideoObject"),
    MEDIA_LOCATION("http://purl.imsglobal.org/caliper/v1/MediaLocation"),

    FILLINBLANK("http://purl.imsglobal.org/caliper/v1/Response/FillinBlank"),
    MULTIPLECHOICE("http://purl.imsglobal.org/caliper/v1/Response/MultipleChoice"),
    MULTIPLERESPONSE("http://purl.imsglobal.org/caliper/v1/Response/MultipleResponse"),
    SELECTTEXT("http://purl.imsglobal.org/caliper/v1/Response/SelectText"),
    TRUEFALSE("http://purl.imsglobal.org/caliper/v1/Response/TrueFalse");

    private final String value;

    /**
     * Private constructor
     * @param value
     */
    private EntityType(final String value) {
        this.value = value;
    }

    /**
     * @return URI string
     */
    @JsonValue
    public String getValue() {
        return value;
    }
}