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

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Overrides the global ObjectMapper serializationInclusion setting as
 * JsonInclude.Include.NON_EMPTY excludes properties of type double
 * whenever the value is set to 0.0.  Reference this custom serializer
 * by annotating the target property's accessor using @JsonSerialize annotation.
 */
public class JxnDoubleSerializer extends JsonSerializer<Double> {
    @Override
    public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider)
                                            throws IOException, JsonGenerationException {
        if (Double.isNaN(value / value)) {
            jgen.writeNull();
        } else {
            // Pattern syntax: "0" = always displayed; "#" = a digit, leading zeroes omitted; "." = decimal separator
            final String pattern = "##0.0#";
            final DecimalFormat formatter = new DecimalFormat(pattern);
            final String output = formatter.format(value);

            jgen.writeNumber(output);
        }
    }
}