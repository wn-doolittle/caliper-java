package org.imsglobal.caliper.validators;

public class ValidatorErrorMessage {
    private StringBuilder builder;
    private String newline = "\n";

    /**
     * Constructor
     */
    public ValidatorErrorMessage() {
        this.builder = new StringBuilder();
    }

    /**
     * Append violation and add comma if appropriate when building conformance violation message.
     * @param text
     */
    public void appendViolation(String text) {
        if (this.builder.length() > 0) {
            this.builder.append("," + this.newline);
            this.builder.append(text + this.newline);
        } else {
            this.builder.append(text);
        }
    }

    /**
     * Prepend text, typically a Caliper conformance clause that prefixes a string of one or more violations.
     * @param text
     */
    public void prependViolation(String text) {
        this.builder.insert(0, text + this.newline);
    }

    /**
     * Prepend intro text to message and close trailing sentence with a period.
     * @param text
     */
    public void endMessage(String text) {
        this.prependViolation(text);
        this.endSentence();
    }

    /**
     * Append period and end sentence.
     */
    public void endSentence() {
        this.builder.append(".");
    }

    /**
     * Return message length from builder.
     * @return
     */
    public int length() {
        return this.builder.length();
    }

    /**
     * Return message string from builder.
     * @return
     */
    @Override
    public String toString() {
        return this.builder.toString();
    }
}