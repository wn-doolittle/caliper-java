package org.imsglobal.caliper.entities.assignable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableMap;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.validators.EntityValidator;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

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
    "extensions",
    "dateCreated",
    "dateModified",
    "datePublished",
    "version",
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
        ASSESSMENT_ITEM("http://purl.imsglobal.org/caliper/v1/AssessmentItem");

        private final String uri;
        private static Map<String, Type> lookup;

        /**
         * Create reverse lookup hash map
         */
        static {
            Map<String, Type> map = new HashMap<String, Type>();
            for (Type constants : Type.values()) {
                map.put(constants.uri(), constants);
            }
            lookup = ImmutableMap.copyOf(map);
        }

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

        /**
         * Retrieve enum type from reverse lookup map.
         * @param uri
         * @return Event.Type enum
         */
        public static AssignableDigitalResource.Type lookupConstantWithTypeURI(String uri) {
            return lookup.get(uri);
        }
    }

    @JsonProperty("@type")
    private final String type;

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

        EntityValidator.checkTypeUri(builder.type, DigitalResource.Type.ASSIGNABLE_DIGITAL_RESOURCE);

        this.type = builder.type;
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
    @Nonnull
    public String getType() {
        return type;
    }

    /**
     * @return the dateToActivate
     */
    @Nullable
    public DateTime getDateToActivate() {
        return dateToActivate;
    }

    /**
     * @return the dateToShow
     */
    @Nullable
    public DateTime getDateToShow() {
        return dateToShow;
    }

    /**
     * @return the dateToStartOn
     */
    @Nullable
    public DateTime getDateToStartOn() {
        return dateToStartOn;
    }

    /**
     * @return the dateToSubmit
     */
    @Nullable
    public DateTime getDateToSubmit() {
        return dateToSubmit;
    }

    /**
     * @return the maxAttempts
     */
    @Nullable
    public int getMaxAttempts() {
        return maxAttempts;
    }

    /**
     * @return the maxSubmits
     */
    @Nullable
    public int getMaxSubmits() {
        return maxSubmits;
    }

    /**
     * @return the maxScore
     */
    @Nullable
    public double getMaxScore() {
        return maxScore;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends DigitalResource.Builder<T>  {
        private String type;
        private DateTime dateToActivate, dateToShow, dateToStartOn, dateToSubmit;
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
        public T maxScore(double maxScore) {
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