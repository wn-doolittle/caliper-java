package org.imsglobal.caliper.entities.assignable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.entities.DigitalResource;
import org.joda.time.DateTime;

/**
 * Assignable Digital Resource
 */
@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "description",
    "objectType",
    "alignedLearningObjective",
    "keywords",
    "isPartOf",
    "properties",
    "dateCreated",
    "dateModified",
    "datePublished",
    "dateToActivate",
    "dateToShow",
    "dateToStartOn",
    "dateToSubmit",
    "maxAttempts",
    "maxSubmits",
    "maxScore" })
public class AssignableDigitalResource extends DigitalResource implements org.imsglobal.caliper.entities.assignable.Assignable {

    public enum Type {
        ASSESSMENT("http://purl.imsglobal.org/caliper/v1/Assessment"),
        ASSESSMENT_ITEM("http://purl.imsglobal.org/caliper/v1/AssessmentItem"),
        ASSESSMENT_ITEM_DRAGOBJECT("http://purl.imsglobal.org/caliper/v1/AssessmentItem/DragObject"),
        ASSESSMENT_ITEM_HOTSPOT("http://purl.imsglobal.org/caliper/v1/AssessmentItem/HotSpot"),
        ASSESSMENT_ITEM_FILLINBLANK("http://purl.imsglobal.org/caliper/v1/AssessmentItem/FillinBlank"),
        ASSESSMENT_ITEM_MULTIPLECHOICE("http://purl.imsglobal.org/caliper/v1/AssessmentItem/MultipleChoice"),
        ASSESSMENT_ITEM_MULTIPLERESPONSE("http://purl.imsglobal.org/caliper/v1/AssessmentItem/MultipleResponse"),
        ASSESSMENT_ITEM_SELECTTEXT("http://purl.imsglobal.org/caliper/v1/AssessmentItem/SelectText"),
        ASSESSMENT_ITEM_SHORTANSWER("http://purl.imsglobal.org/caliper/v1/AssessmentItem/ShortAnswer"),
        ASSESSMENT_ITEM_SLIDER("http://purl.imsglobal.org/caliper/v1/AssessmentItem/Slider"),
        ASSESSMENT_ITEM_TRUEFALSE("http://purl.imsglobal.org/caliper/v1/AssessmentItem/TrueFalse");

        private final String uri;

        /**
         * Private constructor
         * @param uri
         */
        private Type(final String uri) {
            this.uri = uri;
        }

        /**
         * @return URI string
         */
        public String uri() {
            return uri;
        }
    }

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("dateCreated")
    private DateTime dateCreated;

    @JsonProperty("dateToActivate")
    private DateTime dateToActivate;

    @JsonProperty("dateToShow")
    private DateTime dateToShow;

    @JsonProperty("dateToStartOn")
    private DateTime dateToStartOn;

    @JsonProperty("dateToSubmit")
    private DateTime dateToSubmit;

    @JsonProperty("maxAttempts")
    private int maxAttempts;

    @JsonProperty("maxSubmits")
    private int maxSubmits;

    @JsonProperty("maxScore")
    private double maxScore;

    /**
     * @param builder apply builder object properties to the Target object.
     */
    protected AssignableDigitalResource(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.dateCreated = builder.dateCreated;
        this.dateToActivate = builder.dateToActivate;
        this.dateToShow = builder.dateToShow;
        this.dateToStartOn = builder.dateToStartOn;
        this.dateToSubmit = builder.dateToSubmit;
        this.maxAttempts = builder.maxAttempts;
        this.maxSubmits = builder.maxSubmits;
        this.maxScore = builder.maxScore;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return the dateCreated
     */

    public DateTime getDateCreated() {
        return dateCreated;
    }

    /**
     * @return the dateToActivate
     */

    public DateTime getDateToActivate() {
        return dateToActivate;
    }

    /**
     * @return the dateToShow
     */

    public DateTime getDateToShow() {
        return dateToShow;
    }

    /**
     * @return the dateToStartOn
     */

    public DateTime getDateToStartOn() {
        return dateToStartOn;
    }

    /**
     * @return the dateToSubmit
     */

    public DateTime getDateToSubmit() {
        return dateToSubmit;
    }

    /**
     * @return the maxAttempts
     */
    public int getMaxAttempts() {
        return maxAttempts;
    }

    /**
     * @return the maxSubmits
     */
    public int getMaxSubmits() {
        return maxSubmits;
    }

    /**
     * @return the maxScore
     */
    public double getMaxScore() {
        return maxScore;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends DigitalResource.Builder<T>  {
        private String type;
        private DateTime dateCreated, dateToActivate, dateToShow, dateToStartOn, dateToSubmit;
        private int maxAttempts, maxSubmits;
        private double maxScore;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(DigitalResource.Type.ASSIGNABLE_DIGITAL_RESOURCE.uri());
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(String type) {
            this.type = type;
            return self();
        }

        /**
         * @param dateCreated
         * @return builder
         */
        public T dateCreated(DateTime dateCreated) {
            this.dateCreated = dateCreated;
            return self();
        }

        /**
         * @param dateToActivate
         * @return builder
         */
        public T dateToActivate(DateTime dateToActivate) {
            this.dateToActivate = dateToActivate;
            return self();
        }

        /**
         * @param dateToShow
         * @return builder
         */
        public T dateToShow(DateTime dateToShow) {
            this.dateToShow = dateToShow;
            return self();
        }

        /**
         * @param dateToStartOn
         * @return builder
         */
        public T dateToStartOn(DateTime dateToStartOn) {
            this.dateToStartOn = dateToStartOn;
            return self();
        }

        /**
         * @param dateToSubmit
         * @return builder
         */
        public T dateToSubmit(DateTime dateToSubmit) {
            this.dateToSubmit = dateToSubmit;
            return self();
        }

        /**
         * @param maxAttempts
         * @return builder
         */
        public T maxAttempts(int maxAttempts) {
            this.maxAttempts = maxAttempts;
            return self();
        }

        /**
         * @param maxSubmits
         * @return builder
         */
        public T maxSubmits(int maxSubmits) {
            this.maxSubmits = maxSubmits;
            return self();
        }

        /**
         * @param maxScore
         * @return builder
         */
        public T maxScore(int maxScore) {
            this.maxScore = maxScore;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of Target.
         */
        public AssignableDigitalResource build() {
            return new AssignableDigitalResource(this);
        }
    }

    /**
     *
     */
    private static class Builder2 extends Builder<Builder2> {
        @Override
        protected Builder2 self() {
            return this;
        }
    }

    /**
     * Static factory method.
     * @return a new instance of the builder.
     */
    public static Builder<?> builder() {
        return new Builder2();
    }
}