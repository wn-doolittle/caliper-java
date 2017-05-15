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

package org.imsglobal.caliper.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.imsglobal.caliper.entities.CaliperCoercible;

import java.io.IOException;

public class JxnCoercibleSerializer extends JsonSerializer<CaliperCoercible> {
    private JsonSerializer<Object> defaultSerializer;

    /**
     * Constructor
     */
    public JxnCoercibleSerializer() {
        this(null);
    }

    /**
     * Constructor that injects default serializer.
     * @param defaultSerializer
     */
    public JxnCoercibleSerializer(JsonSerializer<Object> defaultSerializer) {
        this.defaultSerializer = defaultSerializer;
    }

    @Override
    public void serialize(CaliperCoercible value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException, JsonProcessingException {

        //System.out.print("IS_COERCED: " + value.getClass().getSimpleName() + " " + value.isCoercedToId() + "\n");

        if (value.isCoercedToId()) {
            jgen.writeString(value.getId());
        } else {
            defaultSerializer.serialize(value, jgen, provider);
        }
    }
}