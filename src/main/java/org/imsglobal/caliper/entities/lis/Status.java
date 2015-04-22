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

package org.imsglobal.caliper.entities.lis;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public enum Status implements org.imsglobal.caliper.entities.w3c.Status {
    ACTIVE("http://purl.imsglobal.org/vocab/lis/v2/status#Active"),
    DELETED("http://purl.imsglobal.org/vocab/lis/v2/status#Deleted"),
    INACTIVE("http://purl.imsglobal.org/vocab/lis/v2/status#Inactive");

    private final String value;
    private static Map<String, Status> lookup;

    /**
     * Create reverse lookup hash map
     */
    static {
        Map<String, Status> map = new HashMap<String, Status>();
        for (Status constants : Status.values()) {
            map.put(constants.getValue(), constants);
        }
        lookup = ImmutableMap.copyOf(map);
    }

    /**
     * Private constructor
     * @param value
     */
    private Status(final String value) {
        this.value = value;
    }

    /**
     * @param key
     * @return true if lookup returns a key match; false otherwise.
     */
    public static boolean hasKey(String key) {
        return lookup.containsKey(key);
    }

    /**
     * @return the URI
     */
    @JsonValue
    public String getValue() {
        return value;
    }
}