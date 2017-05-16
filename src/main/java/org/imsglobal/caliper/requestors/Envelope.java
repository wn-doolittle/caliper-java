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

package org.imsglobal.caliper.requestors;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import java.util.List;

public class Envelope {

    @JsonProperty("sensor")
    private String sensorId;

    @JsonProperty("sendTime")
    private DateTime sendTime;

    @JsonProperty("dataVersion")
    private String dataVersion;

    @JsonProperty("data")
    private List<Object> data;

    /**
     * Constructor
     * @param id
     * @param sendTime
     * @param data
     */
    public Envelope(@Nonnull String id, @Nonnull DateTime sendTime, @Nonnull String dataVersion, @Nonnull List<Object> data) {
        this.sensorId = id;

        if (sendTime == null) {
            this.sendTime = DateTime.now();
        } else {
            this.sendTime = sendTime;
        }
        this.dataVersion = dataVersion;
        this.data = data;
    }

    /**
     * Get the sensor
     * @return sensor identifier
     */
    @Nonnull
    public String getSensorId() {
        return sensorId;
    }

    /**
     * Get the sent time.
     * @return the sent time.
     */
    @Nonnull
    public DateTime getSendTime() {
        return sendTime;
    }

    /**
     * Get the dataVersion.
     * @return the dataVersion
     */
    @Nonnull
    public String getDataVersion() {
        return dataVersion;
    }

    /**
     * Get the data.
     * @return the data
     */
    @Nonnull
    public List<Object> getData() {
        return data;
    }
}