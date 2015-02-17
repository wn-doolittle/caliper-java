package org.imsglobal.caliper.entities.assessment;

public enum AssessmentItemCardinality {
    MULTIPLE("multiple"),
    ORDERED("ordered"),
    // RECORD("record"),
    SINGLE("single");

    private final String value;

    /**
     * Private constructor
     * @param value
     */
    private AssessmentItemCardinality(final String value) {
        this.value = value;
    }

    /**
     * @return URI string
     */
    public String value() {
        return value;
    }
}