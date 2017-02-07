package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import javax.annotation.Nullable;

/**
 * Reusable object for storing Entity startedAtTime, endedAtTime and duration property values.
 */
public class TimePeriod {

    @JsonProperty("startedAtTime")
    private DateTime startedAtTime;

    @JsonProperty("endedAtTime")
    private DateTime endedAtTime;

    @JsonProperty("duration")
    private String duration;

    /**
     * Constructor
     */
    public TimePeriod() {

    }

    /**
     * @return started time
     */
    @Nullable
    public DateTime getStartedAtTime() {
        return startedAtTime;
    }

    /**
     * Set the start time
     * @param startedAtTime
     */
    public void setStartedAtTime(@Nullable DateTime startedAtTime) {
        this.startedAtTime = startedAtTime;
    }

    /**
     * @return ended at time
     */
    @Nullable
    public DateTime getEndedAtTime() {
        return endedAtTime;
    }

    /**
     * Set the end time
     * @param endedAtTime
     */
    public void setEndedAtTime(@Nullable DateTime endedAtTime) {
        this.endedAtTime = endedAtTime;
    }

    /**
     * An xsd:duration (http://books.xmlschemata.org/relaxng/ch19-77073.html)
     * The format is expected to be PnYnMnDTnHnMnS.  Valid values include PT1004199059S, PT130S,
     * PT2M10S, P1DT2S, -P1Y, or P1Y2M3DT5H20M30.123S.  The following values are invalid:
     * 1Y (leading P is missing), P1S (T separator is missing), P-1Y (all parts must be positive),
     * P1M2Y (parts order is significant and Y must precede M) or P1Y-1M (all parts must be positive).
     *
     * @return duration
     */
    @Nullable
    public String getDuration() {
        return duration;
    }

    /**
     * Set the duration
     * @param duration
     */
    public void setDuration(@Nullable String duration) {
        this.duration = duration;
    }
}