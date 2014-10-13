package org.imsglobal.caliper.entities.assessment;

import org.imsglobal.caliper.entities.assignable.CaliperAssignableDigitalResource;
import org.imsglobal.caliper.entities.qti.AssessmentItem;

/**
 * Caliper representation of an Assessment Item.
 * 
 * Part of the Assessment Metric Profile.
 */
public class CaliperAssessmentItem extends CaliperAssignableDigitalResource implements AssessmentItem {

    /**
     * @param builder apply builder object properties to the CaliperAssessmentItem object.
     */
    protected CaliperAssessmentItem(Builder<?> builder) {
        super(builder);
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperAssignableDigitalResource.Builder<T>  {

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(CaliperAssignableDigitalResource.Type.CALIPER_ASSESSMENT_ITEM.uri());
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of CaliperAssessmentItem.
         */
        public CaliperAssessmentItem build() {
            return new CaliperAssessmentItem(this);
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
