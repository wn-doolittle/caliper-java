package org.imsglobal.caliper.entities.annotation;

import javax.annotation.Nonnull;

/**
 * A Selector which describes a range of text based on its start and end positions
 * Defined by: http://www.w3.org/ns/oa#d4e667
 */
public class TextPositionSelector {

    private String start;
    private String end;

    /**
     * @return the start
     */
    @Nonnull
    public String getStart() {
        return start;
    }

    /**
     * @param start
     * the start to set
     */
    public void setStart(@Nonnull String start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    @Nonnull
    public String getEnd() {
        return end;
    }

    /**
     * @param end
     * the end to set
     */
    public void setEnd(@Nonnull String end) {
        this.end = end;
    }
}