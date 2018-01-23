/**
 * This file is part of IMS Caliper Analytics™ and is licensed to
 * IMS Global Learning Consortium, Inc. (http://www.imsglobal.org)
 * under one or more contributor license agreements.  See the NOTICE
 * file distributed with this work for additional information.
 *
 * IMS Caliper is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, version 3 of the License.
 *
 * IMS Caliper is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.imsglobal.caliper.selectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;

/**
 * A Selector which describes a range of text based on its start and end positions
 * Defined by: http://www.w3.org/ns/oa#d4e667
 */
public class TextPositionSelector implements Selector {

    @JsonProperty("type")
    private String type = "TextPositionSelector";

    @JsonProperty("start")
    private int start;

    @JsonProperty("end")
    private int end;

    // Constructor
    public TextPositionSelector() {

    }
    
    // Constructor
    public TextPositionSelector(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * @return the type
     */
    @Nonnull
    public String getType() {
        return type;
    }

    /**
     * @return the start
     */
    @Nonnull
    public int getStart() {
        return start;
    }

    /**
     * @param start
     * the start to set
     */
    public void setStart(@Nonnull int start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    @Nonnull
    public int getEnd() {
        return end;
    }

    /**
     * @param end
     * the end to set
     */
    public void setEnd(@Nonnull int end) {
        this.end = end;
    }
}