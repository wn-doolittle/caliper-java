package org.imsglobal.caliper.validators;

public class ValidatorResult {
    private boolean isValid = false;
    private ValidatorErrorMessage message = new ValidatorErrorMessage();

    /**
     * Constructor
     */
    public ValidatorResult() {

    }

    /**
     * Retrieve the error message.
     * @return the error message
     */
    public ValidatorErrorMessage errorMessage() {
        return message;
    }

    /**
     * Retrieve isValid.
     * @return boolean true/false
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     * Set isValid true or false.
     * @param value
     */
    public void setIsValid(boolean value) {
        this.isValid = value;
    }
}