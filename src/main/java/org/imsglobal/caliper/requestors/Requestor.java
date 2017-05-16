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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.imsglobal.caliper.config.Options;

import java.io.UnsupportedEncodingException;

public abstract class Requestor {
    private Options options;

    /**
     * Constructor
     */
    public Requestor(Options opts) {
        this.options = opts;
    }

    /**
     * Retrieve options
     * @return options
     */
    public Options getOptions() {
        return options;
    }

    /**
     * Send Envelope to a target endpoint
     * @param envelope
     * @return true/false boolean on success/failure
     */
    public abstract boolean send(Envelope envelope);

    /**
     * Serialize Caliper envelope.
     * @param envelope
     * @param mapper
     * @return String
     * @throws JsonProcessingException
     */
    public String serializeEnvelope(Envelope envelope, ObjectMapper mapper) throws JsonProcessingException {
        return mapper.writeValueAsString(envelope);
    }

    /**
     * Generate an HTTP StringEntity from the provided JSON string.  Set the ContentType to 'application/json'.
     * @param value
     * @param contentType
     * @return
     * @throws UnsupportedEncodingException
     */
    public StringEntity generatePayload(String value, ContentType contentType) throws UnsupportedEncodingException {
        StringEntity payload = new StringEntity(value);
        payload.setContentType(contentType.toString());

        return payload;
    }
}